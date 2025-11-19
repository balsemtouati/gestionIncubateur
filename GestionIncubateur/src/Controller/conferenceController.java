/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Gestion_Acteurs.Formateur;
import Model.Gestion_Acteurs.Intervenant;
import Model.Gestion_Evenements.Conference;
import Model.Gestion_Evenements.Formation;
import Model.Gestion_Evenements.Lieu;
import Model.Gestion_Utilisateur.Administrateur;
import gestionincubateur.ErreurEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author touat
 */
public class conferenceController {
    

 @FXML
    private TextField ConferenceIDfield;
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
    private TextField intervenantidfield;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prénomField;
    @FXML
    private TextField fonctionfield;

 @FXML
    private TableView<Conference> tableView; // TableView pour afficher les formations
    @FXML
    private TableColumn<Conference, Integer> conferenceidcolumn; 
    @FXML
    private TableColumn<Conference, Integer> dureecolumn; 
    @FXML
    private TableColumn<Conference, Double> budgetcolumn; 
    @FXML
    private TableColumn<Conference, String> adressecolumn; 
    @FXML
    private TableColumn<Conference, Integer> capacitecolumn; 
    @FXML
    private TableColumn<Conference, Boolean> reservecolumn;
    @FXML
    private TableColumn<Conference, Integer> intervenantidcolumn;
    @FXML
    private TableColumn<Conference, String> nomcolumn;
    @FXML
    private TableColumn<Conference, String> prenomcolumn;
    @FXML
    private TableColumn<Conference, String> fonctioncolumn;

    @FXML
    private Button ajouterfield;
    @FXML
    private Button supprimerfield;
    @FXML
    private Button rapportfield;
    @FXML
    private Button acceuilbutton;

    // Instance de la classe Administrateur
    private Administrateur administrateur;
    private ObservableList<Conference> conferenceList; 
     public conferenceController() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.conferenceList = FXCollections.observableArrayList();
    }
    // Méthode pour récupérer la liste des entrepreneurs
    public List<Conference> getConferences() {
        return administrateur.getConference(); // Récupérer les entrepreneurs de l'administrateur
    }


    @FXML
    public void initialize() {
        //loadConferencesFromFile();
        // Initialisation des composants si nécessaire
        yourComboBoxId.getItems().addAll("Oui", "Non"); // Exemple d'options pour le ComboBox
         // Sélectionner par défaut la première option
        yourComboBoxId.setValue("false");
        
// Initialisation des colonnes avec des expressions lambda
        
        conferenceidcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getConferenceId()).asObject());
        dureecolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuree()).asObject());
        budgetcolumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBudget()).asObject());
        adressecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation().getAdresse())); // Assurez-vous d'avoir une méthode toString() dans Lieu
        capacitecolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLocation().getCapacite()).asObject()); // Vous devez définir comment obtenir la capacité
        reservecolumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getLocation().getReserve())); // Vous devez définir comment obtenir la réserve
        intervenantidcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIntervenant().intervenantId()).asObject()); // Vous devez définir comment obtenir l'ID du formateur
        nomcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntervenant().nom())); // Vous devez définir comment obtenir le nom
        prenomcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntervenant().prenom())); // Vous devez définir comment obtenir le prénom
        fonctioncolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntervenant().fonction()));
        tableView.setItems(conferenceList);
        loadConferencesFromFile();
    }
 private static final String CONFERENCE_FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\conferences.txt"; // Chemin du fichier pour les conférences
