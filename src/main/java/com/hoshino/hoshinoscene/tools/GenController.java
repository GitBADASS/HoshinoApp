package com.hoshino.hoshinoscene.tools;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class GenController implements Initializable {


    public TextArea en;
    public TextArea cn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        en.setText("Good");
        cn.setText("好的");
    }
}
