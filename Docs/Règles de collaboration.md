# INF1763_TaskFlow_Project
INF1763-Mini projet 1. Application Java de Gestion des tâches : TaskFlow
# Règles de collaboration — Projet TaskFlow
 
## INF1763 — Mini-projet 1
 
### 1. Objectif du document
 
Ce document présente les règles de collaboration adoptées

par l'équipe pour le développement de l'application TaskFlow.
 
Il vise à :
 
- Faciliter le travail parallèle des membres.

- Assurer la qualité du code Java.

- Réduire les conflits lors de l'intégration.

- Organiser les Pull Requests et les revues de code.

- Définir les conditions de réalisation des récits utilisateurs.

- Gérer efficacement les blocages et les dépendances.
 
---
 
## 2. Conventions de création des branches Git
 
### 2.1 Branche principale
 
La branche `main` constitue la branche principale du projet.
 
Elle contient les fonctionnalités intégrées et validées.
 
Aucun membre ne doit modifier directement le code de

l'application sur `main`.
 
Les modifications de code doivent passer par une branche

de travail et une Pull Request.
 
### 2.2 Création des branches
 
Chaque récit utilisateur doit être développé dans une

branche dédiée.
 
La convention de nommage retenue est :
 
feature/TF-XX-description-courte
 
Exemples :
 
- feature/TF-01-ajouter-tache

- feature/TF-04-terminer-tache

- feature/TF-06-ajouter-echeance

- feature/TF-09-sauvegarder

- feature/TF-10-recharger
 
Pour les corrections de bogues :
 
fix/description-courte
 
Pour les modifications documentaires :
 
docs/description-courte
 
### 2.3 Règles d'utilisation
 
- Chaque membre travaille sur sa propre branche.

- Une branche correspond normalement à un récit utilisateur.

- Les branches sont créées à partir de la dernière version

  de `develop`.

- Les membres synchronisent régulièrement leur branche.

- Les modifications doivent être enregistrées dans des commits

  clairs et descriptifs.

- Les branches ne sont fusionnées dans `develop` qu'après

  vérification et approbation.
 
### 2.4 Conventions des messages de commit
 
Les messages doivent expliquer clairement les modifications.
 
Format recommandé :
 
type: description
 
Exemples :
 
feat: ajouter la création des tâches
 
fix: corriger la validation des dates
 
test: ajouter les tests de sauvegarde
 
docs: compléter la documentation du projet
 
refactor: simplifier la gestion des priorités
 
---
 
## 3. Règles de création et de validation des Pull Requests
 
### 3.1 Création d'une Pull Request
 
Lorsqu'un membre termine le développement d'un récit,

il doit créer une Pull Request vers la branche `develop`.
 
La Pull Request doit contenir :
 
- Le numéro du récit utilisateur.

- Une description des modifications.

- Les critères d'acceptation couverts.

- Les vérifications effectuées.

- Les problèmes connus, s'il y en a.
 
### 3.2 Convention de nommage
 
Le titre d'une Pull Request doit respecter le format :
 
[TF-XX] Description de la fonctionnalité
 
Exemples :
 
[TF-01] Ajouter une nouvelle tâche
 
[TF-05] Modifier la priorité d'une tâche
 
[TF-09] Sauvegarder les tâches dans un fichier
 
### 3.3 Revue de code
 
Chaque Pull Request doit être examinée par au moins

un autre membre de l'équipe.
 
Le réviseur doit vérifier :
 
- La lisibilité du code.

- Le respect des critères d'acceptation.

- Le fonctionnement de la fonctionnalité.

- La gestion des erreurs.

- L'absence de modifications inutiles.

- L'absence de conflits non résolus.
 
### 3.4 Validation
 
Une Pull Request peut être fusionnée lorsque :
 
- Le code compile correctement.

- Les vérifications prévues ont été effectuées.

- Les critères d'acceptation sont satisfaits.

- Un autre membre a approuvé la Pull Request.

- Les problèmes bloquants sont corrigés.

- Aucun conflit Git non résolu n'est présent.
 
### 3.5 Responsabilités
 
Chaque membre doit :
 
- Développer au moins deux récits utilisateurs

  en tant que responsable principal.

- Créer les Pull Requests correspondant à son travail.

- Effectuer au moins une revue de Pull Request

  provenant d'un autre membre.
 
---
 
## 4. Definition of Done (DoD)
 
### 4.1 Définition
 
La Definition of Done représente l'ensemble des conditions

à respecter avant qu'un récit utilisateur puisse être

considéré comme terminé.
 
### 4.2 Critères obligatoires
 
Un récit utilisateur est terminé lorsque :
 
- [ ] Le code Java correspondant est développé.

- [ ] Tous les critères d'acceptation sont satisfaits.

- [ ] Le programme compile sans erreur.

- [ ] Les scénarios de vérification ont été exécutés.

- [ ] Les cas d'erreur pertinents ont été vérifiés.

