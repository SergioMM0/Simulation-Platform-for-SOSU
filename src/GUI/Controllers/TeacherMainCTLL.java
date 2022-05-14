package GUI.Controllers;

import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.TeacherMainMOD;
import GUI.Util.CatAndSubC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

public class TeacherMainCTLL {

    @FXML
    private ComboBox<String> caseCategoryComboBox;

    @FXML
    private TextField caseNameField;

    @FXML
    private ComboBox<String> caseSubcategoryComboBox;

    @FXML
    private Label casesAssignedLBL;

    @FXML
    private TableView<Case> casesAssignedList;

    @FXML
    private TableColumn<Case, String> nameColCases;

    @FXML
    private Label casesGradedLBL;

    @FXML
    private TableView<Case> casesGradedList;

    @FXML
    private TableColumn<Case, String> nameColCasesGraded;

    @FXML
    private TableView<Case> casesListGV;

    @FXML
    private TableColumn<Group, String> nameColCasesGV;

    @FXML
    private TextField descriptionOfConditionText;

    @FXML
    private TableColumn<Group, String> groupNameCol;

    @FXML
    private TableView<User> participantsTable;

    @FXML
    private TableColumn<User, String> participantsNameCol;

    @FXML
    private Label groupNameLBL;

    @FXML
    private Label groupsLBL;

    @FXML
    private TableView<Group> groupsTable;

    @FXML
    private Label medicalHistoryLBL;

    @FXML
    private TextArea medicalHistoryTextArea;

    @FXML
    private TableView<Patient> patientsListGV;

    @FXML
    private TextField cprField;

    @FXML
    private DatePicker dateOfBirthPicker;

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
    private TextField weightField;

    @FXML
    private TableColumn<Patient, String> nameColPatientsGV;

    @FXML
    private TableColumn<Group, String> studentNameCol;

    @FXML
    private Label studentNamesLBL;

    @FXML
    private Label studentsLBL;

    @FXML
    private TableView<User> studentsTable;

    private User logedUser;
    private final String generalCSS = "";
    private TeacherMainMOD model;
    private final CatAndSubC catAndSubC;

    public void setUser(User user) {
        logedUser = user;
    }

    public TeacherMainCTLL() {
        model = new TeacherMainMOD();
        catAndSubC = CatAndSubC.getInstance();
    }

    public void initializeView() {
        populateGroupsTable();
        populateCasesTable();
        populatePatientsTable();
        populateStudentsTable();
        setUPContextMenus();
    }

