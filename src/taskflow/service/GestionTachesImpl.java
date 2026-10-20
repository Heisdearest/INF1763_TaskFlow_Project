package taskflow.service;

import java.time.LocalDate;
import java.util.*;

import taskflow.modele.*;

public final class GestionTachesImpl implements GestionTaches {

    private final Map<Integer, Tache> taches =
            new LinkedHashMap<>();

    private int prochainId = 1;

    @Override
    public Tache ajouterTache(
            String titre,
            Priorite priorite,
            LocalDate echeance) {

        // Vérifier que le titre existe.
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException(
                "Le titre de la tâche est obligatoire."
            );
        }

        // On Supprime les espaces inutiles.
        String titreNettoye = titre.strip();

        // On Vérifie la longueur du titre.
        if (titreNettoye.length() > 150) {
            throw new IllegalArgumentException(
                "Le titre ne doit pas dépasser 150 caractères."
            );
        }

        // Définir une priorité par défaut.
        Priorite prioriteFinale =
            priorite == null
                ? Priorite.NORMALE
                : priorite;

        // Vérifier la disponibilité des identifiants.
        if (prochainId <= 0 ||
            prochainId == Integer.MAX_VALUE) {

            throw new IllegalStateException(
                "Aucun nouvel identifiant disponible."
            );
        }

        // Créer la tâche.
        Tache nouvelleTache = new Tache(
            prochainId,
            titreNettoye,
            prioriteFinale,
            echeance,
            Statut.A_FAIRE
        );

        // Ajouter la tâche à la collection.
        taches.put(
            nouvelleTache.getId(),
            nouvelleTache
        );

        // Préparer le prochain identifiant.
        prochainId++;

        return nouvelleTache;
    }


    @Override
    public List<Tache> obtenirToutesLesTaches() {

        return Collections.unmodifiableList(
            new ArrayList<>(taches.values())
        );
    }

    @Override
    public Tache trouverParId(int id) {

        Tache tache = taches.get(id);

        if (tache == null) {
            throw new IllegalArgumentException(
                "Identifiant inconnu : " + id
            );
        }

        return tache;
    }

    @Override
    public boolean terminerTache(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "L'identifiant doit être un entier positif."
            );
        }

        Tache tache = trouverParId(id);

        if (tache.getStatut() == Statut.TERMINEE) {
            return false;
        }

        tache.terminer();

        return true;
    }

    @Override
    public void modifierPriorite(
            int id,
            Priorite priorite) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "L'identifiant doit être un entier positif."
            );
        }

        if (priorite == null) {
            throw new IllegalArgumentException(
                "La priorité est obligatoire."
            );
        }

        Tache tache = trouverParId(id);

        if (tache.getPriorite() == priorite) {
            return;
        }

        tache.setPriorite(priorite);
    }

    @Override
    public void modifierEcheance(
            int id,
            LocalDate echeance) {

        trouverParId(id).setEcheance(echeance);
    }

    @Override

    public void remplacerToutes(List<Tache> donnees) {
    
        if (donnees == null) {

            throw new IllegalArgumentException(

                "La liste des tâches ne peut pas être nulle."

            );

        }
    
        Map<Integer, Tache> nouvellesTaches =

            new LinkedHashMap<>();
    
        int plusGrandId = 0;
    
        for (Tache tache : donnees) {
    
            if (tache == null) {

                throw new IllegalArgumentException(

                    "Une tâche chargée ne peut pas être nulle."

                );

            }
    
            int id = tache.getId();
    
            if (id <= 0) {

                throw new IllegalArgumentException(

                    "Identifiant invalide : " + id

                );

            }
    
            if (nouvellesTaches.containsKey(id)) {

                throw new IllegalArgumentException(

                    "Identifiant dupliqué : " + id

                );

            }
    
            nouvellesTaches.put(id, tache);
    
            if (id > plusGrandId) {

                plusGrandId = id;

            }

        }
    
        if (plusGrandId == Integer.MAX_VALUE) {

            throw new IllegalArgumentException(

                "Impossible de générer un nouvel identifiant."

            );

        }
    
        // Remplacer les données seulement après validation.

        taches.clear();
    
        taches.putAll(nouvellesTaches);
    
        // Préparer un identifiant supérieur au maximum chargé.

        prochainId = plusGrandId + 1;

    }
}
