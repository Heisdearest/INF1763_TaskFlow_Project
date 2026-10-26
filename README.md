# INF1763 — Mini-projet 1 : TaskFlow

## 1. Présentation du projet

TaskFlow est une application de gestion des tâches développée en Java dans le cadre du cours INF1763.

Le projet a été réalisé par une équipe de cinq membres selon une démarche de développement collaboratif reposant sur des itérations, un backlog de récits utilisateurs et l'utilisation de Git et GitHub.

L'application fonctionne en mode console et permet à l'utilisateur de créer, consulter et gérer ses tâches, ainsi que de sauvegarder et de recharger ses données.

## 2. Fonctionnalités

TaskFlow propose les fonctionnalités suivantes :

- Ajouter une tâche.
- Afficher les tâches enregistrées.
- Afficher un message lorsque la liste est vide.
- Marquer une tâche comme terminée.
- Modifier la priorité d'une tâche.
- Ajouter une date d'échéance.
- Modifier une date d'échéance.
- Supprimer une date d'échéance.
- Sauvegarder les tâches dans un fichier.
- Recharger automatiquement les tâches au démarrage.
- Démarrer lorsqu'aucun fichier de sauvegarde n'existe.
- Détecter les fichiers de sauvegarde invalides.
- Gérer les erreurs de sauvegarde.
- Garantir l'unicité des identifiants après chargement.
- Quitter correctement l'application.

Ces fonctionnalités correspondent aux récits utilisateurs TF-01 à TF-15.

## 3. Technologies utilisées

- **Langage :** Java 17 ou version ultérieure
- **Environnement de développement :** Visual Studio Code
- **Gestion de versions :** Git
- **Plateforme collaborative :** GitHub
- **Suivi du travail :** GitHub Projects
- **Interface utilisateur :** Console

## 4. Organisation du projet

Le code source est organisé en plusieurs composantes :

- [`src/taskflow/Main.java`](./src/taskflow/Main.java) : point d'entrée de l'application.
- [`src/taskflow/modele/`](./src/taskflow/modele/) : représentation des tâches, des priorités et des statuts.
- [`src/taskflow/service/`](./src/taskflow/service/) : logique de gestion des tâches.
- [`src/taskflow/stockage/`](./src/taskflow/stockage/) : sauvegarde et chargement des données.
- [`src/taskflow/console/`](./src/taskflow/console/) : interface utilisateur en mode console.
- [`Docs/`](./Docs/) : documentation du projet.

## 5. Prérequis

Pour compiler et exécuter TaskFlow, il faut disposer :

- D'un JDK 17 ou d'une version ultérieure.
- D'un terminal PowerShell sous Windows pour utiliser les commandes ci-dessous.
- De Git pour récupérer le dépôt.

Visual Studio Code est recommandé, mais il n'est pas obligatoire pour compiler et exécuter l'application.

## 6. Installation

Cloner le dépôt :

```powershell
git clone https://github.com/Heisdearest/INF1763_TaskFlow_Project.git
```

Accéder au dossier du projet :

```powershell
Set-Location INF1763_TaskFlow_Project
```

## 7. Compilation

Les fichiers `.class` du dossier `out` sont suivis par Git dans ce dépôt. Pour éviter de les modifier pendant la compilation, placer les nouvelles classes dans un répertoire temporaire :

```powershell
$classes = Join-Path $env:TEMP ("taskflow-classes-" + [guid]::NewGuid().ToString("N"))
New-Item -ItemType Directory -Path $classes | Out-Null
$sources = Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d $classes $sources
if ($LASTEXITCODE -ne 0) { throw "La compilation a échoué." }
```

## 8. Exécution

Depuis la racine du projet, démarrer l'application avec :

```powershell
java -cp $classes taskflow.Main
```

Le menu principal de TaskFlow apparaît dans la console. L'utilisateur peut ensuite choisir une option pour effectuer les opérations proposées.

## 9. Sauvegarde des données

