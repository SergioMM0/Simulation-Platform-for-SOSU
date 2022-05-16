package GUI.Controllers;

import BE.StudentQuestion;
import BE.StudentQuestionnaireAnswer;
import GUI.Models.StudentQuestionMOD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentQuestionCTL implements Initializable {
    @FXML
    public Label categoryLabel;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public ImageView image4;
    @FXML
    public ImageView image5;
    @FXML
    public RadioButton state1radio;
    @FXML
    public RadioButton state2radio;
    @FXML
    public RadioButton state3radio;
    @FXML
    public RadioButton state4radio;
    @FXML
    public RadioButton state5radio;
    @FXML
    public RadioButton state6radio;
    @FXML
    public Label questionIdLabel;
    @FXML
    private TextArea textFieldQuestion;
    @FXML
    private AnchorPane titleContainer;

    StudentQuestionMOD model = new StudentQuestionMOD();    //use model to operation and contact with bll
    BE.StudentQuestion currentQuestion;

    public void saveQuestionAndLoadNext(ActionEvent event) {
        //save question then load next question
        int state = getState();//calculate the selected state
        if (questionIdLabel.getText() == "null") return; //end of loading next questions
        int questionId = Integer.parseInt(questionIdLabel.getText());
        StudentQuestionnaireAnswer answer = new StudentQuestionnaireAnswer(0, questionId, state, 0); //create answer object

        model.saveStudentQuestionAnswer(answer);            //save answer to database
        currentQuestion = model.getNextQuestion(currentQuestion);       //load next question
        if (currentQuestion == null) return;        //questions finished
        setQuestion(currentQuestion);           //set current question to controls
        setAnswer(model.getAnswer(currentQuestion.getId()));
    }

    @FXML
    void loadPreviousQuestion(ActionEvent event) {
        int currentQuestionId = 0;
        if (questionIdLabel.getText() == "null")       //if we are at end of questions
            currentQuestionId = Integer.MAX_VALUE;        //set currentQuestion with biggest integer value
        else if (questionIdLabel.getText() != "")
            currentQuestionId = Integer.parseInt(questionIdLabel.getText());
        StudentQuestion previousQuestion = getPreviousQuestionId(currentQuestionId);
        if (previousQuestion==null)
            return;
        currentQuestion = previousQuestion;
        setQuestion(currentQuestion);
        StudentQuestionnaireAnswer answer = model.getAnswer(currentQuestion.getId());
        setAnswer(answer);
    }



    private StudentQuestion getPreviousQuestionId(int currentQuestionId) {
        return model.getPreviousQuestion(currentQuestionId);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertImage();
        currentQuestion = model.GetFirstQuestion();       //get first question
        setQuestion(currentQuestion);
    }

    private void setQuestion(BE.StudentQuestion question) {
        if (question == null) {
            questionIdLabel.setText("null");
            return;
        }

        categoryLabel.setText(question.getCategory());
        titleContainer.setStyle("-fx-background-color: "+question.getColor()+";");
        titleLabel.setText(question.getTitle());
        questionIdLabel.setText(question.getId() + "");
        textFieldQuestion.setText(question.getQuestion());
        state1radio.setSelected(true);

    }

    private void insertImage() {
        Image imageOne = new Image("res/one.png");
        Image imageTwo = new Image("res/two.png");
        Image imageThree = new Image("res/three.png");
        Image imageFour = new Image("res/four.png");
        Image imageFive = new Image("res/five.png");

        image1.setImage(imageOne);
        image2.setImage(imageTwo);
        image3.setImage(imageThree);
        image4.setImage(imageFour);
        image5.setImage(imageFive);
    }

    private int getState() {
        var state = 1;
        if (state1radio.isSelected())
            state = 2;
        if (state2radio.isSelected())
            state = 3;
        if (state3radio.isSelected())
            state = 4;
        if (state4radio.isSelected())
            state = 5;
        if (state5radio.isSelected())
            state = 6;

        return state;
    }
    private void setAnswer(StudentQuestionnaireAnswer answer) {
        if (answer==null)
            return;
        switch (answer.getState()) {
            case 1:
                state6radio.setSelected(true);
                break;
            case 2:
                state1radio.setSelected(true);
                break;
            case 3:
                state2radio.setSelected(true);
                break;
            case 4:
                state3radio.setSelected(true);
                break;
            case 5:
                state4radio.setSelected(true);
                break;
            case 6:
                state5radio.setSelected(true);
                break;
        }
    }
}
