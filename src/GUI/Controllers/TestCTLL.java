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

import java.net.URL;
import java.util.ResourceBundle;

public class TestCTLL implements Initializable {
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

    StudentQuestionMOD model = new StudentQuestionMOD();    //use model to operation and contact with bll
    StudentQuestion currentQuestion;

    public void saveQuestionAndLoadNext(ActionEvent event) {
        //save question then load next question
        int state = getState();             //calculate the selected state
        StudentQuestionnaireAnswer answer = new StudentQuestionnaireAnswer(0, Integer.parseInt(questionIdLabel.getText()), state, 0); //create answer object

        model.saveStudentQuestionAnswer(answer);            //save answer to database
        currentQuestion = model.getNextQuestion(currentQuestion);       //load next question
        if(currentQuestion ==null)return;        //questions finished
        setQuestion(currentQuestion);           //set current question to controls
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertImage();
        currentQuestion =model.GetFirstQuestion();       //get first question
        setQuestion(currentQuestion);
    }
    private void setQuestion(StudentQuestion question) {
        categoryLabel.setText(question.getCategory());
        titleLabel.setText(question.getTitle());
        questionIdLabel.setText(question.getId() + "");
        textFieldQuestion.setText(question.getQuestion());

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
        if(state5radio.isSelected())
            state = 6 ;

        return state;
    }
}
