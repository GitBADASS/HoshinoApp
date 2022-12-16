package com.hoshino.hoshinoscene;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public VBox root;
    public Button createWarehouse;
    /*
    * 要求：点击创建库按钮进入创建学习库窗口
    * 我们应该提前先创建好一个窗口，然后在触发点击事件的时候调用show()方法，否则会出现多开bug
    * */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createWarehouse.setOnMousePressed(e->{

        });
    }
}