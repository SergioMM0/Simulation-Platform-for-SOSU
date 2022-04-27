module dk.easv.simulationplatform {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.simulationplatform to javafx.fxml;
    exports dk.easv.simulationplatform;
}