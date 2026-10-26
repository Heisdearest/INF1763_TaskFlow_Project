# INF1763 — TaskFlow

## Bilan de l'itération 1

### 1. Informations générales

* **Période planifiée :** 14 au 17 octobre 2026
* **Équipe :** 5 membres
* **Branche d'intégration :** `develop`
* **Date de clôture réelle :** À compléter

### 2. Objectif de l'itération

L'objectif de cette première itération était de mettre en place les fonctionnalités principales de TaskFlow, notamment la création, l'affichage et la gestion des tâches, ainsi que les premières fonctionnalités de sauvegarde et de chargement.

### 3. Récits planifiés

| Récit | Fonctionnalité         | Responsable | État réel à la clôture |
| ----- | ---------------------- | ----------- | ---------------------- |
| TF-01 | Ajouter une tâche      | Membre 1    | Terminé                |
| TF-02 | Afficher les tâches    | Membre 1    | Terminé                |
| TF-04 | Terminer une tâche     | Membre 2    | Terminé                |
| TF-05 | Modifier une priorité  | Membre 2    | Terminé                |
| TF-06 | Ajouter une échéance   | Membre 3    | Terminé                |
| TF-07 | Modifier une échéance  | Membre 3    | Terminé                |
| TF-09 | Sauvegarder les tâches | Membre 4    | Terminé                |
| TF-10 | Recharger les tâches   | Membre 5    | Terminé                |

## 4. Travail réalisé

La première itération du mini-projet TaskFlow a été consacrée au développement des fonctionnalités principales de l'application de gestion des tâches.

Les huit récits utilisateurs planifiés ont permis de répartir les responsabilités entre les cinq membres de l'équipe.

Les travaux de développement ont porté sur les fonctionnalités suivantes :

* **TF-01 — Ajouter une tâche :** développement de la fonctionnalité permettant à l'utilisateur de créer une tâche en précisant notamment son titre et sa priorité.
* **TF-02 — Afficher les tâches :** mise en place de l'affichage des tâches enregistrées et de leurs principales informations.
* **TF-04 — Terminer une tâche :** ajout de la possibilité de modifier le statut d'une tâche pour la marquer comme terminée.
* **TF-05 — Modifier la priorité :** développement de la fonctionnalité permettant de changer la priorité d'une tâche existante.
* **TF-06 — Ajouter une échéance :** mise en place de l'attribution d'une date d'échéance à une tâche.
* **TF-07 — Modifier une échéance :** ajout de la possibilité de remplacer une date d'échéance existante.
* **TF-09 — Sauvegarder les tâches :** développement du mécanisme permettant d'enregistrer les tâches dans un fichier afin de conserver les données.
* **TF-10 — Recharger les tâches :** mise en place du chargement automatique des tâches sauvegardées au démarrage de l'application.

Sur le plan technique, ces fonctionnalités ont été développées en Java, en respectant l'organisation du projet en différentes classes et composantes.

Le travail collaboratif a été organisé à l'aide de Git et GitHub. Les membres ont utilisé des branches distinctes pour leurs récits utilisateurs et des Pull Requests pour proposer l'intégration de leurs modifications dans la branche `develop`.

Cette première itération a ainsi permis de construire les principales fonctionnalités de TaskFlow et de préparer les améliorations prévues pour la deuxième itération.

## 5. Tests et vérification

Les vérifications associées à la première itération concernent principalement la création, l'affichage, la modification, la sauvegarde et le chargement des tâches.

Les fonctionnalités suivantes ont été couvertes par la campagne finale de tests fonctionnels et d'intégration :

| Test | Récit | Vérification                      | Résultat final |
| ---- | ----- | --------------------------------- | -------------- |
| T01  | TF-01 | Ajouter une tâche                 | Réussi         |
| T02  | TF-02 | Afficher les tâches               | Réussi         |
| T04  | TF-04 | Marquer une tâche comme terminée  | Réussi         |
| T05  | TF-05 | Modifier la priorité d'une tâche  | Réussi         |
| T06  | TF-06 | Ajouter une échéance              | Réussi         |
| T07  | TF-07 | Modifier une échéance             | Réussi         |
| T09  | TF-09 | Sauvegarder les tâches            | Réussi         |
| T10  | TF-10 | Recharger les tâches au démarrage | Réussi         |

**Bilan de validation finale :**

* Nombre de fonctionnalités vérifiées : 8.
* Nombre de tests réussis : 8.
* Nombre de tests échoués : 0.
* Taux de réussite : 100 %.

Ces résultats correspondent à la campagne finale de vérification du projet et ne constituent pas, à eux seuls, une preuve que tous les tests avaient déjà été exécutés à la clôture de la première itération.

Ils confirment néanmoins que les fonctionnalités développées dans le cadre de cette itération ont été validées avec succès.

