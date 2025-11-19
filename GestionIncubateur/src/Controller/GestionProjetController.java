package Controller;

import Model.Gestion_Projets.Projet;
import Model.Gestion_Projets.Ressource;
import Model.Gestion_Utilisateur.Administrateur;
import Model.Gestion_Utilisateur.Entrepreneur;
import Model.Gestion_Utilisateur.Investisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListCell;
import gestionincubateur.ErreurEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class GestionProjetController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField domaineField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<Entrepreneur> entrepreneurComboBox;

    @FXML
    private Button ajouterProjetButton;

    @FXML
    private Label resultLabel;

    @FXML
    private TableView<Projet> projetTableView;

    @FXML
    private TableColumn<Projet, String> nomProjetcolumn;

    @FXML
    private TableColumn<Projet, String> domainecolumn;

    @FXML
    private TableColumn<Projet, String> descriptioncolumn;

    @FXML
    private TableColumn<Projet, String> nomPropcolumn;

    @FXML
    private TableColumn<Projet, String> prenomPropcolumn;

    @FXML
    private TableColumn<Projet, Integer> CINPropcolumn;

    @FXML
    private TableColumn<Projet, String>emailPropcolumn;

    @FXML
    private TableColumn<Projet, Integer> tailleequipecolumn;

    @FXML
    private TableColumn<Projet, Integer> idPropColumn;
    
    @FXML
    private Button suppProjetButton;
    @FXML
    private Button ButtonAcceuil;
     @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button modifierProjetButton;

    private Administrateur administrateur; // Instance de l'administrateur
    private ObservableList<Entrepreneur> entrepreneurList; // Liste observable pour les entrepreneurs
    private ObservableList<Projet> projetList;
    public GestionProjetController() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.projetList = FXCollections.observableArrayList();
    }
    

    public void setEntrepreneurs(List<Entrepreneur> entrepreneurs) {
    if (entrepreneurComboBox != null && entrepreneurs != null) {
        entrepreneurComboBox.setItems(FXCollections.observableArrayList(entrepreneurs));

        // Personnaliser l'affichage dans le ComboBox
        entrepreneurComboBox.setCellFactory(listView -> new ListCell<Entrepreneur>() {
            @Override
            protected void updateItem(Entrepreneur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString()); // Assurez-vous que toString() est bien défini dans Entrepreneur
                }
            }
        });

        entrepreneurComboBox.setButtonCell(new ListCell<Entrepreneur>() {
            @Override
            protected void updateItem(Entrepreneur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
}
    

     @FXML
    public void initialize() {
       
        // Initialisation des colonnes de la TableView
        nomProjetcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nom()));
        domainecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().domaine()));
        descriptioncolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().description()));
        nomPropcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().proprietaire().getNom()));
        prenomPropcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().proprietaire().getPrenom()));
        CINPropcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().proprietaire().getCin()).asObject());
        emailPropcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().proprietaire().getEmail()));
        tailleequipecolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().proprietaire().getTailleEquipe()).asObject());
        idPropColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().proprietaire().getEntrepreneurID()).asObject());
        // Initialiser la liste des entrepreneurs
    entrepreneurList = FXCollections.observableArrayList();
         // Charger les projets depuis le fichier
      loadProjectsFromFile();
      loadEntrepreneursFromFile();
      // Remplir le ComboBox avec les entrepreneurs
    setEntrepreneurs(entrepreneurList);
      // Remplir la table avec les entrepreneurs
       projetTableView.setItems(projetList);
        // Action du bouton Ajouter Projet
        ajouterProjetButton.setOnAction(event -> {
            try {
                ajouterProjet();
            } catch (ErreurEvent ex) {
                Logger.getLogger(GestionProjetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        suppProjetButton.setOnAction(event -> this.supprimerProjet(event));
       
    }
    
    private static final String PROJECT_FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\projets.txt"; // Chemin du fichier
    private void saveProjectsToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROJECT_FILE_PATH))) {
        for (Projet projet : projetList) {
            writer.write(projet.nom()+ "," +
                         projet.domaine()+ "," +
                         projet.description()+ "," +
                         projet.proprietaire().getNom() + "," +
                         projet.proprietaire().getPrenom() + "," +
                         projet.proprietaire().getCin() + "," +
                         projet.proprietaire().getEmail() + "," +
                         projet.proprietaire().getTailleEquipe() + "," +
                         projet.proprietaire().getEntrepreneurID());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    private void loadProjectsFromFile() {
    File file = new File(PROJECT_FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 9) { // Assurez-vous que le nombre de données correspond
                String nom = data[0];
                String domaine = data[1];
                String description = data[2];
                String nomProprietaire = data[3];
                String prenomProprietaire = data[4];
                int cin = Integer.parseInt(data[5]);
                String email = data[6];
                int tailleEquipe = Integer.parseInt(data[7]);
                int entrepreneurId = Integer.parseInt(data[8]);

                Entrepreneur proprietaire = new Entrepreneur(nomProprietaire, prenomProprietaire, cin, email, entrepreneurId, tailleEquipe);
                Projet projet = new Projet(nom, domaine, description, proprietaire);
                projetList.add(projet);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private void loadEntrepreneursFromFile() {
    File file = new File("C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\entrepreneurs.txt");
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

private void ajouterProjet() throws ErreurEvent {
    // Récupérer les valeurs des champs de texte
    try {
        String nom = nomField.getText();
        String domaine = domaineField.getText();
        String description = descriptionField.getText();
        Entrepreneur proprietaire = entrepreneurComboBox.getValue(); // Utilisez directement cette valeur

        if (proprietaire != null) { 
            Projet p = new Projet(nom, domaine, description, proprietaire);
            
            // Appel à la méthode d'ajout de la classe Administrateur
            administrateur.ajouterProjet(p);

            // Ajouter le projet à la liste observable et mettre à jour la table
            projetList.add(p);
            projetTableView.setItems(projetList);
            Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ajout projet");
        alert.setHeaderText(null);
        alert.setContentText("projet ajouté avec succés .");
        alert.showAndWait();
        // Enregistrer les projets dans le fichier
            saveProjectsToFile(); // Enregistrer les projets après ajout
        } 

        clearFields();
    
    } catch (ErreurEvent e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ajout projet");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}



     public void supprimerProjet(ActionEvent event) {
    Projet selectedProjet = projetTableView.getSelectionModel().getSelectedItem();
    if (selectedProjet != null) {
        administrateur.supprimerProjet(selectedProjet);
        projetList.remove(selectedProjet);
        projetTableView.setItems(projetList);
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("suppression de projet");
        alert.setHeaderText(null);
        alert.setContentText("projet supprimé avec succès !");
        alert.showAndWait();
        // Enregistrer les projets dans le fichier
            saveProjectsToFile(); // Enregistrer les projets après ajout
    } else {
         Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("suppression de projet");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une projet à supprimer.");
        alert.showAndWait();
        
    }
}

  // Méthode fictive pour obtenir la liste des investisseurs
    private ObservableList<Entrepreneur> proprietaire() {
        // Remplacez ceci par votre logique pour obtenir la liste des investisseurs
        return FXCollections.observableArrayList(); // Exemple vide
    }     
    @FXML
private void modifierProjet() {
    Projet selectedProjet = projetTableView.getSelectionModel().getSelectedItem();
    if (selectedProjet != null) {
        // Créer un TextArea pour saisir les nouvelles informations
        TextArea textArea = new TextArea();
        textArea.setText("Nom: " + selectedProjet.nom() + "\n" +
                         "Domaine: " + selectedProjet.domaine() + "\n" +
                         "Description: " + selectedProjet.description() + "\n");
        textArea.setWrapText(true);
        
        // Créer une nouvelle fenêtre pour afficher le TextArea
        Stage modifierStage = new Stage();
        modifierStage.setTitle("Modifier Projet");
        
        // Créer un bouton pour enregistrer les modifications
        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(event -> {
            String[] lignes = textArea.getText().split("\n");
            if (lignes.length >= 3) {
                String nouveauNom = lignes[0].replace("Nom: ", "").trim();
                String nouveauDomaine = lignes[1].replace("Domaine: ", "").trim();
                String nouvelleDescription = lignes[2].replace("Description: ", "").trim();
                
                // Modifier le projet
                Entrepreneur proprietaire = selectedProjet.proprietaire(); // Garder le même propriétaire
                Projet nouveauProjet = administrateur.modifierProjet(nouveauNom, nouveauDomaine, nouvelleDescription, proprietaire);
                
                // Mettre à jour la liste et la TableView
                int index = projetList.indexOf(selectedProjet);
                projetList.set(index, nouveauProjet);
                projetTableView.setItems(projetList);
                
                // Afficher un message de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification de Projet");
                alert.setHeaderText(null);
                alert.setContentText("Projet modifié avec succès !");
                alert.showAndWait();
                
                modifierStage.close(); // Fermer la fenêtre de modification
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir toutes les informations requises.");
                alert.showAndWait();
            }
        });
        
        // Créer un layout pour le TextArea et le bouton
        VBox vbox = new VBox(textArea, saveButton);
        Scene scene = new Scene(vbox, 400, 300);
        modifierStage.setScene(scene);
        modifierStage.show();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modification de Projet");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un projet à modifier.");
        alert.showAndWait();
    }
}

    private void clearFields() {
        nomField.clear();
        domaineField.clear();
        descriptionField.clear();
        entrepreneurComboBox.getSelectionModel().clearSelection();
    }
public void handleButtonRetourner() {
    try {
        // Charger la scène d'accueil
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Acceuil.fxml")); 
        Parent root = loader.load();
        
        // Obtenir la fenêtre actuelle
        Stage stage = (Stage) ButtonAcceuil.getScene().getWindow();
        
        // Changer la scène
        stage.setScene(new Scene(root));
        stage.show(); // Afficher la nouvelle scène
    } catch (IOException e) {
        e.printStackTrace(); // Gérer l'exception
    }
 
}
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

 