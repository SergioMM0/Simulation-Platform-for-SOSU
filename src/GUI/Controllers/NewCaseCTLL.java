package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCaseCTLL {

    @FXML
    private Button cancelBtn;

    @FXML
    private Label caseNameLBL;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField causalConditionField;

    @FXML
    private TextField causalDiagnoseField;

    @FXML
    private TextField causeTextField;

    @FXML
    private TextField citizenGoalField;

    @FXML
    private TextField descriptionOfCondition;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private ComboBox<String> subcategoryComboBox;

    @FXML
    void cancelCreate(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveNewCase(ActionEvent event) {

    }

    private void closeWindow() {
        Stage st = (Stage) cancelBtn.getScene().getWindow();
        st.close();
    }

}

