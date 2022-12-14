package com.example.sampleejercicio3addressapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationAgenda extends Application {

    private static final String APP_NAME = "Ejercicio3AdressApp!";
   // private static final String LOGO = "";
    private static final String APP_XML = "rootLayout.fxml";
    private static final String USER_MENU_XML = "PersonOverview.fxml";
    private static final String MENU_XML = "userEdit.fxml";

    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 500;
    private ObservableList<Person> personData;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane PersonOverview;

    public ApplicationAgenda(){
        initComponents();
    }

    private void initComponents(){
        personData = FXCollections.observableArrayList();
        // TODO DEBUG
        personData.add(new Person("Hans", "Muster", "Musterstrasse 1", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Ruth", "Mueller", "Musterstrasse 2", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Heinz", "Kurz", "Musterstrasse 3", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Cornelia", "Meier", "Musterstrasse 4", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Werner", "Meyer", "Musterstrasse 5", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Lydia", "Kunz", "Musterstrasse 6", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Anna", "Best", "Musterstrasse 7", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Stefan", "Meier", "Musterstrasse 8", 12345, "Musterstadt", 2000, 1, 1));
        personData.add(new Person("Martin", "Mueller", "Musterstrasse 9", 12345, "Musterstadt", 2000, 1, 1));
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.getIcons().add(new Image(LOGO));
        stage.setTitle("Ejercicio3AdressApp!");
        loadLayouts();
        Scene scene = new Scene(rootLayout);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    private void loadLayouts() throws IOException {

        FXMLLoader appLoader = new FXMLLoader(ApplicationAgenda.class.getResource(APP_XML));
        rootLayout = appLoader.load();
        FXMLLoader userMenuLoader = new FXMLLoader(ApplicationAgenda.class.getResource(USER_MENU_XML));
        PersonOverview = userMenuLoader.load();

        ControllerAgenda controller = userMenuLoader.getController();
        controller.setApplicationAgenda(this);

        rootLayout.setCenter(PersonOverview);
    }

    public static void main(String[] args) {

        launch();
    }

    public ObservableList<Person> getPersonData() {

        return personData;
    }

    public boolean showPersonEditDialog(Person tempPerson) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ApplicationAgenda.class.getResource(MENU_XML)
            );

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);

            DialogUserEditController userEdit = loader.getController();
            userEdit.setDialogStage(dialogStage);
            userEdit.setPerson(person);
            userEdit.showAndWait();
            return userEdit.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void showAlert(Window window, Alert.AlertType type, String title, String header, String msg) {
        Alert alert = new Alert(type);
        alert.initOwner(window);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        showAlert(primaryStage, type, title, header, content);
    }

    public void warn(String title, String header, String msg) {
        showAlert(Alert.AlertType.WARNING, title, header, msg);
    }

    public void error(String title, String header, String msg) {
        showAlert(Alert.AlertType.ERROR, title, header, msg);
    }
}