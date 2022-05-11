package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    public void saveQuestionAndLoadNext(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertimage();
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
}
