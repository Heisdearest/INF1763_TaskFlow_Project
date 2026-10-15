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
                "Démarrage interrompu : "
                + e.getMessage()
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