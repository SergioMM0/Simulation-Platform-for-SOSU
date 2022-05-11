package GUI.Controllers;

import BE.StudentQuestion;
import BE.StudentQuestionaireAnswer;
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
    public Label categoryLable;
    @FXML
    public Label titleLabel;
    @FXML
    public ImageView iamge1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public ImageView image4;
    @FXML
    public ImageView image5;
    @FXML
    public ImageView image6;
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
    public Label quesitonIdLable;
    //@FXML
    //private Label questionLabel;
    @FXML
    private TextArea textFieldQuestion;

    StudentQuestionMOD model = new StudentQuestionMOD();    //use model to operation and contact with bll
    StudentQuestion currentQuesiton;

    public void saveQuestionAndLoadNext(ActionEvent event) {
        //save question then load next question
        int state = getState();             //calculate the selected state
        StudentQuestionaireAnswer answer = new StudentQuestionaireAnswer(0, Integer.parseInt(quesitonIdLable.getText()), state, 0); //create answer object

        model.saveStudentQuestionAnswer(answer);            //save answer to database
        currentQuesiton = model.getNextQuestion(currentQuesiton);       //load next question
        if(currentQuesiton==null)return;        //questions finished
        setQuestion(currentQuesiton);           //set current question to controls
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertimage();
        currentQuesiton=model.GetFirstQuestion();       //get first question
        setQuestion(currentQuesiton);
    }
    private void setQuestion(StudentQuestion question) {
        categoryLable.setText(question.getCategory());
        titleLabel.setText(question.getTitle());
        quesitonIdLable.setText(question.getId() + "");
        textFieldQuestion.setText(question.getQuestion());
    }

    private void insertimage() {
        Image imageone = new Image("res/one.png");
        Image imagetwo = new Image("res/two.png");
        Image imagethree = new Image("res/three.png");
        Image imagefour = new Image("res/four.png");
        Image imagefive = new Image("res/five.png");

        iamge1.setImage(imageone);
        image2.setImage(imagetwo);
        image3.setImage(imagethree);
        image4.setImage(imagefour);
        image5.setImage(imagefive);
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
        return state;
    }
}
