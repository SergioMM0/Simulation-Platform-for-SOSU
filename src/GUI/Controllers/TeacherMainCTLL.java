package GUI.Controllers;

import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TeacherMainCTLL {

    @FXML
    private ComboBox<?> caseCategoryComboBox;

    @FXML
    private TextField caseNameField;

    @FXML
    private ComboBox<?> caseSubcategoryComboBox;

    @FXML
    private Label casesAssignedLBL;

    @FXML
    private TableView<Case> casesAssignedList;

    @FXML
    private TableColumn<Case,String> nameColCases;

    @FXML
    private Label casesGradedLBL;

    @FXML
    private TableView<Case> casesGradedList;

    @FXML
    private TableColumn<Case,String> nameColCasesGraded;

    @FXML
    private TableView<Case> casesListGV;

    @FXML
    private TableColumn<Group,String>nameColCasesGV;

    @FXML
    private TextField causalConditionText;

    @FXML
    private TextField causalDiagnoseText;

    @FXML
    private TextField causeText;

    @FXML
    private TextField citizenGoalText;

    @FXML
    private TextField descriptionOfConditionText;

    @FXML
    private TableColumn<?, ?> groupNameCol;

    @FXML
    private Label groupNameLBL;

    @FXML
    private TableView<Group> groupTableGV;

    @FXML
    private Label groupsLBL;

    @FXML
    private TableView<Group> groupsTable;

    @FXML
    private TableColumn<?, ?> hasGroupCol;

    @FXML
    private Label medicalHistoryLBL;

    @FXML
    private TextArea medicalHistoryTextArea;

    @FXML
    private TableColumn<Group, String> nameColGroupsGV;

    @FXML
    private TableColumn<?, ?> participantsCol;

    @FXML
    private Label patientAlcoholLBL;

    @FXML
    private Label patientBloodTypeLBL;

    @FXML
    private Label patientCPRLBL;

    @FXML
    private Label patientDateOfBirthLBL;

    @FXML
    private Label patientDietLBL;

    @FXML
    private Label patientExerciseLBL;

    @FXML
    private Label patientFamilyNameLBL;

    @FXML
    private Label patientGenderLBL;

    @FXML
    private Label patientHeightLBL;

    @FXML
    private Label patientNameLBL;

    @FXML
    private Label patientPhoneNumberLBL;

    @FXML
    private Label patientTobaccoLBL;

    @FXML
    private Label patientWeightLBL;

    @FXML
    private TableView<Patient> patientsListGV;

    @FXML
    private TableColumn<Patient,String> nameColPatientsGV;

    @FXML
    private TableColumn<Group,String> studentNameCol;

    @FXML
    private Label studentNamesLBL;

    @FXML
    private Label studentsLBL;

    @FXML
    private TableView<?> studentsTable;

    private User logedUser;

    public void setUser(User user){
        this.logedUser = user;
    }

    private void populateGroupsTable(){

    }

    @FXML
    void addNewCase(ActionEvent event) {

    }

    @FXML
    void addNewPatient(ActionEvent event) {

    }

    @FXML
    void addNewStudent(ActionEvent event) {

    }

    @FXML
    void addStudentToGroup(ActionEvent event) {

    }

    @FXML
    void assignCaseToGroup(ActionEvent event) {

    }

    @FXML
    void assignNewCaseToGroup(ActionEvent event) {

    }

    @FXML
    void createGroup(ActionEvent event) {

    }

    @FXML
    void deleteCase(ActionEvent event) {

    }

    @FXML
    void deleteGroup(ActionEvent event) {

    }

    @FXML
    void deletePatient(ActionEvent event) {

    }

    @FXML
    void deleteStudent(ActionEvent event) {

    }

    @FXML
    void duplicateCase(ActionEvent event) {

    }

    @FXML
    void duplicatePatient(ActionEvent event) {

    }

    @FXML
    void editGroup(ActionEvent event) {

    }

    @FXML
    void editStudent(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void markCaseAsGraded(ActionEvent event) {

    }

    @FXML
    void newObservation(ActionEvent event) {

    }

    @FXML
    void openGradeCase(ActionEvent event) {

    }

    @FXML
    void openGradedCase(ActionEvent event) {

    }

    @FXML
    void openManageGroup(ActionEvent event) {

    }

    @FXML
    void removeStudentFromGroup(ActionEvent event) {

    }

    @FXML
    void saveChangesOnCase(ActionEvent event) {

    }

    @FXML
    void unassignSelectedCase(ActionEvent event) {

    }

    @FXML
    void unmarkCaseAsGraded(ActionEvent event) {

    }

}