private void saveConferencesToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFERENCE_FILE_PATH))) {
        for (Conference conference : conferenceList) {
            writer.write(conference.getConferenceId() + "," +
                         conference.getDuree() + "," +
                         conference.getBudget() + "," +
                         conference.getLocation().getAdresse() + "," +
                         conference.getLocation().getCapacite() + "," +
                         conference.getLocation().getReserve() + "," +
                         conference.getIntervenant().intervenantId() + "," +
                         conference.getIntervenant().nom() + "," +
                         conference.getIntervenant().prenom());
            conference.getIntervenant().fonction();
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void loadConferencesFromFile() {
    File file = new File(CONFERENCE_FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 9) { // Assurez-vous que le nombre de données est correct
                int conferenceId = Integer.parseInt(data[0]);
                int duree = Integer.parseInt(data[1]);
                double budget = Double.parseDouble(data[2]);
                String adresse = data[3];
                int capacite = Integer.parseInt(data[4]);
                boolean reserve = Boolean.parseBoolean(data[5]);
                int intervenantId = Integer.parseInt(data[6]);
                String nom = data[7];
                String prenom = data[8];
                String fonction = data[8];

                // Créer un objet Lieu
                Lieu lieu = new Lieu(adresse, capacite, reserve);

                // Créer un objet Intervenant
                Intervenant intervenant = new Intervenant(intervenantId, nom, prenom,fonction); // Vous pouvez ajouter une fonction si nécessaire

                // Créer une instance de Conference
                Conference conference = new Conference(duree, budget, lieu, conferenceId, intervenant);
                conferenceList.add(conference); // Ajouter la conférence à la liste observable
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
            int conferenceId = Integer.parseInt(ConferenceIDfield.getText());
            int duree = Integer.parseInt(dureeField.getText());
            double budget = Double.parseDouble(budgetField.getText());
            String adresse = adresseField.getText();
            int capacite = Integer.parseInt(capaciteField.getText());
            int intervenantId = Integer.parseInt(intervenantidfield.getText());
            String nom = nomField.getText();
            String prenom = prénomField.getText();
            String fonction = fonctionfield.getText();
            boolean reserve = yourComboBoxId.getValue().equals("Oui");

            // Créer un objet Lieu
            Lieu lieu = new Lieu(adresse, capacite, reserve);

            // Créer un objet Formateur
            Intervenant intervenant = new Intervenant(intervenantId, nom, prenom,fonction);

            // Créer une instance de Formation
            Conference conference = new Conference(duree, budget, lieu, conferenceId, intervenant);

            // Ajouter la formation à l'administrateur
            administrateur.ajouterconference(conference);
            
            conferenceList.add(conference);
            tableView.setItems(conferenceList); // Remplir la TableView
        
            // Afficher un message de succès
            showAlert("Conference ajoutée avec succès!", Alert.AlertType.INFORMATION);
            clearFields(); // Optionnel : pour vider les champs après ajout
            // Sauvegarder les conférences dans le fichier
        saveConferencesToFile();

        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer des valeurs valides.", Alert.AlertType.ERROR);
        } catch (ErreurEvent e) {
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    @FXML
private void handleRapportAction() {
    
    
    StringBuilder rapportGlobal = new StringBuilder();
    
    for (Conference conference : conferenceList) {
        rapportGlobal.append(conference.genererRapport()).append("\n\n"); // Utiliser genererRapport
    }
    
    // Afficher le rapport global dans une nouvelle fenêtre
    afficherRapportDansNouvelleFenetre(rapportGlobal.toString());
}

private void afficherRapportDansNouvelleFenetre(String rapport) {
    Stage rapportStage = new Stage();
    rapportStage.setTitle("Rapport de toutes les Conférences");

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
@FXML
     public void handleSupprimerAction (ActionEvent event) throws ErreurEvent {
    Conference selectedconference = tableView.getSelectionModel().getSelectedItem();
    if (selectedconference != null) {
        administrateur.supprimerConference(selectedconference);
        conferenceList.remove(selectedconference);
        tableView.setItems(conferenceList);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("suppression de conference");
        alert.setHeaderText(null);
        alert.setContentText("conference supprimée avec succès !");
        alert.showAndWait();
        // Sauvegarder les conférences dans le fichier
        saveConferencesToFile();
    } else {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("suppression de conference");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une conference à supprimer.");
        alert.showAndWait();
        
    }
    
}
    


    private void clearFields() {
        // Méthode pour vider les champs de texte après l'ajout
        ConferenceIDfield.clear();
        dureeField.clear();
        budgetField.clear();
        adresseField.clear();
        capaciteField.clear();
        intervenantidfield.clear();
        nomField.clear();
        prénomField.clear();
        yourComboBoxId.setValue(null); // Réinitialiser le ComboBox
    }

    private void showAlert(String message, Alert.AlertType alertType) {
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
}

