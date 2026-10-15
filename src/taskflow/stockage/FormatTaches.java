package taskflow.stockage;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.time.LocalDate;

import taskflow.modele.*;

final class FormatTaches {

    private FormatTaches() {
    }

    static String encoder(Tache tache) {

        String titre = Base64.getEncoder()
            .encodeToString(
                tache.getTitre().getBytes(
                    StandardCharsets.UTF_8
                )
            );

        String date = tache.getEcheance() == null
            ? ""
            : tache.getEcheance().toString();

        return tache.getId()
            + "|" + titre
            + "|" + tache.getPriorite()
            + "|" + date
            + "|" + tache.getStatut();
    }

    static Tache decoder(String ligne) {

        String[] champs = ligne.split("\\|", -1);

        if (champs.length != 5) {
            throw new IllegalArgumentException(
                "Nombre de champs incorrect."
            );
        }

        int id = Integer.parseInt(champs[0]);

        String titre = new String(
            Base64.getDecoder().decode(champs[1]),
            StandardCharsets.UTF_8
        );

        if (!Base64.getEncoder()
                .encodeToString(
                    titre.getBytes(StandardCharsets.UTF_8)
                )
                .equals(champs[1])) {

            throw new IllegalArgumentException(
                "Titre encodé invalide."
            );
        }

        Priorite priorite =
            Priorite.valueOf(champs[2]);

        LocalDate date = champs[3].isEmpty()
            ? null
            : LocalDate.parse(champs[3]);

        Statut statut =
            Statut.valueOf(champs[4]);

        return new Tache(
            id,
            titre,
            priorite,
            date,
            statut
        );
    }
}
