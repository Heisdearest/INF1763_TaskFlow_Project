# Organisation du projet TaskFlow
 
## 1. Présentation du projet
 
TaskFlow est une application Java en ligne de commande
permettant de gérer des tâches.
 
Le projet est réalisé dans le cadre du cours INF1763
— Techniques et outils professionnels de développement logiciel.
 
Le développement repose sur une organisation Agile,
avec un backlog de produit, deux itérations et un suivi
collaboratif dans GitHub Projects.
 
## 2. Organisation de l'équipe — Section 6.1
 
L'équipe de développement est composée de cinq membres.
 
Afin de favoriser la collaboration et le travail en parallèle,
les responsabilités de coordination sont réparties comme suit :
 
| Nom des membres                   | Responsabilité                               | 
|-----------------------------------|----------------------------------------------|
| Dedji Nonteponjesu Ayedjo Dearest | Coordination générale et suivi du backlog    |
| Nkeng Mandes Louis                | Suivi des branches Git et des Pull Requests  |
| Tazo Penawo Ducret Wilfrid        | Coordination des vérifications               |
| Ngondy Loïc Vianney               | Suivi de l'intégration et de la sauvegarde   |
| Mopi Foning Miguel Landry         | Documentation et suivi des problèmes         |
 
Tous les membres participent au développement Java,
aux vérifications et aux revues de code.
 
### 2.1 Communication
 
L'équipe utilise un canal de communication commun
pour échanger sur l'avancement du projet: Microsoft teams
 
Les décisions importantes sont documentées dans GitHub.
 
### 2.2 Prise de décision
 
Les décisions techniques et organisationnelles sont
discutées collectivement.
 
L'équipe recherche un consensus avant d'adopter
une décision ayant un impact sur plusieurs membres.
 
### 2.3 Gestion des blocages
 
Lorsqu'un membre rencontre une difficulté :
 
1. Il informe les autres membres.
2. Il décrit le problème rencontré.
3. L'équipe recherche une solution.
4. La décision retenue est documentée si nécessaire.
 
### 2.4 Collaboration
 
Chaque membre est responsable du développement
d'au moins deux récits utilisateurs.
 
Les modifications du code sont réalisées dans
des branches Git dédiées.
 
Les Pull Requests sont examinées par un autre membre
avant leur intégration dans la branche principale (develop).
 
### 2.5 Suivi du travail
 
Le suivi du travail est réalisé dans GitHub Projects.
 
Les récits passent par les états suivants :
 
À faire → En cours → À vérifier → Terminé.
 
Le tableau permet de suivre les responsabilités,
les priorités, les estimations et les itérations.
has context menu

---


| ID    | Récit utilisateur                                               | Niveau de priorité | État    |
| ----- | --------------------------------------------------------------- | ------------------ | ------- |
| TF-01 | Ajouter une tâche                                               | Haute              | Terminé |
| TF-02 | Afficher toutes les tâches                                      | Haute              | Terminé |
| TF-03 | Consulter une liste vide                                        | Haute              | Terminé |
| TF-04 | Marquer une tâche comme terminée                                | Haute              | Terminé |
| TF-05 | Modifier la priorité d'une tâche                                | Haute              | Terminé |
| TF-06 | Ajouter une échéance                                            | Haute              | Terminé |
| TF-07 | Modifier une échéance                                           | Haute              | À vérifier |
| TF-08 | Supprimer une échéance                                          | Haute              | Terminé |
| TF-09 | Sauvegarder les tâches                                          | Haute              | Terminé |
| TF-10 | Recharger les tâches au démarrage                               | Haute              | À faire |
| TF-11 | Démarrer sans fichier de données                                | Haute              | À faire |
| TF-12 | Gérer un fichier illisible ou mal formé                         | Haute              | À faire |
| TF-13 | Signaler un échec de sauvegarde                                 | Haute              | Terminé |
| TF-14 | Ajouter une tâche après rechargement sans conflit d'identifiant | Haute              | À vérifier |
| TF-15 | Quitter proprement l'application                                | Normale            | À faire |