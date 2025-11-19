package Controller;

import Model.Gestion_Utilisateur.Administrateur;
import Model.Gestion_Utilisateur.Entrepreneur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.List;

public class gestionEntrepreneur {

    
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TextField prenomTextField;
    
    @FXML
    private TextField cinTextField;
    
    @FXML
    private TextField emailTextField;
    
    @FXML
    private TextField tailleEquipeTextField;
    
    @FXML
    private TextField entrepreneurIdTextField;
    
    @FXML
    private Button ajouterEntrepreneurButton;
    
    @FXML
    private Button supprimerEntrepreneurButton;
    @FXML
private Button buttonRetourner; // Déclaration du bouton
    @FXML
private Button ButtonGestionProjet;
    @FXML
    private TableView<Entrepreneur> entrepreneurTableView; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, String> nomColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, String> prenomColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, Integer> cinColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, String> emailColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, Integer> tailleEquipeColumn; // Remplacez par votre modèle de données
    @FXML
    private TableColumn<Entrepreneur, Integer> entrepreneurIdColumn; // Remplacez par votre modèle de données
 @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    private Administrateur administrateur; // Instance de l'administrateur
    private ObservableList<Entrepreneur> entrepreneurList; // Liste observable pour la table
  

    public gestionEntrepreneur() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.entrepreneurList = FXCollections.observableArrayList();
    }
    // Méthode pour récupérer la liste des entrepreneurs
    public List<Entrepreneur> getEntrepreneurs() {
        return administrateur.getEntrepreneurs(); // Récupérer les entrepreneurs de l'administrateur
    }

    @FXML
    public void initialize() {
 
nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
cinColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCin()).asObject());
emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
tailleEquipeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTailleEquipe()).asObject());
entrepreneurIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEntrepreneurID()).asObject());

// Action pour le bouton Retourner
    buttonRetourner.setOnAction(event -> handleButtonRetourner());
    ButtonGestionProjet.setOnAction(event -> handleButtonGestionProjet());
    // Remplir la table avec les entrepreneurs
    entrepreneurTableView.setItems(entrepreneurList);
    loadEntrepreneursFromFile();

        // Action pour le bouton Ajouter Entrepreneur
        ajouterEntrepreneurButton.setOnAction(event -> ajouterEntrepreneur());

        // Action pour le bouton Supprimer Entrepreneur
        supprimerEntrepreneurButton.setOnAction(event -> supprimerEntrepreneur());
        
    }

    private void ajouterEntrepreneur() {
        // Récupérer les valeurs des champs de texte
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        int CIN = Integer.parseInt(cinTextField.getText());
        String email = emailTextField.getText();
        int tailleEquipe = Integer.parseInt(tailleEquipeTextField.getText());
        int entrepreneurId = Integer.parseInt(entrepreneurIdTextField.getText());

        // Créer un nouvel entrepreneur
        Entrepreneur entrepreneur = new Entrepreneur(nom, prenom,CIN, email,entrepreneurId, tailleEquipe);
        
        // Ajouter l'entrepreneur à l'administrateur
        administrateur.ajouterEntrepreneur(entrepreneur);
 
        // Ajouter l'entrepreneur à la liste observable
        entrepreneurList.add(entrepreneur);
        // Enregistrer les entrepreneurs dans le fichier
        saveEntrepreneursToFile();
        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout d'entrepreneur");
        alert.setHeaderText(null);
        alert.setContentText("entrepreneur ajouté avec succès !");
        alert.showAndWait();
        
        // Réinitialiser les champs de texte
        clearFields();
        
    
    }

private void supprimerEntrepreneur() {
    // Vérifiez l'état de la liste avant la suppression
    if (entrepreneurList.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("suppression d'entrepreneur");
        alert.setHeaderText(null);
        alert.setContentText("Aucun entrepreneur à supprimer.");
        alert.showAndWait();
        return; // Sortir de la méthode si la liste est vide
    }

    // Récupérer l'entrepreneur sélectionné
    Entrepreneur selectedEntrepreneur = entrepreneurTableView.getSelectionModel().getSelectedItem();
    
    // Vérifiez si un entrepreneur est sélectionné
    if (selectedEntrepreneur == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un entrepreneur à supprimer.");
        alert.showAndWait();
        return; // Sortir de la méthode si aucun entrepreneur n'est sélectionné
    }

    // Supprimer l'entrepreneur de l'administrateur
    administrateur.supprimerEntrepreneur(selectedEntrepreneur);
    
    // Supprimer l'entrepreneur de la liste observable
    entrepreneurList.remove(selectedEntrepreneur);
    // Enregistrer les entrepreneurs dans le fichier
        saveEntrepreneursToFile();
}
  

    private void clearFields() {
        // Réinitialiser les champs de texte
        nomTextField.clear();
        prenomTextField.clear();
        cinTextField.clear();
        emailTextField.clear();
        tailleEquipeTextField.clear();
        entrepreneurIdTextField.clear();
    }
    


private static final String FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\entrepreneurs.txt"; // Chemin du fichier

private void saveEntrepreneursToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (Entrepreneur entrepreneur : entrepreneurList) {
            writer.write(entrepreneur.getNom() + "," +
                         entrepreneur.getPrenom() + "," +
                         entrepreneur.getCin() + "," +
                         entrepreneur.getEmail() + "," +
                         entrepreneur.getTailleEquipe() + "," +
                         entrepreneur.getEntrepreneurID());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void loadEntrepreneursFromFile() {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6) {
                String nom = data[0];
                String prenom = data[1];
                int cin = Integer.parseInt(data[2]);
                String email = data[3];
                int tailleEquipe = Integer.parseInt(data[4]);
                int entrepreneurId = Integer.parseInt(data[5]);

                Entrepreneur entrepreneur = new Entrepreneur(nom, prenom, cin, email, entrepreneurId, tailleEquipe);
                entrepreneurList.add(entrepreneur);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
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

@FXML
private void handleButtonGestionProjet() {
    passEntrepreneursToGestionProjetController();
    loadEntrepreneursFromFile() ;
}
private void passEntrepreneursToGestionProjetController() {
        try {
            
            // Charger la vue FXML pour le projet
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gestionProjet.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur de la vue GestionProjetController
            GestionProjetController gestionProjetController = loader.getController();

            // Passer la liste des entrepreneurs à ce contrôleur
            gestionProjetController.setEntrepreneurs(administrateur.getEntrepreneurs());

            // Afficher la scène avec la nouvelle vue
            Stage stage = (Stage) ButtonGestionProjet.getScene().getWindow();
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