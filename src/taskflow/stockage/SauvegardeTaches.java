package taskflow.stockage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import taskflow.modele.Tache;

public final class SauvegardeTaches {

    private SauvegardeTaches() {
    }

    public static void sauvegarder(
            Path chemin,
            List<Tache> taches) throws IOException {

        Path destination = chemin.toAbsolutePath();
        Path parent = destination.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lignes = new ArrayList<>();

        lignes.add("TF1");

        for (Tache tache : taches) {
            lignes.add(FormatTaches.encoder(tache));
        }

        Path temporaire = Files.createTempFile(
            parent,
            "taskflow-",
            ".tmp"
        );

        try {

            Files.write(
                temporaire,
                lignes,
                StandardCharsets.UTF_8
            );

            try {

                Files.move(
                    temporaire,
                    destination,
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING
                );

            } catch (AtomicMoveNotSupportedException e) {

                Files.move(
                    temporaire,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
                );
            }

        } finally {

            Files.deleteIfExists(temporaire);
        }
    }
}
