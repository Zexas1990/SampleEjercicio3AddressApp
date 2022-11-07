module com.example.sampleejercicio3addressapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sampleejercicio3addressapp to javafx.fxml;
    exports com.example.sampleejercicio3addressapp;
}