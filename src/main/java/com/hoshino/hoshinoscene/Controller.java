package com.hoshino.hoshinoscene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //从fxml获取最小化按钮
    @FXML
    private Button min;

    //从fxml获取关闭窗口按钮
    @FXML
    private Button close;

    //从fxml获取关闭窗口图标容器
    @FXML
    private ImageView closeImg;

    @FXML
    private HBox titlePane;

    @FXML
    private Button maximum;

    @FXML
    private ImageView maxImg;

    public void init() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*min.setOnAction((e)-> stage.setIconified(true));
        close.setOnMouseMoved((e)-> closeImg.setImage(closeHover));
        close.setOnMouseExited((e)-> closeImg.setImage(closeOri));*/
    }
}