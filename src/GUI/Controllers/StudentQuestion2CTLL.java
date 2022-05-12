package GUI.Controllers;

import BE.StudentQuestion;
import BE.StudentQuestionnaireAnswer;
import GUI.Models.StudentQuestionMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class StudentQuestion2CTLL {
    StudentQuestionMOD model = new StudentQuestionMOD();    //use model to operation and contact with bll
    StudentQuestion currentQuesiton;        //currently using question
    @FXML
    public void initialize() {                      //when view loaded this method will be called
        currentQuesiton=model.GetFirstQuestion();       //get first question
        setQuestion(currentQuesiton);               //set controls with current question values
    }
    @FXML
    private Label categoryLable;
    @FXML
    private Label quesitonIdLable;
    @FXML
    private ToggleGroup group;

    @FXML
    private TextArea questionText;

    @FXML
    private RadioButton state0radio;

    @FXML
    private TextArea state0text;

    @FXML
    private RadioButton state1radio;

    @FXML
    private TextArea state1text;

    @FXML
    private RadioButton state2radio;

    @FXML
    private TextArea state2text;

    @FXML
    private RadioButton state3radio;

    @FXML
    private TextArea state3text;

    @FXML
    private RadioButton state4radio;

    @FXML
    private TextArea state4text;

    @FXML
    private Label titleLabel;

    @FXML
    void saveQuestionAndLoadNext(ActionEvent event) {           //save question then load next question
        int state = getState();             //calculate the selected state
        StudentQuestionnaireAnswer answer = new StudentQuestionnaireAnswer(0, Integer.parseInt(quesitonIdLable.getText()), state, 0); //create answer object

        model.saveStudentQuestionAnswer(answer);            //save answer to database
        currentQuesiton = model.getNextQuestion(currentQuesiton);       //load next question
        if(currentQuesiton==null)return;        //questions finished
        setQuestion(currentQuesiton);           //set current question to controls
    }

    private void setQuestion(StudentQuestion question) {
        categoryLable.setText(question.getCategory());
        titleLabel.setText(question.getTitle());
        quesitonIdLable.setText(question.getId() + "");
        questionText.setText(question.getQuestion());
        state0text.setText(question.getState0());
        state1text.setText(question.getState1());
        state2text.setText(question.getState2());
        state3text.setText(question.getState3());
        state4text.setText(question.getState4());
    }

    private int getState() {
        var state = 0;
        if (state1radio.isSelected())
            state = 1;
        if (state2radio.isSelected())
            state = 2;
        if (state3radio.isSelected())
            state = 3;
        if (state4radio.isSelected())
            state = 4;
        return state;
    }
}
