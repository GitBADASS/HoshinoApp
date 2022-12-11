package com.hoshino.hoshinoscene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

//主类
public class HoshinoApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //加载fxml文件
        FXMLLoader fxmlLoader = new FXMLLoader(HoshinoApp.class.getResource("hello-view.fxml"));

        //创建场景
        Scene scene = new Scene(fxmlLoader.load());

        //设置标题&图标
        stage.setTitle("Hello!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/little.png"))));

        //设置场景
        stage.setScene(scene);

        //自定义
        stage.initStyle(StageStyle.TRANSPARENT); //隐藏标题栏
        //stage.setIconified(true);//最小化
        //stage.setResizable(false);//大小固定

        //获取Controller类
        Controller controller = fxmlLoader.getController();
        controller.init(stage);//初始化方法

        //显示场景
        stage.show();

    }

    //启动
    public static void main(String[] args) {
        launch();
    }
}