package GUI.Controllers;

import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    @FXML
    void loginAct(ActionEvent event) {
        try{

        }catch (DalException dalException){
            new SoftAlert(dalException.getMessage());
        }
    }

    private void closeWindow(){
        Stage st = (Stage) anchorPane.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}