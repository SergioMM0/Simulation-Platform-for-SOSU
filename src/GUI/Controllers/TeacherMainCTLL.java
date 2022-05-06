package GUI.Controllers;

import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private static final String generalCSS = "";

    public void setUser(User user){
        this.logedUser = user;
    }

    private void populateGroupsTable(){

    }

    @FXML
    void addNewCase(ActionEvent event) {
        openView("GUI/Views/CreateCase.fxml",generalCSS,"Create new case",860,660,false);
    }

    @FXML
    void addNewPatient(ActionEvent event) {
        openView("GUI/Views/CreatePatient.fxml",generalCSS,"Create new patient",500,650,false);
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

    private void openView(String resource, String css, String title, int width,int height,boolean resizable){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(resource));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException e){
            e.printStackTrace();
        }
        assert root != null;
        root.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root,width,height));
        stage.setResizable(resizable);
        stage.show();
    }

    private void closeWindow(){
        Stage st = (Stage) groupTableGV.getScene().getWindow();
        st.close();
    }

}
