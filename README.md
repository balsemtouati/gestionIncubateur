# GestionIncubateur

Projet Java de gestion d'un incubateur (interface JavaFX/NetBeans).

## Description
Ce dépôt contient une application de gestion d'incubateur développée en Java (interfaces FXML/JavaFX). Le projet semble organisé pour NetBeans et utilise un fichier `build.xml` (Ant) pour la compilation.

## Structure principale
- `src/` : code source Java et FXML.
- `build/` : artefacts de build (classes compilées, fichiers générés).
- `nbproject/` et `build.xml` : fichiers de configuration NetBeans / Ant.

## Prérequis
- Java JDK 8 ou supérieur installé
- Ant (optionnel, NetBeans inclut un runner)
- NetBeans (recommandé) ou un IDE compatible JavaFX

## Compilation et exécution
Avec NetBeans
- Ouvrir le projet dans NetBeans (`File > Open Project` et sélectionner le dossier du projet).
- Lancer le projet via le bouton Run.

Avec Ant / ligne de commande
- Depuis la racine du projet (là où se trouve `build.xml`) :

```powershell
ant
```

- Après compilation les classes sont généralement dans `build/classes`. Pour lancer l'application depuis la ligne de commande (exemple) :

```powershell
cd GestionIncubateur
java -cp build/classes gestionincubateur.GestionIncubateur
```

(Remplacez `gestionincubateur.GestionIncubateur` par la classe `main` si différente.)

## Développement
- Les contrôleurs FXML se trouvent dans `src/Controller/` et les vues dans `src/View/`.
- Les modèles sont dans `src/Model/`.

## Tester localement
- Ouvrir le projet dans NetBeans et lancer les classes ou FXML associés.
- Vérifier `src/conferences.txt`, `src/entrepreneurs.txt`, etc. pour données d'exemple.

## Mettre le README sur GitHub (commandes utiles)
Si le dépôt local est déjà connecté à un remote GitHub :

```powershell
cd GestionIncubateur
git add README.md
git commit -m "Add README.md"
git push
```

Si aucun remote n'est configuré :

```powershell
cd GestionIncubateur
git remote add origin <GIT_URL>
git push -u origin main
```

Remplacez `<GIT_URL>` par l'URL de votre dépôt GitHub.

## Contribuer
- Ouvrir une issue ou créer une branche, puis soumettre une pull request.

---
Si vous voulez, je peux :
- exécuter les commandes `git` ici pour committer et pousser (je vous demanderai l'autorisation),
- ou simplement vous donner les commandes à copier/coller.
