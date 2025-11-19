package Controller;

import Model.Gestion_Projets.Ressource;
import Model.Gestion_Utilisateur.Administrateur;
import Model.Gestion_Utilisateur.Entrepreneur;
import Model.Gestion_Utilisateur.Investisseur;
import gestionincubateur.ErreurEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterRessourceController {

    @FXML
    private Button ButtonAcceuil;
    
    @FXML
    private Button totalButton;
    @FXML
    private TextField ressourceIDfieldrech;
@FXML
private TextField totalMontant;

    @FXML
    private TableColumn<Ressource, Integer> CINcolumn;

    @FXML
    private TableColumn<Ressource, Integer> Montantcolumn;

    @FXML
    private TextField Montantfield;

    @FXML
    private TableView<Ressource> RessourceTableView;

    @FXML
    private Button ajouterResourceButton;

    @FXML
    private TableColumn<Ressource, String> emailInvcolumn;

    @FXML
    private ComboBox<Investisseur> investisseurComboBox;

    @FXML
    private TableColumn<Ressource, Integer> investisseurIDcolumn;

    @FXML
    private TableColumn<Ressource, Integer> nbinvestissementcolumn;

    @FXML
    private TableColumn<Ressource, String> nominvcolumn;

    @FXML
    private TableColumn<Ressource, String> prenominvcolumn;

    @FXML
    private TableColumn<Ressource, Integer> ressourceIDcolumn;

    @FXML
    private TextField ressourceIDfield;

    @FXML
    private Label resultLabel;

    @FXML
    private Button supprimerButton;
    @FXML
    private Button minimizeButton;

    @FXML
    private Button closeButton;
    
   @FXML
    private Button rechercherButton;
    
    private Administrateur administrateur;
    private ObservableList<Investisseur> investorList;

    private ObservableList<Ressource> ressourceList; // Liste observable pour la table

 public AjouterRessourceController() {
        // Initialisation de l'administrateur
        this.administrateur = new Administrateur(1, 1234, "NomAdmin", "PrenomAdmin", 30, "AdresseAdmin");
        this.ressourceList = FXCollections.observableArrayList();
    }
   public void setInvestisseurs(List<Investisseur> investisseurs) {
    if (investisseurComboBox != null && investisseurs != null) {
        investisseurComboBox.setItems(FXCollections.observableArrayList(investisseurs));

        // Optional: personnaliser l'affichage dans le ComboBox
        investisseurComboBox.setCellFactory(listView -> new ListCell<Investisseur>() {
            @Override
            protected void updateItem(Investisseur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString()); // Utilise la méthode toString() pour afficher le texte
                }
            }
        });
        investisseurComboBox.setButtonCell(new ListCell<Investisseur>() {
            @Override
            protected void updateItem(Investisseur item, boolean empty) {
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
    public void initialize() throws ErreurEvent {
        // Initialiser les colonnes de la table
        ressourceIDcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRessourceID()).asObject());
        Montantcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMontant()).asObject());
        prenominvcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInv().getPrenom()));
        nominvcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInv().getNom()));
        CINcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInv().getCin()).asObject());
        emailInvcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInv().getEmail()));
        investisseurIDcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInv().getInvestisseurID()).asObject());
        nbinvestissementcolumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInv().getNbInvestissements()).asObject());
 // Initialiser la liste des entrepreneurs
    investorList = FXCollections.observableArrayList();
        // Remplir le ComboBox avec les investisseurs (vous devez le faire dans votre logique)
        investisseurComboBox.setItems(getInvestisseurs()); // Méthode fictive à créer pour obtenir les investisseurs
        RessourceTableView.setItems(ressourceList);
        // Charger les ressources depuis le fichier
        loadInvestorsFromFile();
      // Remplir le ComboBox avec les entrepreneurs
        setInvestisseurs(investorList);
    
        loadRessourcesFromFile();

        // Action pour le bouton Ajouter Ressource
        ajouterResourceButton.setOnAction(this::handleAjouterRessource);
        supprimerButton.setOnAction(this::handlesupprimerRessource);
        
        ressourceIDfieldrech.textProperty().addListener((observable, oldValue, newValue) -> {
            // Vérifier si le nouveau texte n'est pas vide
            if (!newValue.isEmpty()) {
                handleRechercherRessource();
            } else {
                // Si le champ est vide, effacer le label de résultat et la sélection
                resultLabel.setText("");
                RessourceTableView.getSelectionModel().clearSelection();
            }
        });
    }
    private static final String FILE_PATH = "C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\ressources.txt"; // Chemin du fichier
    private void saveRessourcesToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (Ressource ressource : ressourceList) {
            writer.write(ressource.getRessourceID() + "," +
                         ressource.getMontant() + "," +
                         ressource.getInv().getNom() + "," +
                         ressource.getInv().getPrenom() + "," +
                         ressource.getInv().getCin() + "," +
                         ressource.getInv().getEmail() + "," +
                         ressource.getInv().getInvestisseurID() + "," +
                         ressource.getInv().getNbInvestissements());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    private void loadRessourcesFromFile() throws ErreurEvent {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
        return; // Si le fichier n'existe pas, ne rien faire
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 8) { // Assurez-vous que le nombre de données correspond
                int ressourceID = Integer.parseInt(data[0]);
                int montant = Integer.parseInt(data[1]);
                String nomProprietaire = data[2];
                String prenomProprietaire = data[3];
                int cin = Integer.parseInt(data[4]);
                String email = data[5];
                int investisseurId = Integer.parseInt(data[6]);
                int nbInvestissements = Integer.parseInt(data[7]);

                Investisseur investisseur = new Investisseur(nomProprietaire, prenomProprietaire, cin, email, investisseurId, nbInvestissements);
                Ressource ressource = new Ressource(ressourceID, montant, investisseur);
                
                // Ajouter la ressource à la liste observable
                ressourceList.add(ressource);
                
                // Ajouter la ressource au tableau tab de l'administrateur
                administrateur.ajouterRessource(ressource); // Assurez-vous que cette méthode ajoute la ressource à tab
            }
        }
        // Mettre à jour le TableView après avoir chargé les ressources
        RessourceTableView.setItems(ressourceList);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private void loadInvestorsFromFile() {
    File file = new File("C:\\Users\\touat\\Desktop\\JavaProjects\\GestionIncubateur\\src\\investisseurs.txt");
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
  
    public void handleAjouterRessource(ActionEvent event) {
    try {
        int ressourceID = Integer.parseInt(ressourceIDfield.getText());
        int montant = Integer.parseInt(Montantfield.getText());
        Investisseur selectedInvestisseur = investisseurComboBox.getValue();

        if (selectedInvestisseur != null) {
            Ressource ressource = new Ressource(ressourceID, montant, selectedInvestisseur);
            
            // Appel à la méthode d'ajout de la classe Administrateur
            administrateur.ajouterRessource(ressource);

            // Ajouter la ressource à la liste observable et mettre à jour la table
            ressourceList.add(ressource);
            RessourceTableView.setItems(ressourceList);
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ajout Ressource");
        alert.setHeaderText(null);
        alert.setContentText("Ressource ajoutée avec succès !");
        alert.showAndWait();
        saveRessourcesToFile();
        } 

        clearFields();
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ajout Ressource");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer des valeurs valides.");
        alert.showAndWait();
    } catch (ErreurEvent e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ajout Ressource");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}

     public void handlesupprimerRessource(ActionEvent event) {
    Ressource selectedRessource = RessourceTableView.getSelectionModel().getSelectedItem();
    if (selectedRessource != null) {
        try {
            administrateur.supprimerRessource(selectedRessource);
            ressourceList.remove(selectedRessource);
            RessourceTableView.setItems(ressourceList);
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression de  Ressource");
        alert.setHeaderText(null);
        alert.setContentText("Ressource supprimée avec succès !");
        alert.showAndWait();
        saveRessourcesToFile();
        } catch (ErreurEvent e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression de  Ressource");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        }
    }
}

    private void clearFields() {
        // Réinitialiser les champs de texte
        ressourceIDfield.clear();
        Montantfield.clear();
        investisseurComboBox.getSelectionModel().clearSelection(); // Réinitialiser la sélection du ComboBox
    }

    // Méthode fictive pour obtenir la liste des investisseurs
    private ObservableList<Investisseur> getInvestisseurs() {
        // Remplacez ceci par votre logique pour obtenir la liste des investisseurs
        return FXCollections.observableArrayList(); // Exemple vide
    }
public void handleCalculerTotalMontant(ActionEvent event) {
    double totalMontants = administrateur.calculerTotalMontant();
    totalMontant.setText(String.valueOf(totalMontants)); // Convertir le double en String
}
public void handleRechercherRessource() {
    try {
        // Effacer les sélections précédentes
        RessourceTableView.getSelectionModel().clearSelection();

        // Réinitialiser le style de chaque ligne
        RessourceTableView.getItems().forEach(ressource -> {
            RessourceTableView.getStyleClass().remove("highlight");
        });

        // Vérifier si le champ n'est pas vide
        if (!ressourceIDfieldrech.getText().isEmpty()) {
            int ressourceID = Integer.parseInt(ressourceIDfieldrech.getText());
            Optional<Ressource> ressourceOpt = trouverRessourceParID(ressourceID);

            if (ressourceOpt.isPresent()) {
                Ressource ressource = ressourceOpt.get();

                // Trouver l'index de la ressource dans la liste
                int index = ressourceList.indexOf(ressource);
                if (index >= 0) {
                    // Sélectionner la ligne correspondante dans le TableView
                    RessourceTableView.getSelectionModel().select(index);
                    RessourceTableView.scrollTo(index); // Optionnel : faire défiler jusqu'à la ligne sélectionnée

                    // Appliquer un style CSS pour mettre en surbrillance la ligne
                    RessourceTableView.setRowFactory(tv -> new TableRow<Ressource>() {
                        @Override
                        protected void updateItem(Ressource item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null && item.equals(ressource)) {
                                setStyle("-fx-background-color: lightblue;"); // Couleur de surbrillance
                            } else {
                                setStyle(""); // Réinitialiser le style
                            }
                        }
                    });
                }
            } else {
                // Si aucune ressource n'est trouvée, effacer la sélection
                RessourceTableView.getSelectionModel().clearSelection();
            }
        }
    } catch (NumberFormatException e) {
        // Gérer le cas où l'ID n'est pas un nombre valide
        RessourceTableView.getSelectionModel().clearSelection(); // Effacer la sélection si l'ID n'est pas valide
    }
}



// Méthode fictive pour trouver une ressource par ID
private Optional<Ressource> trouverRessourceParID(int id) {
    // Implémentez votre logique de recherche ici
    return ressourceList.stream().filter(r -> r.getRessourceID() == id).findFirst(); // Exemple de recherche
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
            @FXML
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
}