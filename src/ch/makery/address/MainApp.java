/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address;

import ch.makery.address.view.PersonOverviewController;
import ch.makery.address.model.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ramon
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Person(new SimpleStringProperty("Hans"), new SimpleStringProperty("Muster")));
        personData.add(new Person(new SimpleStringProperty("Ruth"), new SimpleStringProperty("Mueller")));
        personData.add(new Person(new SimpleStringProperty("Heinz"), new SimpleStringProperty("Kurz")));
        personData.add(new Person(new SimpleStringProperty("Cornelia"), new SimpleStringProperty("Meier")));
        personData.add(new Person(new SimpleStringProperty("Werner"), new SimpleStringProperty("Meyer")));
        personData.add(new Person(new SimpleStringProperty("Lydia"), new SimpleStringProperty("Kunz")));
        personData.add(new Person(new SimpleStringProperty("Anna"), new SimpleStringProperty("Best")));
        personData.add(new Person(new SimpleStringProperty("Stefan"), new SimpleStringProperty("Meier")));
        personData.add(new Person(new SimpleStringProperty("Martin"), new SimpleStringProperty("Mueller")));
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        initRootLayout();
        showPersonOverview();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    private void showPersonOverview() {
        try {
            // Load person overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            //Set person overview into the center of root layout
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
