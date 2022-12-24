package com.hoshino.hoshinoscene.tools;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class GenController implements Initializable {


    public TextField en;//英文输入框
    public TextField cn;//中文输入框
    public Button clearEn;//英文清空框
    public Button clearCn;//中文清空框
    public Button save;//保存按钮
    public TextArea description;//学习库描述
    public TextField name;//学习库名称

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //清空方法
        clearEn.setOnMousePressed(e->en.setText(""));//英
        clearCn.setOnMousePressed(e->cn.setText(""));//中

        solve(en);
        solve(cn);
        solve(name);
        solve(description);
    }

    //保存退出
    public void init(Stage stage) {
        save.setOnMousePressed(e->{
            HashMap<String, String> h = new HashMap<>();
            if(!unconformable(en) && !unconformable(cn)) {
                h.put(en.getText(), cn.getText()); //TODO:这里要根据后期需求整改
            }
            WordsWarehouse wh = new WordsWarehouse(name.getText(), description.getText(), h);
            if(!unconformable(name) && !unconformable(description)) {
                Storage.save(wh);
                stage.close();
            }
        });
    }

    /*待完成：
       利用正则表达式判断是否是中/英文 （中：如果含数字或英文就不规范；英文：如果不是26个字母就不规范）
       更改不规范提示样式，最好是加上显示悬浮文本
       等等
    * */

    private boolean unconformable(TextField tf) {
        return tf.getLength() >= 50 || Objects.equals(tf.getText(), " ") || tf.getLength()  == 0;
    }

    private boolean unconformable(TextArea ta) {
        return ta.getLength() >= 50 || Objects.equals(ta.getText(), " ") || ta.getLength() == 0;
    }

    private void solve(TextField tf) {
        tf.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(unconformable(tf)) {
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

    private void solve(TextArea ta) {
        ta.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(unconformable(ta)) {
                ta.setStyle("-fx-background-color: #ffe1e1;-fx-border-style:solid;-fx-border-width: 0 0 1 0;-fx-border-color: #ffb0b0;");
                Timer t = new Timer();
                TimerTask tk = new TimerTask() {
                    @Override
                    public void run() {
                        ta.setStyle(null);
                    }
                };
                t.schedule(tk, 2000);
            }
        });
    }

}
