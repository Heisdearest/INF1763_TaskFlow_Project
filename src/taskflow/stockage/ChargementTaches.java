package taskflow.stockage;

import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.charset.*;

import java.nio.file.*;

import java.util.*;

import taskflow.modele.Tache;

public final class ChargementTaches {

    private ChargementTaches() {

    }

    public static List<Tache> charger(

            Path chemin) throws IOException {

        Objects.requireNonNull(

            chemin,

            "Le chemin du fichier est obligatoire."

        );

        // Aucun fichier : retourner une liste vide.

        if (Files.notExists(chemin)) {

            return List.of();

        }

        // Vérifier que le chemin correspond à un fichier.

        if (!Files.isRegularFile(chemin)) {

            throw new IOException(

                "Le chemin ne correspond pas à un fichier."

            );

        }

        // Lire le fichier en UTF-8.

        byte[] donnees = Files.readAllBytes(chemin);

        String contenu;

        try {

            contenu = StandardCharsets.UTF_8

                .newDecoder()

                .onMalformedInput(

                    CodingErrorAction.REPORT

                )

                .onUnmappableCharacter(

                    CodingErrorAction.REPORT

                )

                .decode(ByteBuffer.wrap(donnees))

                .toString();

        } catch (CharacterCodingException e) {

            throw new IOException(

                "Le fichier contient un encodage invalide.",

                e

            );

        }

        List<String> lignes = Arrays.asList(

            contenu.split("\\R", -1)

        );

        // Vérifier l'en-tête.

        if (lignes.isEmpty() ||

            !lignes.get(0).equals("TF1")) {

            throw new IOException(

                "Format de sauvegarde non reconnu."

            );

        }

        List<Tache> resultat = new ArrayList<>();

        Set<Integer> identifiants = new HashSet<>();

        try {

            for (int i = 1; i < lignes.size(); i++) {

                String ligne = lignes.get(i);

                // Ignorer uniquement la dernière ligne vide

                // créée par un saut de ligne final.

                if (i == lignes.size() - 1 &&

                    ligne.isEmpty()) {

                    break;

                }

                Tache tache =

                    FormatTaches.decoder(ligne);

                // Vérifier les identifiants dupliqués.

                if (!identifiants.add(tache.getId())) {

                    throw new IllegalArgumentException(

                        "Identifiant dupliqué : "

                        + tache.getId()

                    );

                }

                resultat.add(tache);

            }

        } catch (RuntimeException e) {

            throw new IOException(

                "Erreur de lecture du fichier à sauvegarder : "

                + e.getMessage(),

                e

            );

        }

        return resultat;

    }

}
