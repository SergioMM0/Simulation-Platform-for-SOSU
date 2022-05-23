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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class EvaluateCaseCTLL {
    private ArrayList<Stage> listOfStages = new ArrayList<>();
    private String generalCSS = "";


    public void setGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }

    public void setPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public void setCase(Case currentCase) {
        this.currentCase = currentCase;
    }

    private User currentStudent;
    private Group currentGroup;
    private Patient currentPatient;
    private Case currentCase;

    @FXML
    void openQuestionnaire(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getClassLoader().getResource("GUI/Views/StudentQuestion.fxml"));
//        Parent root = null;
//        try{root = loader.load();}
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        Stage stage = new Stage();
//        stage.setTitle("Student Question");
//        stage.setScene(new Scene(root,880,660));
//
//        stage.show();
        System.out.println("currentGroup = " + currentGroup.getName());
        openView("GUI/Views/StudentQuestion.fxml", generalCSS, "Student Question", 880, 660, false);
    }

    private void openView(String resource, String css, String title, int width, int height, boolean resizable) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        StudentQuestionCTL controller = loader.getController();
        controller.setUser(currentStudent);
        controller.setGroup(currentGroup);
        controller.setCase(currentCase);
        controller.setPatient(currentPatient);
        root.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setTitle(title);
        listOfStages.add(stage);
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(resizable);
        stage.showAndWait();
    }

    public void setUser(User currentStudent) {
        this.currentStudent = currentStudent;
    }
}
