package GUI.Controllers;

import BE.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainCTLL implements Initializable {

    private User logedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setUser(User logedUser) {
        this.logedUser = logedUser;
    }
}
