package GUI.Controllers;

import BE.School;
import BE.User;
import DAL.util.DalException;
import GUI.Alerts.ConfirmationAlert;
import GUI.Models.AdminMOD;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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


    }

    public void displaySchools(){
        try {
            SchoolTalbeView.setItems(adminMOD.getAllSchools());
            schoolID.setCellValueFactory(new PropertyValueFactory<>("id"));
            SchoolName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TeacherTableView.setItems(adminMOD.getAllTeachers(1));
            TeacherID.setCellValueFactory(new PropertyValueFactory<>("id"));
            TeacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
            TeacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            StudentTableView.setItems(adminMOD.getAllSudents(1));
            StudnetID.setCellValueFactory(new PropertyValueFactory<>("id"));
            StudnetName.setCellValueFactory(new PropertyValueFactory<>("name"));
            StudnetEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        } catch (DalException e) {
           new ConfirmationAlert("couldn't retrieve data from the database ");
        }
    }

    public void filterField(KeyEvent keyEvent)  {
            try {
                if (filterid.getText() == null || filterid.getText().length() <= 0) {
                TeacherTableView.setItems(adminMOD.getallusers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"TEACHER"));
                StudentTableView.setItems(adminMOD.getallusers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"STUDENT"));
                } else {
                    ObservableList<User> foundStudents;
                    foundStudents = adminMOD.searchforUser(adminMOD.getallusers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"STUDENT"), filterid.getText());
                    ObservableList<User> foundTeachers;
                    foundTeachers = adminMOD.searchforUser(adminMOD.getallusers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId() ,"TEACHER"), filterid.getText());

                    TeacherTableView.setItems(foundTeachers);
                    StudentTableView.setItems(foundStudents);

                }
            } catch (DalException |NullPointerException e) {
                new ConfirmationAlert("please select school");
            }
    }

    public void CreateSchoolbtn(ActionEvent event) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        final javafx.scene.control.TextField name = new TextField();
        name.setPromptText("Username");
        Button button = new Button();
        button.setText("Create");
        grid.add(new Label("name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(button, 1, 2);
        Stage stage = new Stage();
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
        button.setOnAction(event1 -> {
            try {
                adminMOD.createSchools(name.getText());
            } catch (DalException e) {
              new ConfirmationAlert("please insert valid name");
            }
        });
    }

    public void iWillInsertYou(String text){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        final javafx.scene.control.TextField username = new TextField();
        username.setPromptText("Username");
        final javafx.scene.control.TextField email = new TextField();
        username.setPromptText("email");
        final javafx.scene.control.TextField school = new TextField();
        username.setPromptText("enter username");
        email.setPromptText("enter email");
        username.setPromptText("enter schoolID");
        Button button = new Button();
        button.setText("Create");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("email:"), 0, 1);
        grid.add(email , 1 , 1);
        grid.add(new Label("school") ,0 ,2);
        grid.add(school,1,2);
        grid.add(button, 2, 2);
        Stage stage = new Stage();
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
        button.setOnAction(event1 -> {
            int i = Integer.parseInt(String.valueOf(school.getText()));

            try {
                adminMOD.createUser(new User(1,i ,username.getText(),email.getText(),text));
            } catch (DalException e) {
                new ConfirmationAlert("please enter valid data ");
            }
        });
    }

    public void CreateTeacherBtn(ActionEvent event) {
        iWillInsertYou("TEACHER");
    }

    public void CreateStudentBTN(ActionEvent event) {
        iWillInsertYou("STUDENT");
    }

    public void DeleteSchoolbtn(ActionEvent event) {
        try {
            if(SchoolTalbeView.getSelectionModel().getSelectedIndex() != -1)
            adminMOD.deleteSchool(SchoolTalbeView.getSelectionModel().getSelectedItem());
        } catch (DalException e) {
            new ConfirmationAlert("please select school ");
        }
    }

    public void DeleteTeacherBtn(ActionEvent event) {
            try {
                if(TeacherTableView.getSelectionModel().getSelectedIndex() != -1 ) {
                    adminMOD.removeUser(TeacherTableView.getSelectionModel().getSelectedItem());
                }
            } catch (DalException e) {
               new ConfirmationAlert("please select a Teacher ");
            }

    }

    public void DeleteStudentbtn(ActionEvent event) {
        try {
            if(StudentTableView.getSelectionModel().getSelectedIndex() != -1 ) {
                adminMOD.removeUser(StudentTableView.getSelectionModel().getSelectedItem());
            }
        } catch (DalException e) {
            new ConfirmationAlert("please select a Student ");
        }
    }

    public void ClloseBtn(ActionEvent event) {
        Stage st = (Stage) Closeid.getScene().getWindow();
        st.close();
    }

    public void setUser(User logedUser) {
        this.logedUser = logedUser;
    }

    public void displayusersinschool(MouseEvent mouseEvent) {
        if(SchoolTalbeView.getSelectionModel().getSelectedIndex() != -1){
            try {
                TeacherTableView.setItems(adminMOD.getAllTeachers(SchoolTalbeView.getSelectionModel().getSelectedItem().getId()));
                StudentTableView.setItems(adminMOD.getAllSudents(SchoolTalbeView.getSelectionModel().getSelectedItem().getId()));
            } catch (DalException e) {
                new ConfirmationAlert("No Users Found");
            }
        }
    }
}
