package com.hoshino.hoshinoscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller  {
    /*@FXML
    private Label welcomeText;*/

    @FXML
    private Button min;

    /*@FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Hoshino Application!");
    }*/

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