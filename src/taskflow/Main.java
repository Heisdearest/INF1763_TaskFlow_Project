package taskflow;

import java.io.IOException;
import java.nio.file.Path;

import taskflow.console.MenuConsole;
import taskflow.service.GestionTachesImpl;
import taskflow.stockage.ChargementTaches;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {

        Path chemin = Path.of("data", "taches.tf1");

        GestionTachesImpl gestion =
            new GestionTachesImpl();

        try {

            gestion.remplacerToutes(
                ChargementTaches.charger(chemin)
            );

        } catch (IOException | IllegalArgumentException e) {

            System.err.println(
                "ERREUR : impossible de charger les tâches."
            );

            System.err.println(
                "Détail : " + e.getMessage()
            );

            System.err.println(
                "Le fichier de sauvegarde est peut-être "
                + "invalide ou corrompu."
            );

            System.err.println(
                "Aucune modification n'a été apportée "
                + "au fichier de sauvegarde."
            );

            System.err.println(
                "L'application ne démarrera pas afin "
                + "d'éviter d'écraser les données existantes."
            );

            System.exit(1);
            return;
        }

        new MenuConsole(
            gestion,
            chemin
        ).executer();
    }
}
