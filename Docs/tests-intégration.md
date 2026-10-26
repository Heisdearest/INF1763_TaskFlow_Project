# INF1763 — TaskFlow
## Rapport des tests d'intégration

1. Introduction

Ce document présente les tests fonctionnels et les tests d'intégration de l'application TaskFlow, développée en Java par une équipe de cinq membres.

Les tests visent à vérifier le fonctionnement des récits utilisateurs TF-01 à TF-15 après l'intégration des contributions dans la branche develop.

2. Environnement de test
. Application : TaskFlow
. Langage : Java
. Interface : Console
. Environnement de développement : Visual Studio Code
. Gestion de versions : Git et GitHub
. Branche testée : develop
. Date des tests : À compléter
. Version de Java : À compléter avec java -version
. Révision Git testée : À compléter avec git rev-parse --short HEAD

Les tests sont réalisés dans des dossiers isolés afin de préserver les données principales de l'application.

3. Objectifs des tests

Les tests ont pour objectifs de vérifier :

. La création et l'affichage des tâches.
. La modification des statuts et des priorités.
. La gestion des dates d'échéance.
. La sauvegarde et le chargement des données.
. La gestion des fichiers absents ou invalides.
. La gestion des erreurs de sauvegarde.
. L'unicité des identifiants.
. La fermeture correcte de l'application.

4. Résultats des tests

| ID  | Récit | Résultat | Observation |
|-----|-------|----------|-------------|
| T01 | TF-01 | Réussi   | Création d'une tâche avec identifiant unique ; titre vide refusé. |
| T02 | TF-02 | Réussi   | Affichage correct des informations de la tâche. |
| T03 | TF-03 | Réussi   | Un message explicite apparaît lorsque la liste est vide. |
| T04 | TF-04 | Réussi   | Le statut devient TERMINEE ; une seconde clôture est correctement gérée. |
| T05 | TF-05 | Réussi   | La priorité est modifiée ; une valeur invalide est refusée. |
| T06 | TF-06 | Réussi   | L'échéance est ajoutée et une seconde attribution est refusée. |
| T07 | TF-07 | Réussi   | L'ancienne échéance est remplacée par la nouvelle. |
| T08 | TF-08 | Réussi   | L'échéance est supprimée sans supprimer la tâche. |
| T09 | TF-09 | Réussi   | Le fichier de sauvegarde est créé avec les tâches enregistrées. |
| T10 | TF-10 | Réussi   | Les tâches sauvegardées sont automatiquement rechargées au démarrage. |
| T11 | TF-11 | Réussi   |L'application démarre correctement sans fichier de sauvegarde. |
| T12 | TF-12 | Réussi   |Le fichier corrompu est détecté et n'est pas écrasé. |
| T13 | TF-13 | Réussi   |Une erreur de sauvegarde est détectée et signalée |
| T14 | TF-14 | Réussi   |Le nouvel identifiant est supérieur au maximum des identifiants chargés. |
| T15 | TF-15 | Réussi   |La fermeture avec sauvegarde, sans sauvegarde et avec réponse invalide fonctionne correctement. |

5. Anomalies détectées

Aucune anomalies détectées.

7. Synthèse des résultats
. Nombre total de tests : 15
. Tests réussis : 15
. Tests échoués : 0
. Tests non exécutés : 0

8. Conclusion

Les tests fonctionnels et d'intégration réalisés sur l'application TaskFlow ont permis de vérifier le bon fonctionnement des fonctionnalités développées par les cinq membres de l'équipe.

Au total, **15 tests correspondant aux récits utilisateurs TF-01 à TF-15 ont été exécutés avec succès**, soit un taux de réussite de **100 %**. Aucun échec ni aucune anomalie n'ont été constatés durant cette phase de vérification. Par conséquent, aucune correction supplémentaire n'a été nécessaire.

Les tests ont notamment permis de valider la création et l'affichage des tâches, la modification des priorités et des statuts, la gestion des échéances, la sauvegarde et le chargement des données, ainsi que la gestion des erreurs et la fermeture de l'application.

Ces résultats confirment que les fonctionnalités testées répondent aux comportements attendus et fonctionnent correctement dans l'environnement de vérification utilisé.

**En conclusion, TaskFlow est considéré comme fonctionnel et prêt pour la préparation de sa livraison finale**, sous réserve de la vérification des derniers éléments documentaires et des exigences de remise du projet.
