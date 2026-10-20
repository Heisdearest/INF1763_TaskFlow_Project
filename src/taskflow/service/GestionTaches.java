package taskflow.service;

import java.time.LocalDate;
import java.util.List;

import taskflow.modele.Tache;
import taskflow.modele.Priorite;

public interface GestionTaches {

    Tache ajouterTache(
        String titre,
        Priorite priorite,
        LocalDate echeance
    );

    List<Tache> obtenirToutesLesTaches();

    Tache trouverParId(int id);

    boolean terminerTache(int id);

    boolean supprimerEcheance(int id);

    void modifierPriorite(
        int id,
        Priorite priorite
    );

    void modifierEcheance(
        int id,
        LocalDate echeance
    );

    void remplacerToutes(List<Tache> taches);

    void ajouterEcheance(int id, LocalDate echeance);
}
