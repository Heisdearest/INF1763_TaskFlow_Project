# Résultats des vérifications

## TF-01 — Ajouter une tâche

**Date :** 2026-10-19
**Composants vérifiés :** `GestionTachesImpl.ajouterTache`, option d'ajout de `MenuConsole`
**Méthode :** scénarios exécutés sur le service et le menu avec un programme de vérification Java temporaire; aucune donnée du projet n'a été modifiée.

### Scénario normal — ajout d'une tâche

- **Étapes :** ajouter une tâche avec un titre entouré d'espaces, sans priorité fournie; vérifier son identifiant, son titre, sa priorité par défaut et son statut. Ajouter une deuxième tâche et vérifier l'unicité des identifiants. Réaliser aussi un ajout depuis le menu, puis afficher la tâche.
- **Résultat attendu :** le titre est nettoyé, l'identifiant est unique, la priorité par défaut est `NORMALE`, le statut initial est `A_FAIRE` et le menu confirme puis affiche la tâche ajoutée.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

### Scénario d'erreur — titre vide ou composé d'espaces

- **Étapes :** tenter un ajout avec un titre vide, puis un titre composé uniquement d'espaces; vérifier que la liste reste inchangée et que le prochain ajout reçoit l'identifiant suivant disponible.
- **Résultat attendu :** les deux ajouts sont refusés avec `IllegalArgumentException`, aucune tâche invalide n'est créée et aucun identifiant n'est consommé.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

## TF-02 — Afficher toutes les tâches

**Date :** 2026-10-19
**Composant vérifié :** option d'affichage de `MenuConsole`
**Méthode :** menu exécuté avec deux tâches préchargées en mémoire et saisie simulée; aucune sauvegarde n'a été déclenchée.

### Scénario normal — afficher plusieurs tâches

- **Étapes :** créer deux tâches avec des priorités différentes et afficher la liste depuis le menu.
- **Résultat attendu :** la liste présente les deux identifiants et les deux titres dans l'ordre d'insertion, affiche un total de deux tâches et ne signale pas que la liste est vide.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

## TF-03 — Consulter une liste vide

**Date :** 2026-10-19
**Composants vérifiés :** `GestionTachesImpl.obtenirToutesLesTaches`, option d'affichage de `MenuConsole`
**Méthode :** nouvelle gestion vide consultée dans le service et depuis le menu; aucune sauvegarde n'a été déclenchée.

### Scénario normal — consulter la liste vide

- **Étapes :** vérifier qu'une nouvelle gestion ne contient aucune tâche, puis choisir l'affichage des tâches dans le menu.
- **Résultat attendu :** la liste est vide; le menu l'indique et invite l'utilisateur à choisir l'option 1 pour ajouter une tâche, sans afficher de tableau ni de décompte.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

### Résultat global TF-01 à TF-03

Compilation de toutes les sources Java et du programme temporaire réussie. Les 22 assertions comportementales ont réussi.

## TF-04 — Marquer une tâche comme terminée

**Date :** 2026-10-19
**Composant vérifié :** `GestionTachesImpl.terminerTache`
**Méthode :** scénarios exécutés sur le service avec un programme de vérification Java temporaire; aucune donnée du projet n'a été modifiée.

### Scénario normal — terminer une tâche

- **Étapes :** créer une tâche, vérifier son statut initial, appeler `terminerTache` avec son identifiant, puis vérifier son statut et le résultat de l'appel.
- **Résultat attendu :** la tâche passe de `A_FAIRE` à `TERMINEE` et l'appel retourne `true`.
- **Résultat obtenu :** conforme; la tâche est passée à `TERMINEE` et l'appel a retourné `true`.
- **Statut :** Réussi.

### Cas particulier — terminer une tâche déjà terminée

- **Étapes :** rappeler `terminerTache` avec l'identifiant de la tâche déjà terminée.
- **Résultat attendu :** l'appel retourne `false` et le statut reste `TERMINEE`.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

### Scénario d'erreur — identifiants non positifs

