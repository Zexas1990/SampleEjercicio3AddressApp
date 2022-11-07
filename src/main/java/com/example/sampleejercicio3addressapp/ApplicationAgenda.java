package com.example.sampleejercicio3addressapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationAgenda extends Application {

    private static final String APP_NAME = "Ejercicio3AdressApp!";
    private static final String APP_XML = "rootLayout.fxml";
    private static final String USER_MENU_XML = "PersonOverview.fxml";

    private ObservableList<Person> personData;

    private BorderPane rootLayout;
    private AnchorPane PersonOverview;

    public ApplicationAgenda(){
        initComponents();
    }

    private void initComponents(){
        personData = FXCollections.observableArrayList();

        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Ejercicio3AdressApp!");
        loadLayouts();
        Scene scene = new Scene(rootLayout);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }


    private void loadLayouts() throws IOException {

        rootLayout = new FXMLLoader(ApplicationAgenda.class.getResource(APP_XML)).load();
        PersonOverview = new FXMLLoader(ApplicationAgenda.class.getResource(USER_MENU_XML)).load();

        rootLayout.setCenter(PersonOverview);
    }

    public static void main(String[] args) {

        launch();
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }
}