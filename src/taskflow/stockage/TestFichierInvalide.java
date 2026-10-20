package taskflow.stockage;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;

import java.nio.file.Path;

public final class TestFichierInvalide {

    public static void main(String[] args)

            throws Exception {

        Path dossier = Files.createTempDirectory(

            "taskflow-test-invalide-"

        );

        try {

            testerEnteteInvalide(dossier);

            testerLigneCorrompue(dossier);

            testerFichierVide(dossier);

            System.out.println(

                "TOUS LES TESTS TF-12 ONT RÉUSSI."

            );

        } finally {

            try (var fichiers = Files.list(dossier)) {

                for (Path fichier : fichiers.toList()) {

                    Files.deleteIfExists(fichier);

                }

            }

            Files.deleteIfExists(dossier);

        }

    }

    private static void verifierErreur(

            Path fichier) throws Exception {

        try {

            ChargementTaches.charger(fichier);

            throw new AssertionError(

                "Une erreur de chargement était attendue."

            );

        } catch (IOException e) {

            System.out.println(

                "Erreur correctement détectée : "

                + e.getMessage()

            );

        }

    }

    private static void testerEnteteInvalide(

            Path dossier) throws Exception {

        Path fichier = dossier.resolve(

            "entete-invalide.tf1"

        );

        Files.writeString(

            fichier,

            "FORMAT_INCONNU\n",

            StandardCharsets.UTF_8

        );

        verifierErreur(fichier);

    }

    private static void testerLigneCorrompue(

            Path dossier) throws Exception {

        Path fichier = dossier.resolve(

            "ligne-corrompue.tf1"

        );

        Files.writeString(

            fichier,

            "TF1\nceci-n-est-pas-une-tache\n",

            StandardCharsets.UTF_8

        );

        verifierErreur(fichier);

    }

    private static void testerFichierVide(

            Path dossier) throws Exception {

        Path fichier = dossier.resolve(

            "fichier-vide.tf1"

        );

        Files.writeString(

            fichier,

            "",

            StandardCharsets.UTF_8

        );

        verifierErreur(fichier);

    }

}