- **Étapes :** appeler `terminerTache` avec `0`, puis avec `-1`; vérifier le type et le message des exceptions, ainsi que le statut d'une autre tâche.
- **Résultat attendu :** chaque appel lève `IllegalArgumentException` avec un message indiquant que l'identifiant doit être positif; aucune tâche n'est modifiée.
- **Résultat obtenu :** conforme pour les deux valeurs; l'autre tâche est restée `A_FAIRE`.
- **Statut :** Réussi.

### Scénario d'erreur — identifiant positif inconnu

- **Étapes :** appeler `terminerTache` avec `999`, qui ne correspond à aucune tâche; vérifier l'exception et le statut des tâches existantes.
- **Résultat attendu :** `IllegalArgumentException` signale l'identifiant inconnu et les tâches existantes restent inchangées.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi.

### Résultat global

Compilation des sources Java et du programme temporaire réussie. Les 11 assertions comportementales ont réussi; aucune régression n'a été observée sur le retour `false` d'une tâche déjà terminée ni sur les données existantes après une erreur.

## TF-05 — Modifier la priorité d'une tâche

**Date :** 2026-10-19
**Composant vérifié :** `GestionTachesImpl.modifierPriorite`
**Méthode :** scénarios exécutés sur le service avec un programme de vérification Java temporaire.

### Scénarios normaux et d'erreur

- **Étapes :** modifier la priorité d'une tâche existante; répéter avec la même priorité; essayer les identifiants `0`, `-1` et `999`, puis une priorité nulle; vérifier l'état de la tâche après les erreurs.
- **Résultat attendu :** une priorité valide est appliquée; les identifiants invalides/inconnus et la priorité nulle sont rejetés; les erreurs ne modifient pas la priorité courante.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 7 assertions.

## TF-06 — Ajouter une échéance

**Date :** 2026-10-19
**Composants vérifiés :** `GestionTachesImpl.ajouterEcheance`, option 5 de `MenuConsole`
**Méthode :** tests du service et du parcours console avec des tâches et fichiers temporaires.

### Scénarios normaux et d'erreur

- **Étapes :** ajouter une date valide à une tâche sans échéance par le service et par le menu; essayer une échéance dupliquée, une date nulle et un identifiant non positif.
- **Résultat attendu :** la date est enregistrée; les entrées invalides ou une seconde échéance sont refusées sans effacer la date existante; le menu confirme l'ajout.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 6 assertions (5 service et 1 console).

## TF-07 — Modifier une échéance

**Date :** 2026-10-19
**Composants vérifiés :** `GestionTachesImpl.modifierEcheance`, affichage des options de `MenuConsole`
**Méthode :** tests du service et inspection automatisée du menu imprimé.

### Scénario de service

- **Étapes :** modifier l'échéance d'une tâche qui en possède déjà une; essayer une date nulle puis modifier une tâche sans échéance; vérifier que l'état reste inchangé après refus.
- **Résultat attendu :** la nouvelle date valide est appliquée; les cas invalides sont rejetés sans modification.
- **Résultat obtenu :** conforme — 4 assertions de service.
- **Statut :** Réussi pour le service.

### Vérification d'accessibilité dans le menu

- **Étapes :** démarrer le menu, sélectionner l'option 8, saisir l'identifiant d'une tâche ayant déjà une échéance, saisir une nouvelle date, puis afficher la liste.
- **Résultat attendu :** le menu affiche une option de modification identifiable; la nouvelle date est enregistrée, confirmée et visible dans la liste.
- **Résultat obtenu :** conforme après ajout de « 8 - Modifier une échéance » au menu et renommage de l'option 5 en « Ajouter une échéance ».
- **Statut :** Réussi — 4 assertions couvrant la visibilité de l'option, le message de confirmation, l'affichage de la nouvelle date et la valeur conservée par la tâche.

## TF-08 — Supprimer une échéance

**Date :** 2026-10-19
**Composants vérifiés :** `GestionTachesImpl.supprimerEcheance`, option 6 de `MenuConsole`
**Méthode :** tests du service et du parcours console.

