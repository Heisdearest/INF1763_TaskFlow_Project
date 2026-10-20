package taskflow.console;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

import taskflow.modele.*;
import taskflow.service.GestionTaches;
import taskflow.stockage.SauvegardeTaches;

public final class MenuConsole {
    private boolean afficherMessageSiListeVide(
        java.util.List<Tache> liste) {

    if (liste.isEmpty()) {

        System.out.println(
            "Aucune tâche enregistrée."        );

        System.out.println(
            "Utilisez l'option 1 pour ajouter votre première tâche."        );

        return true;
    }

    return false;
    }

    private static final DateTimeFormatter DATE =
        DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    private final GestionTaches gestion;
    private final Path fichier;
    private final Scanner scanner =
        new Scanner(System.in);

    public MenuConsole(
            GestionTaches gestion,
            Path fichier) {

        this.gestion = gestion;
        this.fichier = fichier;
    }

    private String demander(String question) {

        System.out.print(question);

        if (!scanner.hasNextLine()) {
            throw new IllegalStateException(
                "Entrée interrompue."
            );
        }

        return scanner.nextLine().strip();
    }

    private int id() {
        return Integer.parseInt(
            demander("Identifiant : ")
        );
    }

    private Priorite priorite(boolean facultative) {

        String saisie = demander(
            "Priorité (Basse/Normale/Élevée)"
            + (facultative ? " [Normale]" : "")
            + " : "
        );

        if (facultative && saisie.isEmpty()) {
            return Priorite.NORMALE;
        }

        return switch (
            saisie.toLowerCase(java.util.Locale.ROOT)
        ) {
            case "basse" -> Priorite.BASSE;
            case "normale" -> Priorite.NORMALE;
            case "élevée", "elevee" -> Priorite.ELEVEE;
            default -> throw new IllegalArgumentException(
                "Priorité invalide."
            );
        };
    }

    private LocalDate date(boolean facultative) {

        String saisie = demander(
            "Échéance (AAAA-MM-JJ)"
            + (facultative ? " [vide = aucune]" : "")
            + " : "
        );

        if (facultative && saisie.isEmpty()) {
            return null;
        }

        if (!saisie.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException(
                "Format AAAA-MM-JJ attendu."
            );
        }

        try {
            return LocalDate.parse(saisie, DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Date impossible."
            );
        }
    }

    public void executer() {

        while (true) {

            System.out.println("\n=== TaskFlow ===");
            System.out.println("1 - Ajouter une tâche");
            System.out.println("2 - Afficher les tâches");
            System.out.println("3 - Terminer une tâche");
            System.out.println("4 - Modifier la priorité");
            System.out.println("5 - Ajouter/modifier une échéance");
            System.out.println("6 - Retirer une échéance");
            System.out.println("7 - Sauvegarder");
            System.out.println("0 - Quitter");

            try {

                switch (demander("Choix : ")) {

                    case "1" -> {

                        String titre = demander("Titre : ");
                        Priorite p = priorite(true);
                        LocalDate d = date(true);

                        Tache tache =
                            gestion.ajouterTache(titre, p, d);

                        System.out.println(
                            "Tâche ajoutée. Identifiant : "
                            + tache.getId()
                        );
                    }

                    case "2" -> {

                        var liste = gestion.obtenirToutesLesTaches();

                        if (!afficherMessageSiListeVide(liste)) {

                            System.out.println(
                                "\n========== LISTE DES TÂCHES =========="        );

                            for (Tache tache : liste) {

                                System.out.println(
                                    "--------------------------------------"            );

                                System.out.println(
                                    "Identifiant : " + tache.getId()
                                );

                                System.out.println(
                                    "Titre       : " + tache.getTitre()
                                );

                                System.out.println(
                                    "Priorité    : " + tache.getPriorite()
                                );

                                System.out.println(
                                    "Statut      : " + tache.getStatut()
                                );

                                System.out.println(
                                    "Échéance    : "+ (tache.getEcheance() == null? "Aucune"                    : tache.getEcheance())
                                );
                            }

                            System.out.println(
                                "--------------------------------------"        );

                            System.out.println(
                                "Nombre total de tâches : " + liste.size()
                            );
                        }
                    }

                    case "3" -> {

                        boolean modifie =
                            gestion.terminerTache(id());

                        System.out.println(
                            modifie
                                ? "Tâche terminée."
                                : "Tâche déjà terminée."
                        );
                    }

                    case "4" -> {

                        int identifiant = id();
                        Priorite p = priorite(false);

                        gestion.modifierPriorite(
                            identifiant, p
                        );

                        System.out.println(
                            "Priorité modifiée."
                        );
                    }

                   case "5" -> {
 
                        int identifiant = id();
                    
                        LocalDate nouvelleDate = date(false);
                    
                        gestion.ajouterEcheance(
                            identifiant,
                            nouvelleDate
                        );
                    
                        System.out.println(
                            "Date d'échéance ajoutée avec succès."
                        );
                    }

                    case "6" -> {
 
                        int identifiant = id();
                    
                        boolean supprimee =
                            gestion.supprimerEcheance(identifiant);
                    
                        if (supprimee) {
                    
                            System.out.println(
                                "Date d'échéance supprimée avec succès."
                            );
                    
                        } else {
                    
                            System.out.println(
                                "Cette tâche ne possède aucune échéance."
                            );
                        }
                    }

                    case "7" -> {
 
                        System.out.println(

                            "Sauvegarde des tâches en cours..."

                        );
                    
                        try {
                    
                            SauvegardeTaches.sauvegarder(

                                fichier,

                                gestion.obtenirToutesLesTaches()

                            );
                    
                            System.out.println(

                                "Les tâches ont été sauvegardées avec succès."

                            );
                    
                        } catch (IOException e) {
                    
                            System.out.println(

                                "ERREUR : la sauvegarde a échoué."

                            );
                    
                            System.out.println(

                                "Détail : " + e.getMessage()

                            );
                    
                            System.out.println(

                                "Vérifiez le dossier de destination "

                                + "et les autorisations d'écriture."

                            );
                    
                            System.out.println(

                                "Les tâches restent disponibles "

                                + "dans l'application."

                            );

                        }

                    }
                    case "8" -> {
                    
                        int identifiant = id();
                    
                        LocalDate nouvelleDate = date(false);
                    
                        gestion.modifierEcheance(
                            identifiant,
                            nouvelleDate
                        );
                    
                        System.out.println(
                            "Date d'échéance modifiée avec succès."
                        );
                    }

 
                    case "0" -> {

                        System.out.println(
                            "Au revoir. Pensez à sauvegarder."
                        );

                        return;
                    }

                    default -> System.out.println(
                        "Choix inconnu."
                    );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Erreur : " + e.getMessage()
                );

            } catch (IllegalStateException e) {

                System.out.println(
                    "Fin de saisie : " + e.getMessage()
                );

                return;
            }
        }
    }
}
