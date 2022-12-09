package com.hoshino.hoshinoscene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//主类
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //加载fxml文件
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //创建新的场景
        Scene scene = new Scene(fxmlLoader.load());

        //设置标题
        stage.setTitle("Hello!");

        //设置场景
        stage.setScene(scene);

        //自定义
        stage.initStyle(StageStyle.TRANSPARENT); //隐藏标题栏
        //stage.setIconified(true);//最小化
        //stage.setResizable(false);//大小固定

        //创建内容
        //用于固定窗口的按钮
        Button pin = new Button("HELLO");
        pin.setOnAction((e)->{
            stage.setIconified(true);
        });

        //显示场景
        stage.show();

    }

    //启动
    public static void main(String[] args) {
        launch();
    }
}