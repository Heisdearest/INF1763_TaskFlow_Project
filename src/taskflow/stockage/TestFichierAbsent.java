package taskflow.stockage;

import java.nio.file.Files;

import java.nio.file.Path;

import java.util.List;

import taskflow.modele.Tache;

public final class TestFichierAbsent {

    public static void main(String[] args)

            throws Exception {

        Path dossier = Files.createTempDirectory(

            "taskflow-test-absent-"

        );

        try {

            Path fichier = dossier.resolve(

                "sauvegarde-inexistante.tf1"

            );

            // Vérifier que le fichier n'existe pas.

            if (Files.exists(fichier)) {

                throw new AssertionError(

                    "Le fichier ne devrait pas exister."

                );

            }

            // Tenter le chargement.

            List<Tache> taches =

                ChargementTaches.charger(fichier);

            // Vérifier que la liste est vide.

            if (!taches.isEmpty()) {

                throw new AssertionError(

                    "La liste devrait être vide."

                );

            }

            System.out.println(

                "TEST TF-11 RÉUSSI : "

                + "chargement sans fichier."

            );

        } finally {

            Files.deleteIfExists(dossier);

        }

    }

}
