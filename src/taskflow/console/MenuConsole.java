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

                        if (gestion.obtenirToutesLesTaches()
                                .isEmpty()) {

                            System.out.println(
                                "Aucune tâche enregistrée."
                            );
                        }

                        for (Tache tache :
                                gestion.obtenirToutesLesTaches()) {

                            System.out.printf(
                                "#%d | %s | %s | %s | %s%n",
                                tache.getId(),
                                tache.getTitre(),
                                tache.getPriorite(),
                                tache.getEcheance() == null
                                    ? "Sans échéance"
                                    : tache.getEcheance(),
                                tache.getStatut()
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
                        LocalDate d = date(false);

                        gestion.modifierEcheance(
                            identifiant, d
                        );

                        System.out.println(
                            "Échéance enregistrée."
                        );
                    }

                    case "6" -> {

                        gestion.modifierEcheance(id(), null);

                        System.out.println(
                            "Échéance retirée."
                        );
                    }

                    case "7" -> {

                        try {

                            SauvegardeTaches.sauvegarder(
                                fichier,
                                gestion.obtenirToutesLesTaches()
                            );

                            System.out.println(
                                "Sauvegarde réussie."
                            );

                        } catch (IOException e) {

                            System.err.println(
                                "Échec de sauvegarde : "
                                + e.getMessage()
                            );
                        }
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
