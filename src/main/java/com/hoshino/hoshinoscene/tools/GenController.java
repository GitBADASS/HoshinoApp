package com.hoshino.hoshinoscene.tools;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GenController implements Initializable {


    public TextField en;
    public TextField cn;
    public Button clearEn;
    public Button clearCn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearEn.setOnMousePressed(e->en.setText(""));
        clearCn.setOnMousePressed(e->cn.setText(""));
    }
}
