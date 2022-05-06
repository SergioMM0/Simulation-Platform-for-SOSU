package GUI.Controllers;

import GUI.Models.NewPatientMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPatientCTLL implements Initializable {

    @FXML
    private ComboBox<String> alcoholComboBox;

    @FXML
    private ComboBox<String> bloodTypeComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cprField;

    @FXML
    private Button createButton;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private ComboBox<String> dietComboBox;

    @FXML
    private ComboBox<String> exerciseComboBox;

    @FXML
    private TextField familyNameField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField heightField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField observationsField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private ComboBox<String> tobaccoComboBox;

    @FXML
    private TextField weightField;

    private NewPatientMOD model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new NewPatientMOD();
        populateComboBoxes();
    }

    private void populateComboBoxes() {
        genderComboBox.getItems().addAll(model.getGenders());
        bloodTypeComboBox.getItems().addAll(model.getBloodTypes());
        exerciseComboBox.getItems().addAll(model.getExerciseOptions());
        dietComboBox.getItems().addAll(model.getDietOptions());
        alcoholComboBox.getItems().addAll(model.getAlcoholOptions());
        tobaccoComboBox.getItems().addAll(model.getTobaccoOptions());
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void createPatient(ActionEvent event) {

    }

    private void closeWindow(){
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }
}

