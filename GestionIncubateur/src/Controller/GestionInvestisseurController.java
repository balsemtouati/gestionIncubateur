/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author touat
 */


import Model.Gestion_Utilisateur.Administrateur;
import Model.Gestion_Utilisateur.Entrepreneur;
import Model.Gestion_Utilisateur.Investisseur; 
import gestionincubateur.ErreurEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;


import javafx.scene.control.Button;


import javafx.scene.control.TextField;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

public class GestionInvestisseurController {

    @FXML
    private Label statusLabel;

    @FXML
    private TextField investorIdField;
    
    @FXML
    private TextField investmentCountField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField cinField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField surnameField;

    @FXML
    private Button addInvestorButton;
    @FXML
    private Button suppInvestorButton; // Assurez-vous que ce bouton est défini dans votre FXML
    @FXML
    private Button ressButton; // Assurez-vous que ce bouton est défini dans votre FXML

    @FXML
    private Button buttonRetourner; // Déclaration du bouton Retourner

    @FXML
    private TableView<Investisseur> investorTable; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, String> nameColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, String> surnameColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, Integer> cinColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, String> emailColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, Integer> investmentCountColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Investisseur, Integer> investorIdColumn; // Remplacez par votre modèle de données
    
     @FXML
    private Button minimizeButton;
     
      @FXML
    private Button closeButton;

    private Administrateur administrateur; // Instance de l'administrateur
    private ObservableList<Investisseur> investorList; // Liste observable pour la table

    public GestionInvestisseurController() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.investorList = FXCollections.observableArrayList();
    }
  // Méthode pour récupérer la liste des entrepreneurs
    public List<Investisseur> getInvestisseurs() {
        return administrateur.getInvestisseurs(); // Récupérer les entrepreneurs de l'administrateur
    }

    @FXML
    public void initialize() {
        // Configuration des colonnes de la table
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        surnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        cinColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCin()).asObject());
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        investmentCountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNbInvestissements()).asObject());
        investorIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInvestisseurID()).asObject());

        // Remplir la table avec les investisseurs
        investorTable.setItems(investorList);
        loadInvestorsFromFile();
        ressButton.setOnAction(event -> handleButtonRessource());

        // Action pour le bouton Ajouter Investisseur
        addInvestorButton.setOnAction(event -> {
            try {
                ajouterInvestisseur();
            } catch (ErreurEvent ex) {
                Logger.getLogger(GestionInvestisseurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         // Lier l'action du bouton à la méthode supprimerInvestisseur
        suppInvestorButton.setOnAction(event -> supprimerInvestisseur());
        
        // Action pour le bouton Retourner
        buttonRetourner.setOnAction(event -> handleButtonRetourner());
    }
    private static final String INVESTOR_FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\investisseurs.txt"; // Chemin du fichier pour les investisseurs
    private void saveInvestorsToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVESTOR_FILE_PATH))) {
        for (Investisseur investisseur : investorList) {
            writer.write(investisseur.getNom() + "," +
                         investisseur.getPrenom() + "," +
                         investisseur.getCin() + "," +
                         investisseur.getEmail() + "," +
                         investisseur.getInvestisseurID() + "," +
                         investisseur.getNbInvestissements());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void loadInvestorsFromFile() {
    File file = new File(INVESTOR_FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6) { // Assurez-vous que le nombre de données est correct
                String nom = data[0];
                String prenom = data[1];
                int cin = Integer.parseInt(data[2]);
                String email = data[3];
                int investorId = Integer.parseInt(data[4]);
                int investmentCount = Integer.parseInt(data[5]);

                Investisseur investisseur = new Investisseur(nom, prenom, cin, email, investorId, investmentCount);
                investorList.add(investisseur); // Ajouter l'investisseur à la liste observable
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void ajouterInvestisseur() throws ErreurEvent {
        // Récupérer les valeurs des champs de texte
        String nom = nameField.getText();
        String prenom = surnameField.getText();
        int CIN = Integer.parseInt(cinField.getText());
        String email = emailField.getText();
        int investmentCount = Integer.parseInt(investmentCountField.getText());
        int investorId = Integer.parseInt(investorIdField.getText());

        // Créer un nouvel investisseur
        Investisseur investisseur = new Investisseur(nom, prenom, CIN, email, investorId, investmentCount);
        
        // Ajouter l'investisseur à l'administrateur
        administrateur.ajouterInvestisseur(investisseur);
 
        // Ajouter l'investisseur à la liste observable
        investorList.add(investisseur);
        
        // Réinitialiser les champs de texte
       
        clearFields();
        // Sauvegarder les investisseurs dans le fichier
    saveInvestorsToFile();
        
        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout d'Investisseur");
        alert.setHeaderText(null);
        alert.setContentText("Investisseur ajouté avec succès !");
        alert.showAndWait();
    }
    private void supprimerInvestisseur() {
    // Vérifiez l'état de la liste avant la suppression
    if (investorList.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Aucun investisseur à supprimer.");
        alert.showAndWait();
        return; // Sortir de la méthode si la liste est vide
    }

    // Récupérer l'entrepreneur sélectionné
    Investisseur selectedInvestisseur = investorTable.getSelectionModel().getSelectedItem();
    
    // Vérifiez si un entrepreneur est sélectionné
    if (selectedInvestisseur == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un investisseur à supprimer.");
        alert.showAndWait();
        return; // Sortir de la méthode si aucun entrepreneur n'est sélectionné
    }

    // Supprimer l'entrepreneur de l'administrateur
    administrateur.supprimerInvestisseur(selectedInvestisseur);
    
    // Supprimer l'entrepreneur de la liste observable
    investorList.remove(selectedInvestisseur);
    // Sauvegarder les investisseurs dans le fichier
    saveInvestorsToFile();
}

    private void clearFields() {
        // Réinitialiser les champs de texte
        nameField.clear();
        surnameField.clear();
        cinField.clear();
        emailField.clear();
        investmentCountField.clear();
        investorIdField.clear();
    }

    @FXML
    public void handleButtonRetourner() {
        try {
            // Charger la scène d'accueil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Acceuil.fxml")); 
            Parent root = loader.load();
            
            // Obtenir la fenêtre actuelle
            Stage stage = (Stage) buttonRetourner.getScene().getWindow();
            
            // Changer la scène
            stage.setScene(new Scene(root));
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }
    }
  
   private void handleButtonRessource() {
      
        try {
            // Charger la vue FXML pour le projet
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajouterRessource.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur de la vue GestionProjetController
            AjouterRessourceController g = loader.getController();

            // Passer la liste des entrepreneurs à ce contrôleur
           g.setInvestisseurs(administrateur.getInvestisseurs());

            // Afficher la scène avec la nouvelle vue
            Stage stage = (Stage) ressButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       // Méthode pour minimiser la fenêtre
    @FXML
    private void handleMinimizeButtonAction() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
        stage.setIconified(true); // Minimise la fenêtre
    }

    // Méthode pour fermer la fenêtre
    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
        stage.close(); // Ferme la fenêtre
    }
}


