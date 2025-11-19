/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Gestion_Evenements.Formation;
import Model.Gestion_Evenements.Lieu;
import Model.Gestion_Acteurs.Formateur;
import gestionincubateur.ErreurEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

import Model.Gestion_Evenements.Lieu;

import Model.Gestion_Acteurs.Formateur;

import Model.Gestion_Evenements.Formation;
import Model.Gestion_Projets.Projet;

import Model.Gestion_Utilisateur.Administrateur;
import Model.Gestion_Utilisateur.Entrepreneur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Alert;

import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormationController {

    @FXML
    private TextField formationIdField;
    @FXML
    private TextField dureeField;
    @FXML
    private TextField budgetField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField capaciteField;
    @FXML
    private ComboBox<String> yourComboBoxId; // Pour le champ "réservé"
    @FXML
    private TextField formateuridfield;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prénomField;

 @FXML
    private TableView<Formation> tableView; // TableView pour afficher les formations
    @FXML
    private TableColumn<Formation, Integer> formationidcolumn; 
    @FXML
    private TableColumn<Formation, Integer> dureecolumn; 
    @FXML
    private TableColumn<Formation, Double> budgetcolumn; 
    @FXML
    private TableColumn<Formation, String> adressecolumn; 
    @FXML
    private TableColumn<Formation, Integer> capacitecolumn; 
    @FXML
    private TableColumn<Formation, Boolean> reservecolumn;
    @FXML
    private TableColumn<Formation, Integer> formateuridcolumn;
    @FXML
    private TableColumn<Formation, String> nomcolumn;
    @FXML
    private TableColumn<Formation, String> prenomcolumn;

    @FXML
    private Button ajouterfield;
    @FXML
    private Button supprimerfield;
    @FXML
    private Button rapportfield;
    @FXML
    private Button filtrerfield;
    @FXML
    private Button acceuilbutton;
    @FXML
    private Button ButtonGestionFormation;
    
      //private Button minimizeButton;

    //private Button closeButton;
    // Instance de la classe Administrateur
    private Administrateur administrateur;
    private ObservableList<Formation> formationList; 

      public FormationController() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.formationList = FXCollections.observableArrayList();
    }
    // Méthode pour récupérer la liste des entrepreneurs
    public List<Formation> getFormations() {
        return administrateur.getFormation(); // Récupérer les entrepreneurs de l'administrateur
    }


    @FXML
    public void initialize() {
        // Initialisation des composants si nécessaire
        yourComboBoxId.getItems().addAll("Oui", "Non"); // Exemple d'options pour le ComboBox
         // Sélectionner par défaut la première option
        yourComboBoxId.setValue("non");
                // Initialisation des colonnes
// Initialisation des colonnes avec des expressions lambda
        
        formationidcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFormationId()).asObject());
        dureecolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuree()).asObject());
        budgetcolumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBudget()).asObject());
        adressecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation().getAdresse())); // Assurez-vous d'avoir une méthode toString() dans Lieu
        capacitecolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLocation().getCapacite()).asObject()); // Vous devez définir comment obtenir la capacité
        reservecolumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getLocation().getReserve())); // Vous devez définir comment obtenir la réserve
        formateuridcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getForm().getFormateurId()).asObject()); // Vous devez définir comment obtenir l'ID du formateur
        nomcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForm().getNom())); // Vous devez définir comment obtenir le nom
        prenomcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForm().getPrenom())); // Vous devez définir comment obtenir le prénom
// Charger les formations depuis le fichier
    loadFormationsFromFile();

    // Remplir la TableView avec les formations
    tableView.setItems(formationList);
        
    }