    private void populateGroupsTable() {
        try {
            groupsTable.getItems().addAll(model.getAllGroups(logedUser.getSchoolID()));
            groupNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (DalException dalException) {
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }

    private void populateCasesTable() {
        try {
            casesListGV.getItems().addAll(model.getAllCases(logedUser.getSchoolID()));
            nameColCasesGV.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (DalException dalException) {
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }

    private void populatePatientsTable() {
        try {
            patientsListGV.getItems().addAll(model.getAllPatients(logedUser.getSchoolID()));
            nameColPatientsGV.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        } catch (DalException dalException) {
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }

    private void populateStudentsTable(){
        try{
            studentsTable.getItems().addAll(model.getAllStudents(logedUser.getSchoolID()));
            studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        }catch (DalException dalException){
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }


    public void addPatientToList(Patient patient) {
        model.addPatientToList(patient);
        refreshPatientsList();
    }

    public void refreshPatientsList(){
        patientsListGV.getItems().clear();
        patientsListGV.getItems().addAll(model.getObservablePatients());
    }


    public void addCaseToList(Case newCase) {
        model.addCaseToList(newCase);
        casesListGV.getItems().clear();
        casesListGV.getItems().addAll(model.getObservableCases());
    }

    @FXML
    void addNewCase(ActionEvent event) {
        openView("GUI/Views/CreateCase.fxml", generalCSS, "Create new case", 860, 770, false, 0);
    }

    @FXML
    void addNewPatient(ActionEvent event) {
        openView("GUI/Views/CreatePatient.fxml", generalCSS, "Create new patient", 485, 510, false, 0);
    }

    @FXML
    void caseIsSelected(MouseEvent event) {
        if(casesListGV.getSelectionModel().getSelectedItem() != null){
            caseCategoryComboBox.getItems().clear();
            caseSubcategoryComboBox.getItems().clear();

            String[] allCat = catAndSubC.getCategories();
            String[] allSubCat = catAndSubC.getSubcategoriesOf(casesListGV.getSelectionModel().getSelectedItem().getCategory());

            caseNameField.setText(casesListGV.getSelectionModel().getSelectedItem().getName());
            descriptionOfConditionText.setText(casesListGV.getSelectionModel().getSelectedItem().getConditionDescription());

            for (String cat : allCat) {
                caseCategoryComboBox.getItems().add(cat); //population of the categories
            }
            for(String subCat : allSubCat){
                caseSubcategoryComboBox.getItems().add(subCat); // population of the subcategories of the category
            }
            //population of case in case info when selected in general view

            caseCategoryComboBox.getSelectionModel().select(
                    caseCategoryComboBox.getItems().indexOf(
                            casesListGV.getSelectionModel().getSelectedItem().getCategory()));
            //selects the category specified for the case

            caseSubcategoryComboBox.getSelectionModel().select(
                    caseSubcategoryComboBox.getItems().indexOf(
                            casesListGV.getSelectionModel().getSelectedItem().getSubCategory()));
            //selects the subcategory specified for the case

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
    }

    @FXML
    public void updatePatient(ActionEvent actionEvent) {
        if(patientFieldsAreFilled()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to update this patient?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                try{
                    Patient patient = patientsListGV.getSelectionModel().getSelectedItem();
                    patient.setFirst_name(nameField.getText());
                    patient.setLast_name(familyNameField.getText());
                    patient.setDateOfBirth(dateOfBirthPicker.getValue());
                    patient.setGender(genderComboBox.getValue());
                    patient.setWeight(weightField.getText());
                    patient.setHeight(heightField.getText());
                    patient.setCpr(cprField.getText());
                    patient.setPhoneNumber(phoneNumberField.getText());
                    model.updatePatient(patient);
                    refreshPatientsList();
                }catch(DalException dalException){
                    dalException.printStackTrace();
                    new SoftAlert(dalException.getMessage());
                }
            }
        }
    }

    private boolean patientFieldsAreFilled(){
        if(nameField.getText().isEmpty()){
            new SoftAlert("Please introduce a new name for the patient");
            return false;
        }
        else if(familyNameField.getText().isEmpty()){
            new SoftAlert("Please introduce a new family name for the patient");
            return false;
        }
        else if(dateOfBirthPicker.getValue().isAfter(LocalDate.now()) || dateOfBirthPicker.getValue() == null){
            new SoftAlert("Please introduce a new date of birth for the patient");
            return false;
        }
        else if(genderComboBox.getSelectionModel().isEmpty() || genderComboBox.hasProperties()){
            new SoftAlert("Please introduce a new gender combo box for the patient");
            return false;
        }
        else if(weightField.getText().isEmpty()){
            new SoftAlert("Please introduce a new valid weight for the patient");
            return false;
        }
        else if(heightField.getText().isEmpty()){
            new SoftAlert("Please introduce a new valid height for the patient");
            return false;
        }
        else if(cprField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid CPR for the patient");
            return false;
        }
        else if(phoneNumberField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid phone number for the patient");
            return false;
        }
        else return true;
    }

    private boolean fieldsAreTheSame(){
        int same = 0;
        if(nameField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getFirst_name().toLowerCase(Locale.ROOT))){
            same++;
        }
        else if(familyNameField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getLast_name().toLowerCase(Locale.ROOT))) {
            same++;
        }
        else if(genderComboBox.getValue().equals(patientsListGV.getSelectionModel().getSelectedItem().getGender())){
            same++;
        }
        else if(weightField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getWeight().toLowerCase(Locale.ROOT))){
            same++;
        }
        else if(heightField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getHeight().toLowerCase(Locale.ROOT))){
            same++;
        }
        else if(cprField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getCpr().toLowerCase(Locale.ROOT))){
            same++;
        }
        else if(phoneNumberField.getText().toLowerCase(Locale.ROOT).equals(
                patientsListGV.getSelectionModel().getSelectedItem().getPhoneNumber().toLowerCase(Locale.ROOT))){
            same++;
        }
        return same<7;
    }

    @FXML
    void addStudentToGroup(ActionEvent event) {
        if(studentsTable.getSelectionModel().getSelectedItem() != null
                && groupsTable.getSelectionModel().getSelectedItem() != null){
            if(!groupsTable.getSelectionModel().getSelectedItem().containsMember(studentsTable.getSelectionModel().getSelectedItem())){
                try{
                    model.addStudentToGroup(groupsTable.getSelectionModel().getSelectedItem(),
                            studentsTable.getSelectionModel().getSelectedItem());
                }catch (DalException dalException){
                    new SoftAlert(dalException.getMessage());
                }
                System.out.println("Is reflecting");
                model.updateObservableGroup(groupsTable.getSelectionModel().getSelectedItem().addMember(
                        studentsTable.getSelectionModel().getSelectedItem()));
                populateParticipantsTable();
            }else new SoftAlert("This student is already in the group");
        } else{
            if(studentsTable.getSelectionModel().getSelectedItem() == null){
                new SoftAlert("Please select a student");
            }else new SoftAlert("Please select a group");
        }
    }

    @FXML
    void groupIsSelected(MouseEvent event){
        populateParticipantsTable();
    }

    private void populateParticipantsTable(){ //this method might be implemented straight in the MouseEvent
        if(groupsTable.getSelectionModel().getSelectedItem().getMembers()!= null){
            participantsTable.getItems().clear();
            participantsTable.getItems().addAll(model.getObservableParticipants(groupsTable.getSelectionModel().getSelectedItem()));
            participantsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
    }


    private void setUPContextMenus(){
        ContextMenu contm = new ContextMenu();
        participantsTable.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                contm.show(participantsTable, t.getScreenX(), t.getScreenY());
            }else contm.hide();
        });
        MenuItem mi1 = new MenuItem("Remove participant");
        mi1.setOnAction(t -> {
            removeParticipant();
            contm.hide();
            populateParticipantsTable();
        });
        contm.getItems().add(mi1);
    }

    @FXML
    void removeStudentFromGroup(ActionEvent event) {
        removeParticipant();
        populateParticipantsTable();
    }

    private void removeParticipant(){
        groupsTable.getSelectionModel().getSelectedItem().removeMember(
                participantsTable.getSelectionModel().getSelectedItem());
        model.removeParticipant(participantsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void assignedCaseIsSelected(MouseEvent event){

    }

    @FXML
    void gradedCaseIsSelected(MouseEvent event){

    }

    @FXML
    void addNewStudent(ActionEvent event) {
        openView("GUI/Views/ManageStudent.fxml", generalCSS, "Add new Student", 400, 220, false, 1);
    }

    @FXML
    void editStudent(ActionEvent event) {
        if(studentsTable.getSelectionModel().getSelectedItem() != null){
            openView("GUI/Views/ManageStudent.fxml", generalCSS, "Edit student", 400, 220, false, 2);
        }
        else new SoftAlert("Please select a student");
    }

    public void addStudentToTable(User user) {
        model.addObservableStudent(user);
        refreshStudentsTable();
    }

    public void updateStudentInTable(User student) {
        model.updateStudentInTable(student);
        refreshStudentsTable();
    }

    private void refreshStudentsTable(){
        studentsTable.getItems().clear();
        studentsTable.getItems().addAll(model.getObservableStudents());
    }

    @FXML
    void assignCaseToGroup(ActionEvent event) {

    }

    @FXML
    void assignNewCaseToGroup(ActionEvent event) {

    }

    @FXML
    void createGroup(ActionEvent event) {
        openView("GUI/Views/ManageGroup.fxml", generalCSS, "Add new group", 400, 180, false, 1);
    }

    @FXML
    void editGroup(ActionEvent event) {
        if(groupsTable.getSelectionModel().getSelectedItem() != null){
            openView("GUI/Views/ManageGroup.fxml", generalCSS, "Edit group", 400, 180, false, 2);
        }else new SoftAlert("Please select a group");
    }

    public void addGroupToList(Group group) {
        model.addObservableGroup(group);
        refreshGroupList();
    }

    public void updateGroupInList(Group group){
        model.updateObservableGroup(group);
        refreshGroupList();
    }

    public void refreshGroupList(){
        groupsTable.getItems().clear();
        groupsTable.getItems().addAll(model.getObservableGroups());
    }

    @FXML
    void deleteGroup(ActionEvent event) {
        if(groupsTable.getSelectionModel().getSelectedItem() != null){
            try{
                model.deleteGroup(groupsTable.getSelectionModel().getSelectedItem());
            }catch (DalException dalException){
                new SoftAlert(dalException.getMessage());
            }
            model.removeObservableGroup(groupsTable.getSelectionModel().getSelectedItem());
            refreshGroupList();
        }
    }

    @FXML
    void deleteCase(ActionEvent event) {

    }

    @FXML
    void deletePatient(ActionEvent event) {

    }

    @FXML
    void deleteStudent(ActionEvent event) {
        User student = studentsTable.getSelectionModel().getSelectedItem();
        try {
            model.deleteStudent(student);
        } catch (DalException dalException) {
            new SoftAlert(dalException.getMessage());
        }
        model.deleteObservableStudent(student);
        model.deleteStudentFromGroups(student);
        refreshStudentsTable();
        refreshGroupList();
    }

    @FXML
    void duplicateCase(ActionEvent event) {

    }

    @FXML
    void duplicatePatient(ActionEvent event) {

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
    void saveChangesOnCase(ActionEvent event) {

    }

    @FXML
    void unassignSelectedCase(ActionEvent event) {

    }

    @FXML
    void unmarkCaseAsGraded(ActionEvent event) {

    }

    private void openView(String resource, String css, String title, int width, int height, boolean resizable, int operationType) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(resource));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        if (resource.equals("GUI/Views/CreatePatient.fxml")) {
            loader.<NewPatientCTLL>getController().setUser(logedUser);
            loader.<NewPatientCTLL>getController().setController(this);
        }
        if (resource.equals("GUI/Views/CreateCase.fxml")) {
            loader.<NewCaseCTLL>getController().setController(this);
            loader.<NewCaseCTLL>getController().setUser(logedUser);
        }
        if (resource.equals("GUI/Views/ManageStudent.fxml") && operationType == 1) {
            loader.<ManageStudentCTLL>getController().setUser(logedUser);
            loader.<ManageStudentCTLL>getController().setController(this);
            loader.<ManageStudentCTLL>getController().setOperationType(operationType);
        }
        if (resource.equals("GUI/Views/ManageStudent.fxml") && operationType == 2) {
            loader.<ManageStudentCTLL>getController().setUser(logedUser);
            loader.<ManageStudentCTLL>getController().setController(this);
            loader.<ManageStudentCTLL>getController().setOperationType(operationType);
            loader.<ManageStudentCTLL>getController().setStudent(studentsTable.getSelectionModel().getSelectedItem());
            loader.<ManageStudentCTLL>getController().populateStudentFields();
        }
        if(resource.equals("GUI/Views/ManageGroup.fxml") && operationType == 1){
            loader.<ManageGroupCTLL>getController().setUser(logedUser);
            loader.<ManageGroupCTLL>getController().setController(this);
            loader.<ManageGroupCTLL>getController().setOperationType(operationType);
        }
        if(resource.equals("GUI/Views/ManageGroup.fxml") && operationType == 2){
            loader.<ManageGroupCTLL>getController().setUser(logedUser);
            loader.<ManageGroupCTLL>getController().setGroup(groupsTable.getSelectionModel().getSelectedItem());
            loader.<ManageGroupCTLL>getController().setController(this);
            loader.<ManageGroupCTLL>getController().setOperationType(operationType);
            loader.<ManageGroupCTLL>getController().populateGroupField();
        }
        root.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(resizable);
        stage.show();
    }

    private void closeWindow() {
        Stage st = (Stage) casesListGV.getScene().getWindow();
        st.close();
    }
}
