import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUI/Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 450);
        stage.setResizable(false);
        stage.setTitle("Simulation platform - Log in");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //TODO Test addNewCase and "case info" in teacherView
}