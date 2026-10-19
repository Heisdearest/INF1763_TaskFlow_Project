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
