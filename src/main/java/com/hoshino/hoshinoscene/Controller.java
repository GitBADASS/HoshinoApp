package com.hoshino.hoshinoscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Controller {

    //从fxml获取最小化按钮
    @FXML
    private Button min;

    //从fxml获取关闭窗口按钮
    @FXML
    private Button close;

    //从fxml获取关闭窗口图标容器
    @FXML
    private ImageView closeImg;

    //设置鼠标滑过/滑出时的图像
    Image closeHover = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/closeHover.png")));
    Image closeOri = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/close.png")));

    @FXML
    protected void exit() {
        System.exit(0);
    }

    public void init(Stage stage) {
        min.setOnAction((e)->{
            stage.setIconified(true);
        });
        close.setOnMouseMoved((e)->{
            closeImg.setImage(closeHover);
        });
        close.setOnMouseExited((e)->{
            closeImg.setImage(closeOri);
        });
    }
}