### Scénarios normaux et d'erreur

- **Étapes :** supprimer une échéance existante, répéter la suppression, essayer les identifiants `0` et `999`, puis vérifier que la tâche est toujours présente; exécuter aussi l'action depuis le menu.
- **Résultat attendu :** la première suppression retourne vrai et retire la date; la suivante retourne faux; les identifiants invalides/inconnus sont signalés; le menu confirme la suppression et la tâche reste présente.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 7 assertions (6 service et 1 console).

## TF-09 — Sauvegarder les tâches

**Date :** 2026-10-19
**Composants vérifiés :** `SauvegardeTaches`, `ChargementTaches`, option 7 de `MenuConsole`
**Méthode :** sauvegardes et rechargements réalisés dans des répertoires temporaires.

### Scénario normal

- **Étapes :** sauvegarder deux tâches dans un chemin imbriqué inexistant; recharger le fichier et comparer les identifiants, titres, priorités, échéances et statuts; sauvegarder ensuite une tâche seule; vérifier également l'action de sauvegarde du menu.
- **Résultat attendu :** les répertoires et le fichier sont créés, les données TF1 sont fidèles et une sauvegarde suivante remplace l'ancien contenu.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 6 assertions (5 stockage et 1 console).

## TF-13 — Signaler un échec de sauvegarde

**Date :** 2026-10-19
**Composants vérifiés :** `SauvegardeTaches`, option 7 de `MenuConsole`
**Méthode :** destination configurée comme un répertoire existant afin de provoquer un échec d'écriture; scénario également exécuté via le menu.

### Scénario d'erreur

- **Étapes :** tenter une sauvegarde vers le répertoire; vérifier le signalement d'erreur, l'absence de message de succès, puis ajouter une tâche dans la même session.
- **Résultat attendu :** une `IOException` est remontée par le stockage; le menu indique l'échec sans annoncer un succès; les données en mémoire restent disponibles et le menu continue.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 5 assertions.

## TF-14 — Ajouter une tâche après rechargement sans conflit d'identifiant

**Date :** 2026-10-19
**Composants vérifiés :** `ChargementTaches`, `GestionTachesImpl.remplacerToutes`, `GestionTachesImpl.ajouterTache`
**Méthode :** sauvegarder deux tâches dont le plus grand identifiant vaut 8, charger le fichier dans une nouvelle gestion, puis créer une tâche.

### Scénario normal

- **Étapes :** recharger les tâches et ajouter une nouvelle tâche; vérifier l'identifiant attribué et la conservation des tâches rechargées.
- **Résultat attendu :** la nouvelle tâche reçoit l'identifiant 9 et aucune tâche existante n'est remplacée.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 2 assertions.

### Réserve de revue

La PR #26 est fusionnée, mais sa description porte sur des « données simulées » et non sur le récit TF-14 du backlog; aucune revue d'un autre membre n'est enregistrée dans GitHub pour cette PR. La vérification fonctionnelle réussit, mais les critères de revue et de traçabilité doivent être clarifiés avant de déclarer le récit terminé.

### Résultat global TF-05 à TF-14

Compilation de toutes les sources Java et des programmes de vérification réussie. 28 assertions ont été exécutées pour les scénarios TF-06 à TF-14, puis 3 assertions supplémentaires ont validé les parcours console TF-06, TF-08 et TF-09. Après correction du menu TF-07, 4 assertions supplémentaires ont validé le parcours de modification d'échéance.

## TF-15 — Quitter proprement l'application

**Date :** 2026-10-19
**Composant vérifié :** option 0 de `MenuConsole`
**Méthode :** parcours console testé avec saisie simulée et destinations temporaires.

### Scénarios normaux et d'erreur

- **Étapes :** quitter sans sauvegarder; accepter une sauvegarde avant de quitter; provoquer une erreur de sauvegarde, vérifier que la fermeture est annulée, puis choisir de quitter sans sauvegarder.
- **Résultat attendu :** le choix explicite « non » quitte sans créer de fichier; « oui » sauvegarde avant de quitter; un échec de sauvegarde empêche la fermeture et permet à l'utilisateur de choisir à nouveau.
- **Résultat obtenu :** conforme.
- **Statut :** Réussi — 3 assertions.

