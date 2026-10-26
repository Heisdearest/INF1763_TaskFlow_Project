# INF1763 — TaskFlow

## Bilan de l'itération 2

### 1. Informations générales

* **Période planifiée :** 18 au 21 octobre 2026
* **Équipe :** 5 membres
* **Branche d'intégration :** `develop`
* **Date de clôture réelle :** À compléter

### 2. Objectif de l'itération

L'objectif de la deuxième itération était de compléter les fonctionnalités de TaskFlow, d'améliorer la gestion des erreurs, de vérifier la persistance des données et de préparer l'application pour sa validation finale.

### 3. Récits planifiés

| Récit | Fonctionnalité                    | Responsable | État réel à la clôture |
| ----- | --------------------------------- | ----------- | ---------------------- |
| TF-03 | Afficher une liste vide           | Membre 1    | Terminé            |
| TF-08 | Supprimer une échéance            | Membre 3    | Terminé            |
| TF-11 | Démarrer sans sauvegarde          | Membre 5    | Terminé            |
| TF-12 | Gérer un fichier invalide         | Membre 5    | Terminé            |
| TF-13 | Gérer les erreurs de sauvegarde   | Membre 4    | Terminé            |
| TF-14 | Garantir des identifiants uniques | Membre 4    | Terminé            |
| TF-15 | Quitter correctement              | Membre 2    | Terminé            |

## 4. Travail réalisé

La deuxième itération du mini-projet TaskFlow a été consacrée à la finalisation des fonctionnalités de l'application, à l'amélioration de sa fiabilité et à la gestion des situations particulières.

Sept récits utilisateurs ont été planifiés et répartis entre les membres de l'équipe.

Les travaux ont porté sur les fonctionnalités suivantes :

* **TF-03 — Afficher une liste vide :** mise en place d'un message permettant d'informer l'utilisateur lorsqu'aucune tâche n'est enregistrée.
* **TF-08 — Supprimer une échéance :** développement de la fonctionnalité permettant de retirer la date d'échéance d'une tâche sans supprimer celle-ci.
* **TF-11 — Démarrer sans sauvegarde :** vérification du comportement de l'application lorsqu'aucun fichier de sauvegarde n'existe, notamment lors de la première utilisation.
* **TF-12 — Gérer un fichier invalide :** amélioration du chargement afin de détecter les fichiers de sauvegarde incorrects ou corrompus et d'éviter l'utilisation de données invalides.
* **TF-13 — Gérer les erreurs de sauvegarde :** mise en place d'un traitement permettant de détecter et de signaler les échecs d'enregistrement des données.
* **TF-14 — Garantir des identifiants uniques :** vérification du mécanisme d'attribution des identifiants afin d'éviter les doublons après le rechargement des tâches.
* **TF-15 — Quitter correctement :** finalisation du comportement de fermeture de l'application, notamment concernant la sauvegarde des données avant de quitter.

Ces travaux ont permis de compléter les fonctionnalités prévues pour TaskFlow et de renforcer la cohérence entre les différentes composantes du logiciel.

Comme lors de la première itération, les contributions ont été organisées à l'aide de branches Git et de Pull Requests.

À l'issue du développement, l'application disposait des fonctionnalités nécessaires à la réalisation de la campagne finale de tests fonctionnels et d'intégration.

## 5. Tests et vérification

La deuxième itération a porté principalement sur les cas particuliers, la gestion des erreurs et la finalisation du comportement de l'application.

Les sept fonctionnalités suivantes ont été vérifiées lors de la campagne finale de tests :

| Test | Récit | Vérification                                       | Résultat final |
| ---- | ----- | -------------------------------------------------- | -------------- |
| T03  | TF-03 | Afficher une liste vide                            | Réussi         |
| T08  | TF-08 | Supprimer une échéance                             | Réussi         |
| T11  | TF-11 | Démarrer sans fichier de sauvegarde                | Réussi         |
| T12  | TF-12 | Détecter un fichier invalide                       | Réussi         |
| T13  | TF-13 | Gérer une erreur de sauvegarde                     | Réussi         |
| T14  | TF-14 | Garantir des identifiants uniques après chargement | Réussi         |
| T15  | TF-15 | Quitter correctement l'application                 | Réussi         |

**Bilan de validation finale :**

* Nombre de fonctionnalités vérifiées : 7.
* Nombre de tests réussis : 7.
* Nombre de tests échoués : 0.
* Taux de réussite : 100 %.

Aucune anomalie n'a été détectée pendant cette campagne de vérification et aucune correction supplémentaire n'a été nécessaire.

En considérant l'ensemble du projet, les résultats sont les suivants :

* Nombre total de récits utilisateurs : 15.
* Nombre total de tests fonctionnels et d'intégration : 15.
* Tests réussis : 15.
* Tests échoués : 0.
* Taux global de réussite : 100 %.

Ces résultats confirment que les fonctionnalités vérifiées répondent aux comportements attendus dans l'environnement de test utilisé.

Le détail des vérifications est présenté dans le document `Docs/tests-integration.md`.

## 6. Difficultés rencontrées

Aucune anomalie n'a été relevée lors des tests finaux de la deuxième itération.

Néanmoins, les fonctionnalités développées pendant cette phase nécessitaient une attention particulière, notamment en raison de leur rôle dans la fiabilité de l'application.

