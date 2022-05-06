package GUI.Controllers;

import BE.Case;
import BE.Category;
import BE.SubCategory;
import DAL.util.DalException;
import GUI.Alerts.SoftAlert;
import GUI.Models.NewCaseMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewCaseCTLL implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Label caseNameLBL; //labeled in order to have a fancy color and typo :)

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
    private Button saveChangesBtn; //Must be green on CSS and different to other buttons

    @FXML
    private ComboBox<String> subcategoryComboBox;

    private NewCaseMOD model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new NewCaseMOD();
        populateCategories();
    }

    public void populateCategories(){
        try{
            for(Category cat : model.getAllCategories()){
                categoryComboBox.getItems().add(cat.getName());
            }
        }catch (DalException dalException){
            dalException.printStackTrace();
            new SoftAlert(dalException.getMessage());
        }
    }

    @FXML
    void catSelected(ActionEvent event) { //TODO GUI tested and running, works fine without listener. When solved issue with Subcategories in DAL -> TO TEST
        if(!categoryComboBox.getValue().isEmpty()){
            try {
                for(SubCategory subCat : model.getAllSubcategories(model.getChosenCategory(categoryComboBox.getValue()))){
                    subcategoryComboBox.getItems().add(subCat.getName());
                }
            } catch (DalException dalException) {
                dalException.printStackTrace();
                new SoftAlert(dalException.getMessage());
                subcategoryComboBox.getItems().add("Delete me, injected through GUI by zrh");
            }
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
                model.createCase(new Case(nameField.getText(),
                        descriptionOfCondition.getText(),
                        causeTextField.getText(),
                        causalDiagnoseField.getText(),
                        causalConditionField.getText(),
                        citizenGoalField.getText()),

                        model.getChosenCategory(categoryComboBox.getValue()),
                        model.getChosenSubCategory(categoryComboBox.getValue())
                );
                closeWindow();
            } catch (DalException dalException) {
                dalException.printStackTrace();
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
        else if(causalDiagnoseField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid description of the causal diagnosis");
            return false;
        }
        else if(causalConditionField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid description of the causal condition");
            return false;
        }
        else if(citizenGoalField.getText().isEmpty()){
            new SoftAlert("Please introduce a valid description of the citizen goal");
            return false;
        }
        else return true;
    }

    private void closeWindow() {
        Stage st = (Stage) cancelBtn.getScene().getWindow();
        st.close();
    }
}