- [ ] Les résultats des vérifications sont documentés.

- [ ] Le code respecte les conventions de l'équipe.

- [ ] Le développement a été réalisé dans une branche dédiée.

- [ ] Une Pull Request a été créée.

- [ ] La Pull Request a été revue par un autre membre.

- [ ] Les problèmes bloquants ont été corrigés.

- [ ] La Pull Request a été fusionnée dans `develop`.

- [ ] Le récit a été déplacé dans la colonne `Done`.
 
### 4.3 Application
 
Un récit ne doit pas être considéré comme terminé

simplement parce que son code a été écrit.
 
Il doit également être vérifié, intégré et validé.
 
---
 
## 5. Règles de vérification du code
 
### 5.1 Objectif
 
Les vérifications permettent de confirmer que les

fonctionnalités répondent aux besoins exprimés dans

les récits utilisateurs.
 
### 5.2 Vérifications obligatoires
 
Chaque membre doit vérifier les fonctionnalités

qu'il développe.
 
Les vérifications doivent comprendre :
 
1. Un scénario normal.

2. Un scénario d'erreur ou un cas particulier pertinent.

3. La conformité aux critères d'acceptation.

4. L'absence de régression sur les fonctionnalités existantes.
 
### 5.3 Exemples de vérification
 
#### TF-01 — Ajouter une tâche
 
Scénario normal :
 
- Saisir un titre valide.

- Ajouter la tâche.

- Vérifier que l'identifiant est unique.

- Vérifier que la tâche apparaît dans la liste.
 
Scénario d'erreur :
 
- Saisir un titre vide.

- Vérifier que l'ajout est refusé.

- Vérifier qu'aucune tâche invalide n'est créée.
 
#### TF-06 — Ajouter une échéance
 
Scénario normal :
 
- Sélectionner une tâche existante.

- Saisir une date valide.

- Vérifier que l'échéance est enregistrée.
 
Scénario d'erreur :
 
- Saisir une date impossible.

- Vérifier que la date est refusée.

- Vérifier que les données existantes sont conservées.
 
#### TF-09 — Sauvegarder les tâches
 
Scénario normal :
 
- Créer plusieurs tâches.

- Déclencher la sauvegarde.

- Vérifier la présence du fichier.

- Vérifier que les données sont enregistrées.
 
Scénario d'erreur :
 
- Simuler un emplacement non accessible en écriture.

- Vérifier que l'échec est signalé.
 
### 5.4 Documentation des résultats
 
Les résultats des vérifications sont consignés dans :
 
docs/verification.md
 
Chaque scénario doit préciser :
 
- Le récit concerné.

- Les étapes réalisées.

- Le résultat attendu.

- Le résultat obtenu.

- Le statut de la vérification.
 
### 5.5 Correction des anomalies
 
Lorsqu'une anomalie est détectée :
 
- Elle est signalée dans GitHub.

- Elle est corrigée sur une branche appropriée.

- La correction est vérifiée.

- La modification est intégrée après revue.
 
---
 
## 6. Gestion des blocages et des dépendances
 
### 6.1 Principe général
 
Les membres doivent pouvoir travailler en parallèle

sur les récits qui leur sont attribués.
 
Les dépendances techniques doivent être identifiées

avant le développement.
 
 
### 6.3 Gestion des dépendances
 
Les dépendances connues doivent être documentées.
 
Exemples :
 
- TF-03 dépend du mécanisme d'affichage de TF-02.

- TF-07 et TF-08 utilisent les mécanismes de gestion

  des échéances définis pour TF-06.

- TF-10 utilise le format de fichier défini pour TF-09.

- TF-14 nécessite les fonctionnalités d'ajout et de

  rechargement des tâches.
 
Les membres peuvent commencer leur travail en utilisant

des interfaces communes ou des données simulées.
 
Cependant, la vérification finale d'un récit dépendant

doit être effectuée après l'intégration des fonctionnalités

nécessaires.
 
### 6.4 Signalement d'un blocage
 
Lorsqu'un membre rencontre un blocage, il doit :
 
1. Décrire le problème rencontré.

2. Identifier le récit concerné.

3. Informer les autres membres.

4. Demander de l'aide lorsque nécessaire.

5. Documenter la solution retenue.
 
Les blocages importants doivent être visibles dans

GitHub Issues ou dans les commentaires des récits.
 
### 6.5 Résolution des conflits Git
 
En cas de conflit :
 
1. Identifier les fichiers concernés.

2. Communiquer avec les membres impliqués.

3. Comparer les modifications.

4. Résoudre le conflit sans supprimer du code utile.

5. Compiler et vérifier le programme.

6. Enregistrer la résolution.
 
### 6.6 Intégration régulière
 
L'équipe privilégie de petites Pull Requests.
 
Les membres doivent intégrer régulièrement leurs

fonctionnalités validées afin d'éviter l'accumulation

de conflits en fin d'itération.
 
---

