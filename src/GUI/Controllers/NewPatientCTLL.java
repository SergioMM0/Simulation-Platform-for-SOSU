package GUI.Controllers;

import BE.Patient;
import BE.User;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
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
import java.time.LocalDate;
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
    private User user;
    private TeacherMainCTLL teacherMainCTLL;

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
        if(fieldsAreFiled()){
            try{
                Patient patient = new Patient(
                        0,
                        nameField.getText(),
                        familyNameField.getText(),
                        dateOfBirthPicker.getValue(),
                        genderComboBox.getValue(),
                        weightField.getText(),
                        heightField.getText(),
                        cprField.getText(),
                        phoneNumberField.getText(),
                        bloodTypeComboBox.getValue(),
                        exerciseComboBox.getValue(),
                        dietComboBox.getValue(),
                        alcoholComboBox.getValue(),
                        tobaccoComboBox.getValue(),
                        observationsField.getText(),
                        user.getSchoolID()
                );
                model.createPatient(patient);
                closeWindow();
                teacherMainCTLL.addPatientToList(patient);
            } catch(DalException dalException){
                new SoftAlert(dalException.getMessage());
            }
        }
    }

    private boolean fieldsAreFiled() {
        if (nameField.getText().isEmpty()) {
            new SoftAlert("Please introduce the name of the patient");
            return false;
        } else if (familyNameField.getText().isEmpty()) {
            new SoftAlert("Please introduce the family name of the patient");
            return false;
        } else if (dateOfBirthPicker.getValue().isAfter(LocalDate.now()) || dateOfBirthPicker.getValue() == null) {
            new SoftAlert("Please select a correct date of birth for the patient");
            return false;
        } else if (genderComboBox.getSelectionModel().isEmpty() || genderComboBox.hasProperties()) {
            new SoftAlert("Please introduce the gender of the patient");
            return false;
        } else if (weightField.getText().isEmpty()) {
            new SoftAlert("Please introduce the weight of the patient");
            return false;
        } else if (heightField.getText().isEmpty()) {
            new SoftAlert("Please introduce the height of the patient");
            return false;
        } else if (cprField.getText().isEmpty()) {
            new SoftAlert("Please introduce the CPR of the patient");
            return false;
        } else if (phoneNumberField.getText().isEmpty()) { //Phone number is optional
            phoneNumberField.setText("No phone number");
            return false;
        } else if (bloodTypeComboBox.getSelectionModel().isEmpty()) {
            new SoftAlert("Please introduce the blood type of the patient");
            return false;
        } else if (exerciseComboBox.getSelectionModel().isEmpty()) {
            new SoftAlert("Please introduce the amount of exercise the patient does");
            return false;
        } else if (dietComboBox.getSelectionModel().isEmpty()) {
            new SoftAlert("Please introduce a statement for the diet of the patient");
            return false;
        } else if (alcoholComboBox.getSelectionModel().isEmpty()) {
            new SoftAlert("Please introduce an statement for the alcohol consumption of the patient");
            return false;
        } else if (tobaccoComboBox.getSelectionModel().isEmpty()) {
            new SoftAlert("Please introduce an statement for the tobacco consumption of the patient");
            return false;
        } else if (observationsField.getText().isEmpty()) { //including an observation when creation a patient can be optional
            observationsField.setText("Patient included in the system with no observations");
            return false;
        }
        else return true;
    }

    private void closeWindow(){
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();
    }

    public void setController(TeacherMainCTLL teacherMainCTLL) {
        this.teacherMainCTLL = teacherMainCTLL;
    }

    public void setUser(User logedUser) {
        this.user = logedUser;
    }
}