L'application lit et écrit les tâches dans `data\taches.tf1`, chemin relatif au répertoire courant depuis lequel elle est démarrée. Depuis la racine du projet, cela correspond à `.\data\taches.tf1`.

Le fichier utilise le format de sauvegarde `TF1`, encodé en UTF-8. Chaque ligne de tâche contient l'identifiant, le titre encodé en Base64 UTF-8, la priorité, l'échéance éventuellement vide et le statut.

Si le fichier n'existe pas, l'application démarre avec une liste vide. Si le fichier est illisible ou mal formé, le démarrage est interrompu afin de protéger les données. Une sauvegarde manuelle ou acceptée à la fermeture remplace le fichier de données; faites-en une copie avant les essais si vous souhaitez préserver son contenu.

Pour exécuter TaskFlow sans utiliser le fichier de données du dépôt, lancez-le depuis un répertoire temporaire :

```powershell
$testDir = Join-Path $env:TEMP ("taskflow-run-" + [guid]::NewGuid().ToString("N"))
New-Item -ItemType Directory -Path $testDir | Out-Null
Push-Location $testDir
try {
    java -cp $classes taskflow.Main
}
finally {
    Pop-Location
}
```

## 10. Organisation du développement

Le projet a été organisé en deux itérations :

**Itération 1 — Du 14 au 17 octobre 2026**

Développement des fonctionnalités principales de gestion des tâches et des mécanismes de sauvegarde et de chargement.

**Itération 2 — Du 18 au 21 octobre 2026**

Finalisation des fonctionnalités, gestion des cas particuliers et préparation de la validation finale.

Le backlog comprend 15 récits utilisateurs répartis entre les cinq membres de l'équipe.

Le développement collaboratif a utilisé des branches de fonctionnalités, des commits et des Pull Requests. La branche `develop` a servi à l'intégration des fonctionnalités, tandis que `main` est destinée à contenir la version finale validée.

## 11. Tests et validation

Une campagne finale de tests fonctionnels et d'intégration a été réalisée pour vérifier les récits utilisateurs TF-01 à TF-15.

**Résultats :**

- Nombre total de tests : 15.
- Tests réussis : 15.
- Tests échoués : 0.
- Taux de réussite : 100 %.
- Corrections nécessaires après les tests : 0.

Les résultats détaillés sont disponibles dans [`Docs/tests-intégration.md`](./Docs/tests-intégration.md) et [`Docs/verification.md`](./Docs/verification.md).

## 12. Documentation

Les documents du projet sont regroupés dans le dossier [`Docs/`](./Docs/) :

- [`Organisation.md`](./Docs/Organisation.md) : organisation de l'équipe et backlog.
- [`Règles de collaboration.md`](./Docs/Règles%20de%20collaboration.md) : règles de collaboration et Definition of Done.
- [`Architecture.md`](./Docs/Architecture.md) : architecture technique.
- [`iteration-1.md`](./Docs/iteration-1.md) : bilan de la première itération.
- [`iteration-2.md`](./Docs/iteration-2.md) : bilan de la deuxième itération.
- [`tests-intégration.md`](./Docs/tests-int%C3%A9gration.md) : rapport des tests.
- [`verification.md`](./Docs/verification.md) : résultats détaillés des vérifications.

## 13. Équipe de développement

Le projet a été réalisé par une équipe de cinq membres. Les responsabilités de chaque membre et les récits utilisateurs qui lui ont été attribués sont présentés dans [`Docs/Organisation.md`](./Docs/Organisation.md).

## 14. Conclusion

Le mini-projet TaskFlow a permis de mettre en pratique les principes du développement collaboratif, de la gestion des versions et de l'organisation agile du travail.

Les fonctionnalités développées ont été validées par une campagne de 15 tests fonctionnels et d'intégration, tous réussis.

L'application constitue une solution simple de gestion des tâches en mode console et illustre l'utilisation de Java, Git et GitHub dans le cadre d'un projet réalisé en équipe.
