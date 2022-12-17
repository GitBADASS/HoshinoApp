package com.hoshino.hoshinoscene.tools;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GenController implements Initializable {


    public TextField en;//英文输入框
    public TextField cn;//中文输入框
    public Button clearEn;//英文清空框
    public Button clearCn;//中文清空框

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //清空方法
        clearEn.setOnMousePressed(e->en.setText(""));//英
        clearCn.setOnMousePressed(e->cn.setText(""));//中

        solve(en);
        solve(cn);
    }

    /*待完成：
       利用正则表达式判断是否是中/英文 （中：如果含数字或英文就不规范；英文：如果不是26个字母就不规范）
       更改不规范提示样式，最好是加上显示悬浮文本
       等等
    * */
    private void solve(TextField tf) {
        tf.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(tf.getLength() >= 50 || Objects.equals(tf.getText(), " ")) {
                tf.setStyle("-fx-background-color: #ffe1e1;-fx-border-style:solid;-fx-border-width: 0 0 1 0;-fx-border-color: #ffb0b0;");
                Timer t = new Timer();
                TimerTask tk = new TimerTask() {
                    @Override
                    public void run() {
                        tf.setStyle(null);
                    }
                };
                t.schedule(tk, 2000);
            }
        });
    }
}
