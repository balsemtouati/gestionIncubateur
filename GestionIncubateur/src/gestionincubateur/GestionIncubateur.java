/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package gestionincubateur;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class GestionIncubateur extends Application {
@Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML et le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        AnchorPane root = loader.load();

        // Définir la scène
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Geston D'incubateur");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }


    /*@Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/GestionEntrepreneur.fxml"));
        AnchorPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des entrepreneurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }}*/
    




/*public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/gestionConference.fxml"));
        AnchorPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des Conférences");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gestionlieu.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion de Lieu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/