## TF-10 — Recharger les tâches au démarrage

**Date :** 2026-10-19
**Composants vérifiés :** `Main`, `ChargementTaches`, `GestionTachesImpl.remplacerToutes`

### Scénario normal — fichier valide

- **Étapes :** créer un fichier TF1 valide dans un répertoire temporaire, lancer l'application depuis ce répertoire, afficher les tâches, puis quitter sans sauvegarder.
- **Résultat attendu :** l'application démarre et affiche la tâche précédemment enregistrée.
- **Résultat obtenu :** conforme; code de sortie `0` et tâche affichée.
- **Statut :** Réussi.

## TF-11 — Démarrer sans fichier de données

**Date :** 2026-10-19
**Composants vérifiés :** `Main`, `ChargementTaches`, `TestFichierAbsent`

### Scénario normal — première utilisation

- **Étapes :** lancer l'application depuis un répertoire temporaire sans `data/taches.tf1`, afficher la liste, quitter sans sauvegarder; exécuter aussi `TestFichierAbsent`.
- **Résultat attendu :** le chargement retourne une liste vide, l'application démarre et le menu indique qu'aucune tâche n'est enregistrée.
- **Résultat obtenu :** conforme; code de sortie `0`, message de liste vide affiché et test autonome réussi.
- **Statut :** Réussi.

## TF-12 — Gérer un fichier illisible ou mal formé

**Date :** 2026-10-19
**Composants vérifiés :** `Main`, `ChargementTaches`, `FormatTaches`, `TestFichierInvalide`

### Scénarios d'erreur

- **Étapes :** vérifier un en-tête inconnu, une ligne de tâche corrompue, un fichier vide, des identifiants dupliqués, un fichier encodé en UTF-8 invalide et un lancement avec des données corrompues.
- **Résultat attendu :** chaque format invalide est rejeté; au démarrage, l'application signale l'erreur, retourne un code de sortie non nul et ne modifie pas le fichier.
- **Résultat obtenu :** conforme; les erreurs de chargement sont détectées, le démarrage invalide retourne le code `1` et les octets des fichiers d'origine restent identiques. Le fichier UTF-8 invalide est également rejeté. `TestFichierInvalide` a réussi ses trois cas.
- **Statut :** Réussi.

## Revalidation intégrée TF-01 à TF-15

**Date :** 2026-10-19
**Méthode :** compilation de toutes les sources Java dans un répertoire temporaire, puis exécution d'un banc d'intégration temporaire couvrant les scénarios normaux, erreurs pertinentes, parcours console, stockage et démarrage. Aucun fichier généré n'a été ajouté au dépôt.

- Compilation : réussie avec `javac -encoding UTF-8`.
- Banc d'intégration : **37 assertions réussies** pour TF-01 à TF-15. Les scénarios comprennent notamment les validations des entrées, la modification et la suppression d'échéances, les opérations de stockage, le chargement au démarrage, l'absence de fichier, la préservation d'un fichier corrompu, la continuité des identifiants et les deux choix de sauvegarde à la fermeture.
- Programmes autonomes : `TestFichierAbsent`, `TestFichierInvalide` (en-tête, ligne corrompue et fichier vide) et `TestErreurSauvegarde` : tous réussis.
- Revue documentaire des PR #30 à #33 : elles sont fusionnées dans `develop` et ont reçu des commentaires de membres de l'équipe. TF-07 a également fait l'objet de la revue visuelle demandée.
- **Suivi DoD :** TF-14 est indiqué « Terminé » dans le backlog à la demande de l'équipe. Les assertions fonctionnelles sont réussies et la PR #26 est fusionnée; toutefois, cette PR porte sur des données simulées et aucune revue traçable spécifiquement liée à TF-14 n'a pu être confirmée.
