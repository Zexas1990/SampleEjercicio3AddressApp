package com.example.sampleejercicio3addressapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogUserEditController {
    @FXML
    private TextField txtfFirstName;

    @FXML
    private TextField txtfLastName;

    @FXML
    private TextField txtfStreet;

    @FXML
    private TextField txtfCity;

    @FXML
    private TextField txtfPostalCode;

    @FXML
    private TextField txtfBirthday;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(txtfFirstName.getText());
            person.setLastName(txtfLastName.getText());
            person.setStreet(txtfStreet.getText());
            person.setPostalCode(Integer.parseInt(txtfPostalCode.getText()));
            person.setCity(txtfCity.getText());
            person.setBirthday(DateUtil.parse(txtfBirthday.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        PersonPolicy policy = new PersonPolicy();
        String errorMessage = policy.test(
                txtfFirstName.getText(),
                txtfLastName.getText(),
                txtfStreet.getText(),
                txtfCity.getText(),
                txtfPostalCode.getText(),
                txtfBirthday.getText()
        );
        if (errorMessage.length() == 0)
            return true;
        else {
            error("Invalid Fields", "Please correct invalid fields", errorMessage);
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtfFirstName.setText(person.getFirstName());
        txtfLastName.setText(person.getLastName());
        txtfStreet.setText(person.getStreet());
        txtfCity.setText(person.getCity());
        txtfPostalCode.setText(Integer.toString(person.getPostalCode()));
        txtfBirthday.setText(DateUtil.format(person.getBirthday()));
        txtfBirthday.setPromptText("dd/mm/yyyy");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void showAndWait() {
        dialogStage.showAndWait();
    }

    public void error(String title, String header, String msg) {
        ApplicationAgenda.showAlert(dialogStage, Alert.AlertType.ERROR, title, header, msg);
    }
}
