package GUI.Controllers;

import BE.Case;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.NewCaseMOD;
import GUI.Util.CatAndSubC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewCaseCTLL implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Label caseNameLBL; //labeled in order to have a fancy color and typo :)

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField causeTextField;

    @FXML
    private TextField descriptionOfCondition;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveChangesBtn; //Must be green on CSS and different to other buttons

    @FXML
    private ComboBox<String> subcategoryComboBox;

    private NewCaseMOD model;
    private TeacherMainCTLL teacherMainCTLL;
    private CatAndSubC catAndSubC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new NewCaseMOD();
        populateCategories();
        catAndSubC = CatAndSubC.getInstance();
    }

    public void populateCategories(){
        categoryComboBox.getItems().addAll(catAndSubC.getCategories());
    }

    @FXML
    void catSelected(ActionEvent event) {
        if(!categoryComboBox.getValue().isEmpty()){
                subcategoryComboBox.getItems().clear();
                subcategoryComboBox.getItems().addAll(catAndSubC.getSubcategoriesOf(categoryComboBox.getValue()));
        }
    }

    @FXML
    void cancelCreate(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void saveNewCase(ActionEvent event) {
        if(fieldsAreFiled()){
            try {
                Case newCase = new Case(nameField.getText(),
                        descriptionOfCondition.getText(),
                        causeTextField.getText(),
                        categoryComboBox.getValue(),
                        subcategoryComboBox.getValue());
                model.createCase(newCase);
                closeWindow();
                teacherMainCTLL.addCaseToList(newCase);
            } catch (DalException dalException) {
                new SoftAlert(dalException.getMessage());
            }
        }
    }

    private boolean fieldsAreFiled() {
        if(nameField.getText().isEmpty()){
            new SoftAlert("Please introduce a name for the Case");
            return false;
        }
        else if(categoryComboBox.getValue().isEmpty()){
            new SoftAlert("Please select a category for the Case");
            return false;
        }
        else if(subcategoryComboBox.getValue().isEmpty()){
            new SoftAlert("Please select a subcategory for the Case");
            return false;
        }
        else if(descriptionOfCondition.getText().isEmpty()){
            new SoftAlert("Please introduce a valid description of the condition");
            return false;
        }
        else if(causeTextField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid description of the cause");
            return false;
        }
        else return true;
    }

    private void closeWindow() {
        Stage st = (Stage) cancelBtn.getScene().getWindow();
        st.close();
    }

    public void setController(TeacherMainCTLL teacherMainCTLL) {
        this.teacherMainCTLL = teacherMainCTLL;
    }
}

