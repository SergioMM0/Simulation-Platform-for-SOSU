package GUI.Controllers;

import BE.Case;
import BE.Group;
import BE.Patient;
import BE.User;
import GUI.Models.AssignCaseMOD;
import GUI.Models.TeacherMainMOD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AssignCaseCTLL {

    @FXML
    private TableView<Group> allGroups;

    @FXML
    private TableView<Patient> allPatients;

    @FXML
    private Label caseLBL;

    @FXML
    private TableColumn<Group, String> nameGroupsCOL;

    @FXML
    private TableColumn<Patient, String> namePatientsCOL;

    private Case selectedCase;
    private TeacherMainCTLL teacherMainCTLL;
    private TeacherMainMOD teacherMainMOD;

    public AssignCaseCTLL(){
        teacherMainMOD = new TeacherMainMOD();
    }

    public void initializeView(){
        allGroups.getItems().addAll(teacherMainMOD.getObservableGroups());
        nameGroupsCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPatients.getItems().addAll(teacherMainMOD.getObservablePatients());
        namePatientsCOL.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    }

    @FXML
    void assignCase(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {
        closeView();
    }

    private void closeView(){
        Stage st = (Stage) caseLBL.getScene().getWindow();
        st.close();
    }

    public void setCase(Case selectedCase){
        this.selectedCase = selectedCase;
    }

    public void setController(TeacherMainCTLL teacherMainCTLL){
        this.teacherMainCTLL = teacherMainCTLL;
    }

}

