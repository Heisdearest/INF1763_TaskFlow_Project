# INF1763_TaskFlow_Project

TaskFlow est une application Java en ligne de commande de gestion des tâches,
réalisée dans le cadre du mini-projet 1 du cours INF1763.

## Prérequis

- JDK 17 ou une version ultérieure
- PowerShell (commandes ci-dessous)

## Compilation et démarrage

À exécuter depuis la racine du dépôt :

```powershell
$classes = Join-Path $env:TEMP ("taskflow-classes-" + [guid]::NewGuid().ToString("N"))
New-Item -ItemType Directory -Path $classes | Out-Null
$sources = Get-ChildItem .\src -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d $classes $sources
if ($LASTEXITCODE -ne 0) { throw "La compilation a échoué." }
java -cp $classes taskflow.Main
```

Les classes compilées sont placées dans un répertoire temporaire plutôt que
dans `out`, dont les fichiers `.class` sont suivis par Git dans ce dépôt.

TaskFlow lit et écrit `data\taches.tf1`, relativement au répertoire courant au
démarrage (et non relativement au dossier des classes). La commande ci-dessus
étant lancée depuis la racine, le fichier utilisé est `.\data\taches.tf1`.
Si ce fichier n'existe pas, l'application démarre avec une liste vide. Un fichier
illisible ou invalide interrompt le démarrage afin de protéger les données.
La sauvegarde manuelle ou celle acceptée à la fermeture remplace le fichier de
données de ce répertoire. Faites-en une copie avant les essais si vous souhaitez
préserver son contenu.

Pour faire un essai sans toucher au fichier du dépôt, lancez l'application depuis
un répertoire temporaire :

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

Dans cet essai isolé, le fichier de données sera créé dans
`$testDir\data\taches.tf1` si vous choisissez de sauvegarder.

## Format des données

Le fichier est encodé en UTF-8 et commence par l'en-tête `TF1`. Chaque ligne
suivante décrit une tâche selon les champs suivants :

```text
id|titre_base64_UTF8|priorite|date_ou_vide|statut
```

Le titre est encodé en Base64 UTF-8. La date suit le format `AAAA-MM-JJ` et
peut être vide. Les priorités possibles sont `BASSE`, `NORMALE` et `ELEVEE`;
les statuts sont `A_FAIRE` et `TERMINEE`.

## Programmes de vérification

Après compilation, les programmes autonomes de vérification du stockage peuvent
être exécutés depuis la racine du dépôt. Ils créent leurs propres fichiers
temporaires :

```powershell
java -cp $classes taskflow.stockage.TestFichierAbsent
java -cp $classes taskflow.stockage.TestFichierInvalide
java -cp $classes taskflow.stockage.TestErreurSauvegarde
```

Chaque programme étant autonome, vérifiez son code de sortie avant de poursuivre.
Les résultats détaillés des vérifications des récits utilisateurs sont consignés
dans [`Docs/verification.md`](./Docs/verification.md) et dans le [rapport des tests
d'intégration](./Docs/tests-intégration.md).