## 6. Difficultés rencontrées

Aucune anomalie n'a été signalée lors de la campagne finale de tests concernant les fonctionnalités de cette itération.

Toutefois, le développement collaboratif de TaskFlow nécessitait une attention particulière à plusieurs aspects.

Le premier concernait la coordination entre les membres. Certaines fonctionnalités utilisaient les mêmes classes Java, notamment celles responsables de la gestion des tâches et de l'interface console. Il était donc important de veiller à la compatibilité des modifications réalisées dans les différentes branches.

Le deuxième aspect concernait l'intégration des fonctionnalités. Les opérations d'ajout, d'affichage, de modification, de sauvegarde et de chargement devaient fonctionner ensemble afin de garantir un comportement cohérent de l'application.

Enfin, l'utilisation de Git et GitHub demandait une organisation rigoureuse des branches, des commits et des Pull Requests pour assurer le suivi des contributions.

Ces éléments ont constitué des points de vigilance dans la réalisation du projet, sans qu'une anomalie particulière ait été rapportée lors des tests finaux.

## 7. Ajustements effectués

L'organisation du développement a reposé sur une répartition des récits utilisateurs entre les cinq membres, chacun intervenant sur les fonctionnalités qui lui étaient attribuées.

L'utilisation de branches distinctes et de Pull Requests a permis de structurer les contributions et de faciliter leur examen avant intégration.

Une attention particulière a également été accordée à la cohérence entre les différentes composantes de l'application, notamment entre la gestion des tâches, l'interface console et les mécanismes de sauvegarde et de chargement.

Pour préparer la deuxième itération, les axes d'amélioration suivants ont été retenus comme recommandations :

* Renforcer la coordination lors des modifications de classes communes.
* Vérifier la compatibilité des fonctionnalités après leur intégration.
* Maintenir une organisation claire des branches Git.
* Accorder davantage d'attention aux cas particuliers et à la gestion des erreurs.
* Documenter les résultats des vérifications afin de faciliter la validation finale.

Aucune correction supplémentaire n'a été nécessaire à l'issue de la campagne finale de tests.

### 8. Résultat de l'itération

* **Récits planifiés :** 8
* **Récits terminés à la clôture :** 8
* **Récits reportés :** 0

## 9. Rétrospective

### 9.1. Ce qui a bien fonctionné

La première itération a permis à l'équipe de mettre en place les fonctionnalités essentielles de l'application TaskFlow. La répartition des récits utilisateurs entre les cinq membres a facilité l'organisation du travail et permis à chacun de contribuer au développement du projet.

L'utilisation de Git et GitHub a favorisé le travail collaboratif grâce à la création de branches distinctes pour les fonctionnalités et à l'utilisation des Pull Requests pour intégrer les modifications.

Cette première phase a également permis de structurer le projet Java et de développer les fonctionnalités principales de gestion des tâches, notamment leur création, leur affichage, leur modification et leur persistance.

### 9.2. Ce qui a moins bien fonctionné

La réalisation de plusieurs fonctionnalités interdépendantes a nécessité une attention particulière à la coordination entre les membres, notamment pour les classes Java partagées et les opérations de sauvegarde et de chargement.

L'équipe a également dû veiller à la cohérence des modifications effectuées dans les différentes branches afin de faciliter leur intégration dans la branche `develop`.

Ces aspects constituent des points de vigilance pour améliorer l'organisation du travail collaboratif.

### 9.3. Améliorations retenues pour l'itération 2

Pour la deuxième itération, les améliorations proposées sont les suivantes :

* Renforcer la communication entre les membres travaillant sur des fichiers communs.
* Vérifier régulièrement la compatibilité des modifications avant leur intégration.
* Maintenir les branches de développement à jour avec `develop`.
* Effectuer des tests après chaque intégration de fonctionnalité.
* Accorder une attention particulière aux cas d'erreur et aux situations exceptionnelles.

Ces mesures visent à faciliter la poursuite du développement et à garantir la qualité de l'application.

## 10. Conclusion de l'itération 1

La première itération a constitué une étape importante dans la réalisation du mini-projet TaskFlow. Elle a permis de mettre en place les principales fonctionnalités de l'application et d'établir une base technique pour la suite du développement.

La répartition des responsabilités entre les cinq membres, combinée à l'utilisation de GitHub pour le suivi des récits utilisateurs et la gestion des Pull Requests, a contribué à structurer le travail collaboratif.

Les fonctionnalités développées pendant cette phase ont préparé l'intégration des améliorations prévues pour la deuxième itération, notamment la gestion des cas particuliers, le traitement des erreurs et la finalisation du comportement de l'application.

Cette première itération a ainsi permis de progresser vers l'objectif principal du projet : développer une application de gestion des tâches fonctionnelle, structurée et collaborative.
