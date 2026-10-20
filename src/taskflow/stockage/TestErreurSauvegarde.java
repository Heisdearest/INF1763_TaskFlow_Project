package taskflow.stockage;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
 
public final class TestErreurSauvegarde {
 
    public static void main(String[] args)
            throws IOException {
 
        // Créer un dossier temporaire.
        Path dossier = Files.createTempDirectory(
            "test-taskflow-"
        );
 
        // Utiliser le dossier comme destination.
        // Une sauvegarde doit normalement cibler un fichier.
        Path destination = dossier;
 
        try {
 
            SauvegardeTaches.sauvegarder(
                destination,
                List.of()
            );
 
            throw new AssertionError(
                "Une erreur de sauvegarde était attendue."
            );
 
        } catch (IOException e) {
 
            System.out.println(
                "TEST RÉUSSI : erreur détectée."
            );
 
            System.out.println(
                e.getMessage()
            );
 
        } finally {
 
            Files.deleteIfExists(dossier);
        }
    }
}
