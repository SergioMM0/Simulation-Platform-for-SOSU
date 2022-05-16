package GUI.Controllers;

import BE.School;
import BE.User;
import DAL.util.DalException;
import GUI.Models.AdminMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCTLL implements Initializable {
    @FXML
    private TableView<School> SchoolTalbeView;
    @FXML
    private TableColumn<School , Integer> schoolID;
    @FXML
    private TableColumn<School , String> SchoolName;
    @FXML
    private TableView<User> TeacherTableView;
    @FXML
    private TableColumn<User , Integer> TeacherID;
    @FXML
    private TableColumn<User , String> TeacherName;
    @FXML
    private TableColumn<User , String> TeacherEmail;
    @FXML
    private TableView<User> StudentTableView;
    @FXML
    private TableColumn<User , Integer> StudnetID;
    @FXML
    private TableColumn<User , String > StudnetName;
    @FXML
    private TableColumn<User , String> StudnetEmail;
    @FXML
    private TextField filterid;
    @FXML
    private ImageView filterImageid;
    @FXML
    private Button CreateSchoolID;
    @FXML
    private Button CreateTeacherid;
    @FXML
    private Button CreateStudnetid;
    @FXML
    private Button DeleteSchoolID;
    @FXML
    private Button DeleteTeacherid;
    @FXML
    private Button DeleteStudentid;
    @FXML
    private Button Closeid;
    @FXML
    private Label AdminNameLbl;

    private AdminMOD adminMOD ;
    private User logedUser ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("/res/felterimage.png");
        filterImageid.setImage(image);
        adminMOD = AdminMOD.getInstance();
        displaySchools();
        displayusers();
    }

    public void displaySchools(){
        try {
            SchoolTalbeView.setItems(adminMOD.getAllSchools());
            schoolID.setCellValueFactory(new PropertyValueFactory<>("id"));
            SchoolName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TeacherID.setCellValueFactory(new PropertyValueFactory<>("id"));
            TeacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TeacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            StudnetID.setCellValueFactory(new PropertyValueFactory<>("id"));
            StudnetName.setCellValueFactory(new PropertyValueFactory<>("name"));
            StudnetEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        } catch (DalException e) {
            e.printStackTrace();
        }
    }
        //TODO BUGGED METHOD
    public void displayusers(){
        System.out.println("dd");

                if(SchoolTalbeView.getSelectionModel().getSelectedIndex() != -1){
                    System.out.println("test");
                    try {
                        TeacherTableView.setItems(adminMOD.getAllTeachers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId()));
                        StudentTableView.setItems(adminMOD.getAllSudents(SchoolTalbeView.getSelectionModel().getSelectedItem().getId()));
                    } catch (DalException e) {
                        e.printStackTrace();
                    }

                }

    }

    public void filterField(KeyEvent keyEvent) {
    }

    public void CreateSchoolbtn(ActionEvent event) {
    }

    public void CreateTeacherBtn(ActionEvent event) {
    }

    public void CreateStudentBTN(ActionEvent event) {
    }

    public void DeleteSchoolbtn(ActionEvent event) {
    }

    public void DeleteTeacherBtn(ActionEvent event) {
    }

    public void DeleteStudentbtn(ActionEvent event) {
    }

    public void ClloseBtn(ActionEvent event) {
    }

    public void setUser(User logedUser) {
        this.logedUser = logedUser;
    }
}
