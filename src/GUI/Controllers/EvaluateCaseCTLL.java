package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EvaluateCaseCTLL {
    @FXML
    void openQuestionnaire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("GUI/Views/StudentQuestion.fxml"));
        Parent root = null;
        try{root = loader.load();}
        catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Student Question");
        stage.setScene(new Scene(root,880,660));

        stage.show();
    }
}
