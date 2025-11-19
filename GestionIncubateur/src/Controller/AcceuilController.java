package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AcceuilController {

    @FXML
    private ImageView backgroundImage; // Image de fond

    @FXML
    private Label welcomeLabel; // Label "BIENVENUE"

    @FXML
    private Label subtitleLabel; // Label "DANS NOTRE APPLICATION"
   @FXML
    private Button entrepreneurButton; // Déclaration du bouton
   @FXML
    private Button ProjetButton;
   @FXML
   private Button idgestionEvenement;
    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
     @FXML
    private SplitMenuButton evenementbuutton;

    @FXML
    private MenuItem formationMenuItem;

    @FXML
    private MenuItem conferenceMenuItem;
    
     @FXML
    private Button   investisseurButton;
     @FXML
    private Button ressourceButton;
     
    @FXML
    public void initialize() {
        // Vous pouvez initialiser des valeurs ou des styles ici
        welcomeLabel.setText("BIENVENUE");
        subtitleLabel.setText("DANS NOTRE APPLICATION");
        
        // Vous pouvez ajouter d'autres initialisations ici si nécessaire
    }
    
    // Méthode pour gérer l'action du bouton "Entrepreneur"
    @FXML
    public void handleButtonActionEntrepreneur() {
        try {
            // Charger la nouvelle scène pour l'interface de l'entrepreneur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestionEntrepreneur.fxml")); // Remplacez par le chemin correct
            Parent root = loader.load();
            Stage stage = (Stage) entrepreneurButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
            stage.setScene(new Scene(root)); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }
}
// Méthode pour gérer l'action du bouton "projet"
    @FXML
    public void handleButtonActionProjet() {
        try {
            // Charger la nouvelle scène pour l'interface de l'entrepreneur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gestionProjet.fxml")); // Remplacez par le chemin correct
            Parent root = loader.load();
            Stage stage = (Stage) investisseurButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
            stage.setScene(new Scene(root)); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }}
   // Méthode pour gérer l'action du bouton "projet"
    @FXML
    public void handleButtonActionInvestisseur() {
        try {
            // Charger la nouvelle scène pour l'interface de l'entrepreneur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestionInvestisseur.fxml")); // Remplacez par le chemin correct
            Parent root = loader.load();
            Stage stage = (Stage) investisseurButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
            stage.setScene(new Scene(root)); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }}
      @FXML
    public void handleButtonActionRessource() {
        try {
            // Charger la nouvelle scène pour l'interface de l'entrepreneur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajouterRessource.fxml")); // Remplacez par le chemin correct
            Parent root = loader.load();
            Stage stage = (Stage) investisseurButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
            stage.setScene(new Scene(root)); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }}
    @FXML
    public void handleButtonActionGestionEvenement() {
        try {
            // Charger la nouvelle scène pour l'interface de l'entrepreneur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gestionLieu.fxml")); // Remplacez par le chemin correct
            Parent root = loader.load();
            Stage stage = (Stage) investisseurButton.getScene().getWindow(); // Obtenir la fenêtre actuelle
            stage.setScene(new Scene(root)); // Changer la scène
            stage.show(); // Afficher la nouvelle scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }}
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
@FXML
    public void handleFormation() {
        // Charger l'interface de formation
        try {
            AnchorPane formationPane = FXMLLoader.load(getClass().getResource("/view/orgationsForm.fxml"));
            Scene formationScene = new Scene(formationPane);
            Stage stage = (Stage) evenementbuutton.getScene().getWindow();
            stage.setScene(formationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleConference() {
        // Charger l'interface de conférence
        try {
            AnchorPane conferencePane = FXMLLoader.load(getClass().getResource("/view/conference.fxml"));
            Scene conferenceScene = new Scene(conferencePane);
            Stage stage = (Stage) evenementbuutton.getScene().getWindow();
            stage.setScene(conferenceScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}