La gestion des fichiers de sauvegarde constituait un point important. L'application devait distinguer un fichier absent, correspondant à une première utilisation, d'un fichier invalide ou corrompu.

La gestion des erreurs de sauvegarde devait également permettre d'informer correctement l'utilisateur lorsqu'un enregistrement échouait, sans afficher de confirmation trompeuse.

Un autre aspect concernait l'unicité des identifiants. Après le chargement des tâches enregistrées, le système devait continuer à attribuer des identifiants uniques aux nouvelles tâches.

Enfin, la coordination entre les fonctionnalités de sauvegarde, de chargement et de fermeture exigeait une vérification globale du comportement de l'application.

Ces éléments représentaient des risques fonctionnels à maîtriser. Toutefois, les tests finaux n'ont révélé aucun dysfonctionnement nécessitant une correction.

## 7. Ajustements effectués

La deuxième itération a permis de compléter les mécanismes de gestion des cas particuliers et de renforcer la fiabilité de TaskFlow.

Les travaux ont notamment porté sur :

* L'affichage d'un message approprié lorsque la liste des tâches est vide.
* La suppression des échéances existantes.
* Le traitement de l'absence de fichier de sauvegarde.
* La détection des fichiers invalides ou corrompus.
* La gestion des erreurs rencontrées pendant la sauvegarde.
* La vérification de l'unicité des identifiants après chargement.
* La gestion de la fermeture de l'application.

Ces éléments correspondent aux fonctionnalités prévues dans les récits utilisateurs de la deuxième itération.

Une campagne finale de tests fonctionnels et d'intégration a ensuite permis de vérifier l'ensemble des 15 récits utilisateurs du projet.

Tous les tests ont été exécutés avec succès, sans échec ni correction supplémentaire.

Par conséquent, aucun ajustement correctif n'a été nécessaire après cette phase de vérification.

Les résultats obtenus ont permis de considérer le développement fonctionnel de TaskFlow comme terminé et de passer à la préparation des livrables finaux.


### 8. Résultat de l'itération

* **Récits planifiés :** 7
* **Récits terminés à la clôture :** 7
* **Récits reportés :** 0

## 9. Rétrospective

### 9.1. Ce qui a bien fonctionné

La deuxième itération a permis de compléter les fonctionnalités prévues pour l'application TaskFlow et de renforcer sa fiabilité.

Les travaux ont notamment porté sur la gestion des listes vides, la suppression des échéances, le démarrage sans fichier de sauvegarde, la détection des fichiers invalides, la gestion des erreurs de sauvegarde, l'unicité des identifiants et la fermeture de l'application.

La collaboration entre les cinq membres s'est poursuivie à travers l'utilisation des branches Git, des Pull Requests et de la branche d'intégration `develop`.

La phase finale de vérification a constitué un résultat particulièrement positif. Les **15 tests fonctionnels et d'intégration ont été exécutés avec succès, sans aucun échec ni aucune correction nécessaire**.

Ces résultats ont permis de confirmer le bon fonctionnement des fonctionnalités testées et de renforcer la confiance de l'équipe dans la qualité du logiciel.

### 9.2. Ce qui a moins bien fonctionné

Aucune anomalie n'a été relevée lors de la campagne finale des tests fonctionnels et d'intégration.

Toutefois, le projet a mis en évidence l'importance de maintenir une bonne coordination entre les membres, particulièrement lorsque plusieurs fonctionnalités utilisent les mêmes classes ou dépendent du même mécanisme de sauvegarde.

La vérification des cas particuliers et la documentation des résultats représentent également des activités auxquelles il convient d'accorder suffisamment de temps dans les futurs projets.

### 9.3. Améliorations retenues pour les prochains projets

Pour les prochains projets collaboratifs, l'équipe pourrait :

* Mettre en place des tests automatisés dès le début du développement.
* Renforcer les revues de code entre les membres.
* Documenter systématiquement les résultats des tests.
* Prévoir davantage de tests d'intégration au cours de chaque itération.
* Continuer à utiliser GitHub Projects pour suivre l'avancement des tâches et les responsabilités.
* Améliorer la coordination lors des modifications de fichiers communs.

Ces améliorations permettraient de maintenir un bon niveau de qualité tout en facilitant la collaboration et le suivi des projets.

## 10. Conclusion de l'itération 2

La deuxième itération a permis de compléter les fonctionnalités de l'application TaskFlow et de préparer sa livraison finale.

Les développements réalisés ont renforcé la gestion des tâches, la persistance des données et le traitement des situations particulières, notamment les fichiers absents ou invalides et les erreurs de sauvegarde.

La campagne finale de vérification s'est conclue par un résultat satisfaisant : **les 15 tests correspondant aux récits utilisateurs TF-01 à TF-15 ont été réussis, sans échec ni correction supplémentaire**.

Ces résultats démontrent que les fonctionnalités vérifiées répondent aux comportements attendus dans l'environnement de test utilisé.

Sur le plan collaboratif, le projet a permis aux cinq membres de participer au développement d'une application Java en utilisant les pratiques de gestion de versions et de collaboration prévues dans le cadre du cours.

**En conclusion, la deuxième itération marque l'achèvement du développement fonctionnel prévu pour TaskFlow.** L'application est prête à passer à la dernière étape du mini-projet, consacrée à la vérification des documents, à la préparation de la version finale et à la remise des livrables.
