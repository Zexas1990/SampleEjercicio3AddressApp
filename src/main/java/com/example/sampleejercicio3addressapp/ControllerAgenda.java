package com.example.sampleejercicio3addressapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerAgenda {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;


    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

//  ATRIBUTOS
    private ApplicationAgenda ApplicationAgenda;

    public ControllerAgenda() {
    }


    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        personTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> updateUser(newValue)
        );
        updateUser(null);
    }

    public void setApplicationAgenda(ApplicationAgenda ApplicationAgenda) {
        this.ApplicationAgenda = ApplicationAgenda;
        personTable.setItems(ApplicationAgenda.getPersonData());
    }

    private void updateUser(Person p) {
        if (p == null) {
            firstNameLabel.getParent().setVisible(false);
            return;
        }
        firstNameLabel.getParent().setVisible(true);

        firstNameLabel.setText(p.getFirstName());
        lastNameLabel.setText(p.getLastName());
        streetLabel.setText(p.getStreet());
        postalCodeLabel.setText(Integer.toString(p.getPostalCode()));
        cityLabel.setText(p.getCity());
        birthdayLabel.setText(DateUtil.format(p.getBirthday()));
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = ApplicationAgenda.showPersonEditDialog(tempPerson);
        if (okClicked)
            ApplicationAgenda.getPersonData().add(tempPerson);
    }


    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            ApplicationAgenda.warn(
                    "No Person Selected",
                    "Please select a person in the table.",
                    ""
            );
            return;
        }
        boolean ok = ApplicationAgenda.showPersonEditDialog(selectedPerson);
        if (ok)
            updateUser(selectedPerson);
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            ApplicationAgenda.error("No selection", "No person selected",
                    "Please select a person in the table.");
            return;
        }
        personTable.getItems().remove(selectedIndex);
    }

}