package com.hoshino.hoshinoscene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

//主类
//TODO:新建一个类，存放指定 css 样式文件，各个页面样式均来自该类（可能需要另外的配置文件，到时候能合并就和其他配置文件合并（这个可以手动创建好
//TODO: 1.右键菜单 2.搜索功能
public class HoshinoApp extends Application {
    public static FXMLLoader fxmlLoader = new FXMLLoader(HoshinoApp.class.getResource("app-view.fxml"));
    @Override
    public void start(Stage stage) throws IOException {
        //加载fxml文件
        Parent root = fxmlLoader.load();

        //创建场景
        Scene scene = new Scene(root);

        //设置标题&图标
        stage.setTitle("Hoshino");
        stage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/little.png"))));

        //设置
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(scene);

        //自定义
        stage.initStyle(StageStyle.UNIFIED); //隐藏标题栏
        stage.setResizable(true);

        //实现Controller方法
        Controller c = fxmlLoader.getController();//获得对应Controller对象
        c.addListenerToExit(stage);

        //显示场景
        stage.show();

    }

    @Override
    public void stop() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssz");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("\033[31m[已退出]\033[m " + formatter.format(date));
    }

    //启动
    public static void main(String[] args) {
        launch();
    }
}