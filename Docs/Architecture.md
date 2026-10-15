# Architecture Java — TaskFlow

## 1. Objectif et statut

Ce document décrit une **proposition d'architecture à valider par les cinq membres**. Il accompagne la section 7 (collaboration Git/GitHub) et la section 8.1 (code compréhensible et exécutable) de l'énoncé INF1763. Le document `docs/architecture.md` est recommandé, mais n'est pas un livrable explicitement imposé.

L'application est développée en Java 17+ en ligne de commande. Elle gère des tâches identifiées par un entier positif, avec titre obligatoire, priorité, échéance facultative et statut.

## 2. Arborescence

INF1763_TaskFlow_Project/
│
├── src/
│   └── taskflow/
│       │
│       ├── Main.java
│       │
│       ├── modele/
│       │   ├── Tache.java
│       │   ├── Priorite.java
│       │   └── Statut.java
│       │
│       ├── service/
│       │   ├── GestionTaches.java
│       │   └── GestionTachesImpl.java
│       │
│       ├── stockage/
│       │   ├── FormatTaches.java
│       │   ├── SauvegardeTaches.java
│       │   └── ChargementTaches.java
│       │
│       └── console/
│           └── MenuConsole.java
│
├── docs/
│   ├── organisation.md
│   ├── regles-collaboration.md
│   └── architecture.md
│
├── data/
│
├── README.md
└── .gitignore

## 3. Responsabilités des composants

| Composant           | Responsabilité                                   |
|---------------------|--------------------------------------------------|
| `Tache`             | Données et invariants d'une tâche                |
| `Priorite`          | `BASSE`, `NORMALE`, `ELEVEE`                     |
| `Statut`            | `A_FAIRE`, `TERMINEE`                            |
| `GestionTaches`     | Contrat commun pour les fonctionnalités          |
| `GestionTachesImpl` | Gestion en mémoire, recherche et identifiants    |
| `FormatTaches`      | Encodage/décodage du format de stockage          |
| `SauvegardeTaches`  | Écriture explicite dans un fichier local         |
| `ChargementTaches`  | Lecture et validation au démarrage               |
| `MenuConsole`       | Menus, saisies et messages utilisateur           |
| `Main`              | Initialisation, chargement, arrêt en cas d'erreur|

## 4. Modèle de données

Une tâche contient `id` (entier positif, unique), `titre` (non vide après suppression des espaces périphériques), `priorite` (par défaut `NORMALE`), `echeance` (`LocalDate` ou `null`) et `statut` (initialement `A_FAIRE`). Les titres en double sont permis. Les dates passées sont autorisées.

Les identifiants sont alloués séquentiellement et, après rechargement, reprennent à `max(id) + 1` ; les identifiants déjà enregistrés restent inchangés.

## 5. Contrat technique commun

L'interface `GestionTaches` déclare :

```java
Tache ajouterTache(String titre, Priorite priorite, LocalDate echeance);
List<Tache> obtenirToutesLesTaches();
Tache trouverParId(int id);
boolean terminerTache(int id);
void modifierPriorite(int id, Priorite priorite);
void modifierEcheance(int id, LocalDate echeance);
void remplacerToutes(List<Tache> taches);
```

Les membres doivent valider ces signatures **avant** de développer sur des branches séparées. Les erreurs de saisie sont signalées sans modifier les données déjà valides.

## 6. Sauvegarde et rechargement

Le format de fichier est un **choix de l'équipe** : l'énoncé ne prescrit aucun format. Cette proposition utilise `data/taches.tf1`, un fichier texte UTF-8 versionné au niveau du contenu (en-tête `TF1`). Chaque ligne de tâche possède cinq champs séparés par `|` :

```text
TF1
1|VmlzaXRlciBsZSBzZXJ2ZXVy|ELEVEE|2026-10-20|A_FAIRE
2|VXBkYXRlciBsZSBndWlkZQ==|NORMALE||TERMINEE
```

Champs : `id|titre_base64_UTF8|priorite|date_ou_vide|statut`. Le Base64 préserve les caractères spéciaux et les séparateurs du titre. La date est au format `AAAA-MM-JJ` ou vide. Les valeurs des énumérations correspondent aux noms Java.

Au démarrage : fichier absent → liste vide ; fichier présent mais illisible ou mal formé → message clair et arrêt sans modification du fichier. La sauvegarde est déclenchée explicitement par le menu et une erreur d'écriture ne doit jamais être annoncée comme une réussite. L'écriture utilise un fichier temporaire puis un remplacement de destination pour limiter les risques de corruption.

**Important :** reporter aussi le format, l'emplacement et les commandes de compilation dans `README.md`, comme l'exige l'énoncé.

## 7. Travail parallèle et dépendances

| Membre | Récits principaux   | Zone de code dominante           |
|--------|---------------------|----------------------------------|
| 1      | TF-01, TF-02, TF-03 | Création, consultation           |
| 2      | TF-04, TF-05, TF-15 | Statut, priorité, sortie console |
| 3      | TF-06, TF-07, TF-08 | Échéances                        |
| 4      | TF-09, TF-13, TF-14 | Sauvegarde, continuité des IDs   |
| 5      | TF-10, TF-11, TF-12 | Chargement, validation du fichier|

Cette répartition est à confirmer. Le fichier `MenuConsole.java` et la classe `GestionTachesImpl.java` sont partagés : les changements doivent être coordonnés et fusionnés fréquemment. Pour travailler en parallèle, les membres s'appuient sur l'interface commune et des données fictives de vérification.

## 8. Validation et limites

- Chaque fonctionnalité doit être vérifiée selon ses critères d'acceptation et la Definition of Done.
- Les vérifications manuelles et leurs résultats observés doivent être consignés dans `docs/verification.md`.
- La version initiale fournie est une **base technique proposée** ; l'équipe doit la relire, l'adapter et produire ses propres contributions Git et Pull Requests.
- Le programme ne sauvegarde pas automatiquement à la fermeture : l'utilisateur doit utiliser la commande de sauvegarde explicite.
- Les classes fournies n'incluent pas de tests automatisés ; les vérifications manuelles exigées restent à effectuer.

## 9. Rôle des fichiers

| Fichier                  | Fonction                                   |
| ------------------------ | ------------------------------------------ |
| `Main.java`              | Démarrer TaskFlow                          |
| `Tache.java`             | Représenter une tâche                      |
| `Priorite.java`          | Définir les priorités autorisées           |
| `Statut.java`            | Définir les statuts                        |
| `GestionTaches.java`     | Définir les méthodes communes              |
| `GestionTachesImpl.java` | Gérer les tâches en mémoire                |
| `FormatTaches.java`      | Définir le format des données enregistrées |
| `SauvegardeTaches.java`  | Sauvegarder les tâches                     |
| `ChargementTaches.java`  | Recharger les tâches                       |
| `MenuConsole.java`       | Afficher le menu et gérer les saisies      |

