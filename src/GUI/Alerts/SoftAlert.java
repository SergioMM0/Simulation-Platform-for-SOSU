package GUI.Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SoftAlert {

    private static final String errTitle = "Something went wrong";

    public SoftAlert(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(errTitle);
        alert.setHeaderText(message);
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
}
