package GUI.Controllers;

<<<<<<< Updated upstream
import BE.User;
=======
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
>>>>>>> Stashed changes
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainCTLL implements Initializable {

<<<<<<< Updated upstream
    private User logedUser;

=======
    @FXML
    void openQuestionnaire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("GUI/Views/StudentQuestion2.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Student Question");
        stage.setScene(new Scene(root));
//        stage.setResizable(resizable);
        stage.show();
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
>>>>>>> Stashed changes
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setUser(User logedUser) {
        this.logedUser = logedUser;
    }
}
