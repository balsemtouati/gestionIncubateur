package Controller;

import Model.Gestion_Utilisateur.Administrateur;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField; // Champ de texte pour l'ID administrateur

    @FXML
    private PasswordField passwordField; // Champ de texte pour le mot de passe

    @FXML
    private Button loginButton; // Bouton de connexion

    @FXML
    private Text feedbackText; // Texte de feedback pour les messages
    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    

    // Simuler une base de données d'administrateurs
    private Map<Integer, Administrateur> administrateurs = new HashMap<>();

    public LoginController() {
        // Initialiser quelques administrateurs pour la démonstration
        administrateurs.put(1, new Administrateur(1, 1234, "benhenda", "imen", 30, "123 Rue Exemple"));
        administrateurs.put(2, new Administrateur(2, 2222, "touati", "balsem", 28, "456 Rue Exemple"));
    }

    // Méthode appelée lors du clic sur le bouton "Connexion"
    @FXML
private void handleLoginButtonAction() {
    String idText = usernameField.getText(); // Récupère le texte du champ ID
    String motDePasse = passwordField.getText(); // Récupère le texte du champ mot de passe

    // Vérification des champs
    if (idText.isEmpty() || motDePasse.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connexion !");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        
        return;
    }

    try {
        int administrateurID = Integer.parseInt(idText); // Convertir l'ID en entier

        // Vérifier si l'administrateur existe
        Administrateur administrateur = administrateurs.get(administrateurID);
        if (administrateur != null && String.valueOf(administrateur.getMotDePasse()).equals(motDePasse)) {
            // Feedback pour l'utilisateur
            feedbackText.setText("Administrateur connecté ");
            // Affichage dans la console pour vérification
            System.out.println(administrateur);
            
            // Charger la scène d'accueil
            loadHomeScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connexion !");
        alert.setHeaderText(null);
        alert.setContentText("Identifiant ou mot de passe incorrect.");
        alert.showAndWait();
        }

    } catch (NumberFormatException e) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connexion !");
        alert.setHeaderText(null);
        alert.setContentText("L'ID doit être un nombre entier valide.");
        alert.showAndWait();
    }
}
   private void loadHomeScene() {
    try {
        // Charger le fichier FXML pour la scène d'accueil
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Acceuil.fxml"));
        Parent homeRoot = loader.load();

        // Créer une nouvelle scène avec le contenu de l'accueil
        Scene homeScene = new Scene(homeRoot);

        // Récupérer la scène actuelle et la remplacer par la scène d'accueil
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        currentStage.setScene(homeScene);
        currentStage.setTitle("Accueil"); // Titre de la fenêtre
        currentStage.show();

    } catch (IOException e) {
        e.printStackTrace();
        feedbackText.setText("Erreur de chargement de la scène d'accueil.");
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