package GUI.Controllers;

import BE.User;
import BLL.BLLFacade;
import BLL.BLLManager;
import BLL.Exceptions.BLLException;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.LoginMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginCTLL implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLbl;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLBL;

    private User logedUser;
    private BLLFacade bllFacade;
    private final String generalCSS = "";
    private LoginMOD loginMOD;

    @FXML
    void loginAct(ActionEvent event) {
        if(!emailField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            try{
                logedUser = loginMOD.checkCredentials(emailField.getText(),passwordField.getText());
                switch (logedUser.getUserType()) {
                    case "STUDENT" -> openView("GUI/Views/StudentMain.fxml", generalCSS, "FS3 for Students", 0, 0, false);
                    case "TEACHER" -> openView("GUI/Views/TeacherMain.fxml", generalCSS, "Simulation platform FS3", 0, 0, false);
                }
            }catch (DalException | BLLException exception){ //TODO review message in DAL
                new SoftAlert(exception.getMessage());
            }
        }
    }

    private void closeWindow(){
        Stage st = (Stage) anchorPane.getScene().getWindow();
        st.close();
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
        closeWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginMOD = new LoginMOD();
    }

}