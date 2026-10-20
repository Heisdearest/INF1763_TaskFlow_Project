package taskflow.modele;

import java.time.LocalDate;
import java.util.Objects;

public final class Tache {

    private final int id;
    private final String titre;
    private Priorite priorite;
    private LocalDate echeance;
    private Statut statut;

    public Tache(int id, String titre, Priorite priorite,
                 LocalDate echeance, Statut statut) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "L'identifiant doit être positif."
            );
        }

        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException(
                "Le titre est obligatoire."
            );
        }

        this.id = id;
        this.titre = titre.strip();
        this.priorite = Objects.requireNonNull(priorite);
        this.echeance = echeance;
        this.statut = Objects.requireNonNull(statut);
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = Objects.requireNonNull(priorite);
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public void terminer() {
        this.statut = Statut.TERMINEE;
    }

    public void ajouterEcheance(LocalDate nouvelleEcheance) {
 
        if (nouvelleEcheance == null) {
            throw new IllegalArgumentException(
                "La date d'échéance est obligatoire."
            );
        }
    
        if (this.echeance != null) {
            throw new IllegalStateException(
                "Cette tâche possède déjà une échéance."
            );
        }
    
        this.echeance = nouvelleEcheance;
    }

    public void modifierEcheance(LocalDate nouvelleEcheance) {
    
        if (nouvelleEcheance == null) {
            throw new IllegalArgumentException(
                "La nouvelle échéance est obligatoire."
            );
        }
    
        if (this.echeance == null) {
            throw new IllegalStateException(
                "Cette tâche ne possède aucune échéance à modifier."
            );
        }
    
        if (this.echeance.equals(nouvelleEcheance)) {
            return;
        }
    
        this.echeance = nouvelleEcheance;
    }


    public boolean supprimerEcheance() {
    
        if (this.echeance == null) {
            return false;
        }
    
        this.echeance = null;
    
        return true;
    }
}