private static final String FORMATION_FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\formations.txt"; // Chemin du fichier pour les formations
private void saveFormationsToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FORMATION_FILE_PATH))) {
        for (Formation formation : formationList) {
            writer.write(formation.getFormationId() + "," +
                         formation.getDuree() + "," +
                         formation.getBudget() + "," +
                         formation.getLocation().getAdresse() + "," +
                         formation.getLocation().getCapacite() + "," +
                         formation.getLocation().getReserve() + "," +
                         formation.getForm().getFormateurId() + "," +
                         formation.getForm().getNom() + "," +
                         formation.getForm().getPrenom());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private void loadFormationsFromFile() {
    File file = new File(FORMATION_FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 9) { // Assurez-vous que le nombre de données est correct
                int formationId = Integer.parseInt(data[0]);
                int duree = Integer.parseInt(data[1]);
                double budget = Double.parseDouble(data[2]);
                String adresse = data[3];
                int capacite = Integer.parseInt(data[4]);
                boolean reserve = Boolean.parseBoolean(data[5]);
                int formateurId = Integer.parseInt(data[6]);
                String nom = data[7];
                String prenom = data[8];

                // Créer un objet Lieu
                Lieu lieu = new Lieu(adresse, capacite, reserve);

                // Créer un objet Formateur
                Formateur formateur = new Formateur(formateurId, nom, prenom); // Assurez-vous que la classe Formateur existe

                // Créer une instance de Formation
                Formation formation = new Formation(duree, budget, lieu, formationId, formateur);
                formationList.add(formation); // Ajouter la formation à la liste observable
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @FXML
    private void handleAjouterAction() {
        try {
            // Récupérer les valeurs des champs
            int formationId = Integer.parseInt(formationIdField.getText());
            int duree = Integer.parseInt(dureeField.getText());
            double budget = Double.parseDouble(budgetField.getText());
            String adresse = adresseField.getText();
            int capacite = Integer.parseInt(capaciteField.getText());
            int formateurId = Integer.parseInt(formateuridfield.getText());
            String nom = nomField.getText();
            String prenom = prénomField.getText();
            boolean reserve = yourComboBoxId.getValue().equals("Oui");

            // Créer un objet Lieu
            Lieu lieu = new Lieu(adresse, capacite, reserve);

            // Créer un objet Formateur
            Formateur formateur = new Formateur(formateurId, nom, prenom);

            // Créer une instance de Formation
            Formation formation = new Formation(duree, budget, lieu, formationId, formateur);

            // Ajouter la formation à l'administrateur
            administrateur.ajouterFormation(formation);
            
            formationList.add(formation);
            tableView.setItems(formationList); // Remplir la TableView
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ajout formation");
        alert.setHeaderText(null);
        alert.setContentText("formation ajoutée avec succès !");
        alert.showAndWait();
        saveFormationsToFile();
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer des valeurs valides.", AlertType.ERROR);
        } catch (ErreurEvent e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ajout formation");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        }
        // Réinitialiser les champs de texte
        clearFields();
        
    }
    @FXML
private void handleRapportAction() {
    StringBuilder rapportGlobal = new StringBuilder();
    
    for (Formation formation : formationList) {
        rapportGlobal.append(formation.genererRapport()).append("\n\n"); // Utiliser genererRapport
    }
    
    // Afficher le rapport global dans une nouvelle fenêtre
    afficherRapportDansNouvelleFenetre(rapportGlobal.toString());
}

private void afficherRapportDansNouvelleFenetre(String rapport) {
    Stage rapportStage = new Stage();
    rapportStage.setTitle("Rapport de toutes les Formations");

    // Créer un TextArea pour afficher le rapport
    TextArea rapportTextArea = new TextArea();
    rapportTextArea.setEditable(false);
    rapportTextArea.setText(rapport);
    
    // Configurer le TextArea pour qu'il occupe tout l'espace
    rapportTextArea.setWrapText(true); // Permettre le retour à la ligne
    rapportTextArea.setPrefSize(600, 400); // Taille préférée

    // Utiliser un ScrollPane pour permettre le défilement
    ScrollPane scrollPane = new ScrollPane(rapportTextArea);
    scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane
    scrollPane.setFitToHeight(true); // Ajuster la hauteur du ScrollPane

    // Créer une scène avec le ScrollPane
    Scene scene = new Scene(scrollPane, 600, 400);
    rapportStage.setScene(scene);
    
    rapportStage.show();
}
/*@FXML
private void handleFiltrerAction() {
    // Obtenir les noms des formateurs triés
    List<String> nomsFormateursTries = administrateur.obtenirNomsFormatteursTries();
    
    // Afficher les noms dans une nouvelle fenêtre
    afficherNomsFormateursDansNouvelleFenetre(nomsFormateursTries);
}*/
@FXML
private void handleFiltrerAction() {
    // Utiliser un Set pour éviter les doublons
    Set<String> nomsFormateursSet = new HashSet<>();
    
    // Ajouter les noms des formateurs chargés depuis le fichier texte
    for (Formation formation : formationList) {
        nomsFormateursSet.add(formation.getForm().getNom());
    }
    
    // Ajouter les noms des formateurs existants dans l'administrateur
    nomsFormateursSet.addAll(administrateur.obtenirNomsFormatteursTries());
    
    // Trier les noms des formateurs
    List<String> nomsFormateursTries = new ArrayList<>(nomsFormateursSet);
    Collections.sort(nomsFormateursTries);
    
    // Afficher les noms dans une nouvelle fenêtre
    afficherNomsFormateursDansNouvelleFenetre(nomsFormateursTries);
}
private void afficherNomsFormateursDansNouvelleFenetre(List<String> nomsFormateurs) {
    Stage nomFormateurStage = new Stage();
    nomFormateurStage.setTitle("Noms des Formateurs Triés");

    // Créer un TextArea pour afficher les noms
    TextArea nomFormateurTextArea = new TextArea();
    nomFormateurTextArea.setEditable(false);
    
    // Joindre les noms avec des sauts de ligne
    String nomsAffiches = String.join("\n", nomsFormateurs);
    nomFormateurTextArea.setText(nomsAffiches);
    
    // Configurer le TextArea pour qu'il occupe tout l'espace
    nomFormateurTextArea.setWrapText(true); // Permettre le retour à la ligne
    nomFormateurTextArea.setPrefSize(600, 400); // Taille préférée

    // Utiliser un ScrollPane pour permettre le défilement
    ScrollPane scrollPane = new ScrollPane(nomFormateurTextArea);
    scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane
    scrollPane.setFitToHeight(true); // Ajuster la hauteur du ScrollPane

    // Créer une scène avec le ScrollPane
    Scene scene = new Scene(scrollPane, 600, 400);
    nomFormateurStage.setScene(scene);
    
    nomFormateurStage.show();
    
}
     public void handleSupprimerAction (ActionEvent event) throws ErreurEvent {
    Formation selectedformation = tableView.getSelectionModel().getSelectedItem();
    if (selectedformation != null) {
        administrateur.supprimerFormation(selectedformation);
        formationList.remove(selectedformation);
        tableView.setItems(formationList);
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("suppression de formation");
        alert.setHeaderText(null);
        alert.setContentText("formation supprimée avec succès !");
        alert.showAndWait();
        saveFormationsToFile();
    } else {
         Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("suppression de formation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une formation à supprimer.");
        alert.showAndWait();
        
    }
}


    private void clearFields() {
        // Méthode pour vider les champs de texte après l'ajout
        formationIdField.clear();
        dureeField.clear();
        budgetField.clear();
        adresseField.clear();
        capaciteField.clear();
        formateuridfield.clear();
        nomField.clear();
        prénomField.clear();
        yourComboBoxId.setValue(null); // Réinitialiser le ComboBox
    }

    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
        @FXML
public void handleButtonRetourner() {
    try {
        // Charger la scène d'accueil
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Acceuil.fxml")); 
        Parent root = loader.load();
        
        // Obtenir la fenêtre actuelle
        Stage stage = (Stage) acceuilbutton.getScene().getWindow();
        
        // Changer la scène
        stage.setScene(new Scene(root));
        stage.show(); // Afficher la nouvelle scène
    } catch (IOException e) {
        e.printStackTrace(); // Gérer l'exception
    }
 
}

  /*@FXML
    private void handleMinimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
        stage.setIconified(true); // Minimise la fenêtre
    }

    // Méthode pour fermer la fenêtre
    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
        stage.close(); // Ferme la fenêtre
    }*/

}
