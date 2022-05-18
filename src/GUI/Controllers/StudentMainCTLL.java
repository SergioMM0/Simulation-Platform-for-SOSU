package GUI.Controllers;


import BE.Group;
import BE.User;
import GUI.Models.StudentMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainCTLL implements Initializable {

    @FXML
    private Label groupNameLable;

    private User logedUser;
    private Group loggedUserGroup;
    private StudentMOD model;

    public StudentMainCTLL() {
        model=new StudentMOD();
    }


    @FXML
    void openEvaluate(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("GUI/Views/EvaluateCase.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Evaluate Case");
        stage.setScene(new Scene(root));
//        stage.setResizable(resizable);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setUser(User logedUser) {
        this.logedUser = logedUser;
        this.loggedUserGroup=model.getGroupOf(this.logedUser);
        groupNameLable.setText(this.loggedUserGroup.getName());
    }
}
