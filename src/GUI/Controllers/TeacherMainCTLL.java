package GUI.Controllers;

import BE.*;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.TeacherMainMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMainCTLL {

    @FXML
    private ComboBox<Category> caseCategoryComboBox;

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
    private TableView<Patient> patientsListGV;
    @FXML
    private ComboBox<String> alcoholComboBox;

    @FXML
    private ComboBox<String> bloodTypeComboBox;

    @FXML
    private TextField cprField;

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
    private final String generalCSS = "";
    private TeacherMainMOD model;

    public void setUser(User user){
        logedUser = user;
    }

    public TeacherMainCTLL(){
        model = new TeacherMainMOD();
    }

    public void initializeView() {
        populateGroupsTable();
    }

    private void populateGroupsTable() {
        try{
            groupTableGV.getItems().addAll(model.getAllGroups(logedUser.getSchoolID()));
            nameColGroupsGV.setCellValueFactory(new PropertyValueFactory<>("name")); //Might be with the first letter in capital
        }catch (DalException dalException){
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }

    private void populateCasesTable() {
        try{
            casesListGV.getItems().addAll(model.getAllCases(logedUser.getSchoolID()));
            nameColCasesGV.setCellValueFactory(new PropertyValueFactory<>("name"));
        }catch (DalException dalException){
            new SoftAlert(dalException.getMessage());
        }
    }

    private void populatePatientsTable(){
        try{
            patientsListGV.getItems().addAll(model.getAllPatients(logedUser.getSchoolID()));
            nameColPatientsGV.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        }catch (DalException dalException){
            new SoftAlert(dalException.getMessage());
        }
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
    void groupIsSelected(MouseEvent event) {

    }

    @FXML
    void caseIsSelected(MouseEvent event) {
        try{
            caseNameField.setText(casesListGV.getSelectionModel().getSelectedItem().getName());
            caseCategoryComboBox.getItems().addAll(model.getAllCategories());
        }catch (DalException dalException){
            new SoftAlert(dalException.getMessage());
        }
    }

    @FXML
    void patientIsSelected(MouseEvent event) {
        nameField.setText(patientsListGV.getSelectionModel().getSelectedItem().getFirst_name());
        familyNameField.setText(patientsListGV.getSelectionModel().getSelectedItem().getLast_name());
        dateOfBirthPicker.setValue(patientsListGV.getSelectionModel().getSelectedItem().getDateOfBirth());

        genderComboBox.getItems().addAll(model.getGenders()); //TODO Update
        genderComboBox.getSelectionModel().select(
                genderComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getGender()));
        //Selects in the gender combo box the gender that matches the gender of the patient
        weightField.setText(patientsListGV.getSelectionModel().getSelectedItem().getWeight());
        heightField.setText(patientsListGV.getSelectionModel().getSelectedItem().getHeight());
        cprField.setText(patientsListGV.getSelectionModel().getSelectedItem().getCpr());
        phoneNumberField.setText(patientsListGV.getSelectionModel().getSelectedItem().getPhoneNumber());
        bloodTypeComboBox.getItems().addAll(model.getBloodTypes()); //TODO Update
        bloodTypeComboBox.getSelectionModel().select(
                bloodTypeComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getBloodType()));
        //Selects in the bloodType combo box the gender that matches the bloodType of the patient
        exerciseComboBox.getItems().addAll(model.getExerciseOptions()); //TODO Update
        exerciseComboBox.getSelectionModel().select(
                exerciseComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getExercise()));
        //Same as exercise combo box
        dietComboBox.getItems().addAll(model.getDietOptions()); //TODO Update
        dietComboBox.getSelectionModel().select(
                dietComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getDiet()));
        //same as exercise combo box
        alcoholComboBox.getItems().addAll(model.getAlcoholOptions()); //TODO Update
        alcoholComboBox.getSelectionModel().select(
                alcoholComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getAlcohol()));
        //same as alcohol combo box
        tobaccoComboBox.getItems().addAll(model.getTobaccoOptions()); //TODO Update
        tobaccoComboBox.getSelectionModel().select(
                tobaccoComboBox.getItems().indexOf(patientsListGV.getSelectionModel().getSelectedItem().getTobacco()));
    }

    @FXML
    public void updatePatient(ActionEvent actionEvent) {

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
        if(resource.equals("GUI/Views/CreatePatient.fxml")){
            loader.<NewPatientCTLL>getController().setUser(logedUser);
        }
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
