package taskflow.stockage;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

import taskflow.modele.Tache;

public final class ChargementTaches {

    private ChargementTaches() {
    }

    public static List<Tache> charger(
            Path chemin) throws IOException {

        if (Files.notExists(chemin)) {
            return List.of();
        }

        String contenu = StandardCharsets.UTF_8
            .newDecoder()
            .onMalformedInput(CodingErrorAction.REPORT)
            .onUnmappableCharacter(CodingErrorAction.REPORT)
            .decode(
                java.nio.ByteBuffer.wrap(
                    Files.readAllBytes(chemin)
                )
            )
            .toString();

        List<String> lignes = Arrays.asList(
            contenu.split("\\R", -1)
        );

        if (lignes.isEmpty() ||
            !lignes.get(0).equals("TF1")) {

            throw new IOException(
                "En-tête de sauvegarde invalide."
            );
        }

        List<Tache> resultat = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();

        try {

            for (int i = 1; i < lignes.size(); i++) {

                if (i == lignes.size() - 1 &&
                    lignes.get(i).isEmpty()) {
                    break;
                }

                Tache tache =
                    FormatTaches.decoder(lignes.get(i));

                if (!ids.add(tache.getId())) {

                    throw new IllegalArgumentException(
                        "Identifiant dupliqué."
                    );
                }

                resultat.add(tache);
            }

        } catch (RuntimeException e) {

            throw new IOException(
                "Fichier de sauvegarde mal formé : "
                + e.getMessage(),
                e
            );
        }

        return resultat;
    }
}
