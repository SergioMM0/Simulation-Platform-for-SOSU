package GUI.Controllers;


import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.StudentMOD;
import GUI.Util.CatAndSubC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentMainCTLL {

    @FXML
    private ComboBox<String> caseCategoryComboBox;

    @FXML
    private TextField caseNameField;

    @FXML
    private ComboBox<String> caseSubcategoryComboBox;

    @FXML
    private Tab caseTab;

    @FXML
    private TableView<Case> casesAssignedList;

    @FXML
    private TableView<Case> casesGradedList;

    @FXML
    private TextField cprField;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private TextArea descriptionOfConditionText;

    @FXML
    private TextField familyNameField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private Label groupLBL;

    @FXML
    private TextField heightField;

    @FXML
    private Label medicalHistoryLBL;

    @FXML
    private TextArea medicalHistoryTextArea;

    @FXML
    private TableColumn<Case, String> nameCOLCasesAssigned;

    @FXML
    private TableColumn<Case, String> nameCOLCasesGraded;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea newObservationTextArea;

    @FXML
    private Tab patientOverviewTab;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Label studentLBL;

    @FXML
    private TextField weightField;

    private User currentStudent;
    private Group currentGroup;
    private Patient currentPatient;
    private Case currentCase;
    private static SoftAlert softAlert;
    private static CatAndSubC catAndSubC;
    private StudentMOD model;
    private final String generalCSS = "";
    private ArrayList<Stage> listOfStages = new ArrayList<>();

    public StudentMainCTLL() {
        model = new StudentMOD();
        softAlert = SoftAlert.getInstance();
        catAndSubC = CatAndSubC.getInstance();
    }

    protected void initializeView() {
        getLogedGroup();
        setUpTabs();
        populateCasesAssigned();
        populateCasesGraded();
    }

    private void setUpTabs() {
        patientOverviewTab.setDisable(true);
        caseTab.setDisable(true);
    }

    private void blockPatientTab() {
        patientOverviewTab.setDisable(true);
        patientOverviewTab.setText("Patient info");
    }

    private void blockCaseTab() {
        caseTab.setDisable(true);
        caseTab.setText("Case info");
    }

    private void populateCasesAssigned() {
        try {
            casesAssignedList.getItems().addAll(model.getCasesAssignedTo(currentGroup));
            nameCOLCasesAssigned.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (DalException dalException) {
            softAlert.displayAlert(dalException.getMessage());
        }
    }

    private void populateCasesGraded() {
        try {
            casesGradedList.getItems().addAll(model.getCasesGradedOf(currentGroup));
            nameCOLCasesAssigned.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (DalException dalException) {
            softAlert.displayAlert(dalException.getMessage());
        }
    }

    @FXML
    private void categorySelected(MouseEvent event) {
        if (caseCategoryComboBox.getValue() != null) {
            caseSubcategoryComboBox.getItems().clear();
            caseSubcategoryComboBox.getItems().addAll(
                    CatAndSubC.getInstance().getSubcategoriesOf(caseCategoryComboBox.getValue()));
        }
    }


    @FXML
    private void caseAssignedIsSelected(MouseEvent event) {
        if (casesAssignedList.getSelectionModel().getSelectedItem() != null) {
            this.currentCase = casesAssignedList.getSelectionModel().getSelectedItem();

            try {
                this.currentPatient = model.getPatientOf(currentGroup, currentCase);
            } catch (DalException dalException) {
                softAlert.displayAlert(dalException.getMessage());
            }
            displayPatientInfo(currentPatient);
            displayCaseInfo(currentCase);
        }
    }

    @FXML
    private void caseGradedIsSelected(MouseEvent event) {
        if (casesGradedList.getSelectionModel().getSelectedItem() != null) {
            this.currentCase = casesGradedList.getSelectionModel().getSelectedItem();

            try {
                this.currentPatient = model.getPatientOf(currentGroup, currentCase);
            } catch (DalException dalException) {
                softAlert.displayAlert(dalException.getMessage());
            }
            displayPatientInfo(currentPatient);
            displayCaseInfo(currentCase);
        }
    }

    private void displayPatientInfo(Patient patient) {
        if (patientOverviewTab.isDisabled()) {
            patientOverviewTab.setDisable(false);
        }
        patientOverviewTab.setText(patient.getFirst_name());
        nameField.setText(patient.getFirst_name());
        familyNameField.setText(patient.getLast_name());
        dateOfBirthPicker.setValue(patient.getDateOfBirth());

        genderComboBox.getItems().clear();
        genderComboBox.getItems().addAll(model.getGenders());
        genderComboBox.getSelectionModel().select(
                genderComboBox.getItems().indexOf(patient.getGender()));
        //Selects in the gender combo box the gender that matches the gender of the patient
        weightField.setText(patient.getWeight());
        heightField.setText(patient.getHeight());
        cprField.setText(patient.getCpr());
        phoneNumberField.setText(patient.getPhoneNumber());
        medicalHistoryTextArea.setText(handleObservationsOfPatient(patient));
    }

    private void displayCaseInfo(Case selectedCase) {
        if (caseTab.isDisabled()) {
            caseTab.setDisable(false);
        }
        caseTab.setText(selectedCase.getName());
        caseCategoryComboBox.getItems().clear();
        caseSubcategoryComboBox.getItems().clear();

        String[] allCat = catAndSubC.getCategories();
        String[] allSubCat = catAndSubC.getSubcategoriesOf(selectedCase.getCategory());

        caseNameField.setText(selectedCase.getName());
        descriptionOfConditionText.setText(selectedCase.getConditionDescription());

        for (String cat : allCat) {
            caseCategoryComboBox.getItems().add(cat); //population of the categories
        }
        for (String subCat : allSubCat) {
            caseSubcategoryComboBox.getItems().add(subCat); // population of the subcategories of the category
        }
        //population of case in case info when selected in general view

        caseCategoryComboBox.getSelectionModel().select(
                caseCategoryComboBox.getItems().indexOf(selectedCase.getCategory()));
        //selects the category specified for the case

        caseSubcategoryComboBox.getSelectionModel().select(
                caseSubcategoryComboBox.getItems().indexOf(selectedCase.getSubCategory()));
        //selects the subcategory specified for the case
    }

    @FXML
    private void newObservation(ActionEvent event) {
        if (!newObservationTextArea.getText().isEmpty()) {
            String observation = LocalDate.now() + ": " + newObservationTextArea.getText();
            try {
                model.addObservationToPatient(observation, currentPatient);
            } catch (DalException dalException) {
                softAlert.displayAlert(dalException.getMessage());
            }
            currentPatient.addObservation(observation);
            medicalHistoryTextArea.setText(handleObservationsOfPatient(currentPatient));
            newObservationTextArea.clear();
        }
    }

    private String handleObservationsOfPatient(Patient patient) {
        StringBuilder sb = new StringBuilder();
        for (String observation : patient.getObservationsList()) {
            sb.append(observation).append("\n");
        }
        return sb.toString();
    }

    @FXML
    void saveChangesOnCase(ActionEvent event) {
        if (caseFieldsAreFilled()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this case?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    currentCase.setName(caseNameField.getText());
                    currentCase.setConditionDescription(descriptionOfConditionText.getText());
                    currentCase.setCategory(caseCategoryComboBox.getValue());
                    currentCase.setSubCategory(caseSubcategoryComboBox.getValue());
                    model.updateCase(currentCase);
                    model.updateCaseInTable(currentCase);
                    refreshCasesAssignedList();
                } catch (DalException dalException) {
                    dalException.printStackTrace();
                    softAlert.displayAlert(dalException.getMessage());
                }
            }
        }
    }

    protected void refreshCasesAssignedList() {
        casesAssignedList.getItems().clear();
        casesAssignedList.getItems().addAll(model.getObservableCasesAssigned());
    }

    private boolean caseFieldsAreFilled() {
        if (caseNameField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a valid name for the case");
            return false;
        } else if (descriptionOfConditionText.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a valid description for the case");
            return false;
        } else return true;
    }


    @FXML
    private void evaluateCase(ActionEvent event) {
        openView("GUI/Views/EvaluateCase.fxml", generalCSS, "Evaluate case", 880, 660, false);
    }

    @FXML
    private void seeGradedCase(ActionEvent event) {
        openView("GUI/Views/EvaluateCase.fxml", generalCSS, "Evaluate case", 880, 660, false);
        //TODO Lock all the elements in questionnaire view to prevent modifications(as it is already graded)
    }

    @FXML
    void updatePatient(ActionEvent event) {
        if (patientFieldsAreFilled()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this patient?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    currentPatient.setFirst_name(nameField.getText());
                    currentPatient.setLast_name(familyNameField.getText());
                    currentPatient.setDateOfBirth(dateOfBirthPicker.getValue());
                    currentPatient.setGender(genderComboBox.getValue());
                    currentPatient.setWeight(weightField.getText());
                    currentPatient.setHeight(heightField.getText());
                    currentPatient.setCpr(cprField.getText());
                    currentPatient.setPhoneNumber(phoneNumberField.getText());
                    model.updatePatient(currentPatient);
                } catch (DalException dalException) {
                    softAlert.displayAlert(dalException.getMessage());
                }
            }
        }
    }

    private boolean patientFieldsAreFilled() {
        if (nameField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a new name for the patient");
            return false;
        } else if (familyNameField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a new family name for the patient");
            return false;
        } else if (dateOfBirthPicker.getValue().isAfter(LocalDate.now()) || dateOfBirthPicker.getValue() == null) {
            softAlert.displayAlert("Please introduce a new date of birth for the patient");
            return false;
        } else if (genderComboBox.getSelectionModel().isEmpty() || genderComboBox.hasProperties()) {
            softAlert.displayAlert("Please introduce a new gender combo box for the patient");
            return false;
        } else if (weightField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a new valid weight for the patient");
            return false;
        } else if (heightField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a new valid height for the patient");
            return false;
        } else if (cprField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a valid CPR for the patient");
            return false;
        } else if (phoneNumberField.getText().isEmpty()) {
            softAlert.displayAlert("Please introduce a valid phone number for the patient");
            return false;
        } else return true;
    }


    public void setUser(User user) {
        this.currentStudent = user;
    }

    public void getLogedGroup() {
        try {
            this.currentGroup = model.getGroupOf(currentStudent);
        } catch (DalException dalException) {
            softAlert.displayAlert(dalException.getMessage());
        }
    }

    private void openView(String resource, String css, String title, int width, int height, boolean resizable) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        if (resource.equals("GUI/Views/EvaluateCase.fxml")) {
            //loader.<StudentQuestionCTL>getController().setUser(logedUser);
            //loader.<StudentQuestionCTL>getController().setGroup(logedGroup);
            //loader.<StudentQuestionCTL>getController().setCase();
            //loader.<StudentQuestionCTL>getController().setPatient();
        }
        root.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle(title);
        listOfStages.add(stage);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(resizable);
        stage.showAndWait();
    }

    private void closeWindows() {
        for (Stage listOfStage : listOfStages) {
            listOfStage.close();
        }
        listOfStages.clear();
        model.clearLists();
    }
}
