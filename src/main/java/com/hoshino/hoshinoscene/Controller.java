package com.hoshino.hoshinoscene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller  {
    @FXML
    private Label welcomeText;

    @FXML
    private Button min;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void exit() {
        System.exit(0);
    }

    public void setMin(Stage stage) {
        min.setOnAction((e)->{
            stage.setIconified(true);
        });
    }
}