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

        if (prochainId <= 0 ||
            prochainId == Integer.MAX_VALUE) {

            throw new IllegalStateException(
                "Plus d'identifiants disponibles."
            );
        }

        Tache tache = new Tache(
            prochainId,
            titre,
            priorite == null ? Priorite.NORMALE : priorite,
            echeance,
            Statut.A_FAIRE
        );

        taches.put(tache.getId(), tache);
        prochainId++;

        return tache;
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

        Objects.requireNonNull(
            priorite,
            "Priorité obligatoire"
        );

        trouverParId(id).setPriorite(priorite);
    }

    @Override
    public void modifierEcheance(
            int id,
            LocalDate echeance) {

        trouverParId(id).setEcheance(echeance);
    }

    @Override
    public void remplacerToutes(List<Tache> donnees) {

        Map<Integer, Tache> nouvelles =
                new LinkedHashMap<>();

        int max = 0;

        for (Tache tache : donnees) {

            if (nouvelles.putIfAbsent(
                    tache.getId(), tache) != null) {

                throw new IllegalArgumentException(
                    "Identifiant dupliqué : " + tache.getId()
                );
            }

            max = Math.max(max, tache.getId());
        }

        if (max == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                "Identifiant maximal non pris en charge."
            );
        }

        taches.clear();
        taches.putAll(nouvelles);

        prochainId = max + 1;
    }
}
