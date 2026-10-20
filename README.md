# INF1763_TaskFlow_Project

TaskFlow est une application Java en ligne de commande de gestion des tâches,
réalisée dans le cadre du mini-projet 1 du cours INF1763.

## Prérequis

- JDK 17 ou une version ultérieure
- PowerShell (commandes ci-dessous)

## Compilation et démarrage

À exécuter depuis la racine du dépôt :

```powershell
$sources = Get-ChildItem .\src -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d .\out $sources
java -cp .\out taskflow.Main
```

TaskFlow lit et écrit le fichier `data\taches.tf1`, relativement au répertoire
depuis lequel l'application est lancée. Si le fichier n'existe pas, l'application
démarre avec une liste vide. Un fichier illisible ou invalide interrompt le
démarrage afin d'éviter de remplacer les données existantes.

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

Après compilation, les scénarios autonomes de stockage peuvent être lancés avec :

```powershell
java -cp .\out taskflow.stockage.TestFichierAbsent
java -cp .\out taskflow.stockage.TestFichierInvalide
java -cp .\out taskflow.stockage.TestErreurSauvegarde
```

Les résultats des vérifications des récits utilisateurs sont consignés dans
[`Docs/verification.md`](./Docs/verification.md).
