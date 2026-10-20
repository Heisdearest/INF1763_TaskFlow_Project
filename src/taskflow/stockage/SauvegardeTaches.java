package taskflow.stockage;
 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
 
import taskflow.modele.Tache;
 
public final class SauvegardeTaches {
 
    private SauvegardeTaches() {
    }
 
    public static void sauvegarder(
            Path chemin,
            List<Tache> taches) throws IOException {
 
        // Vérifier les paramètres.
        Objects.requireNonNull(
            chemin,
            "Le chemin de sauvegarde est obligatoire."
        );
 
        Objects.requireNonNull(
            taches,
            "La liste des tâches est obligatoire."
        );
 
        // Convertir le chemin en chemin absolu.
        Path destination = chemin.toAbsolutePath();
 
        // Récupérer le dossier contenant le fichier.
        Path dossier = destination.getParent();
 
        // Créer le dossier si nécessaire.
        if (dossier != null) {
            Files.createDirectories(dossier);
        }
 
        // Préparer les lignes du fichier.
        List<String> lignes = new ArrayList<>();
 
        // En-tête indiquant le format utilisé.
        lignes.add("TF1");
 
        // Convertir chaque tâche en texte.
        for (Tache tache : taches) {
 
            if (tache == null) {
                throw new IllegalArgumentException(
                    "Une tâche ne peut pas être nulle."
                );
            }
 
            lignes.add(
                FormatTaches.encoder(tache)
            );
        }
 
        // Créer un fichier temporaire.
        Path fichierTemporaire = Files.createTempFile(
            dossier,
            "taskflow-",
            ".tmp"
        );
 
        try {
 
            // Écrire les données dans le fichier temporaire.
            Files.write(
                fichierTemporaire,
                lignes,
                StandardCharsets.UTF_8
            );
 
            // Remplacer le fichier de sauvegarde.
            try {
 
                Files.move(
                    fichierTemporaire,
                    destination,
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING
                );
 
            } catch (AtomicMoveNotSupportedException e) {
 
                // Solution de repli si le déplacement
                // atomique n'est pas disponible.
                Files.move(
                    fichierTemporaire,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
                );
            }
 
        } finally {
 
            // Supprimer le fichier temporaire
            // s'il existe encore.
            Files.deleteIfExists(fichierTemporaire);
        }
    }
}