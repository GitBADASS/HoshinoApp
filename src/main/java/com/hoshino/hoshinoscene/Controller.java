package com.hoshino.hoshinoscene;

import com.hoshino.hoshinoscene.tools.DragUtil;
import com.hoshino.hoshinoscene.tools.DrawUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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

    //设置鼠标滑过/滑出时的图像
    Image closeHover = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/closeHover.png")));
    Image closeOri = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/close.png")));
    Image restoreImg = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/restore.png")));
    Image oriMax = new Image(Objects.requireNonNull(Controller.class.getResourceAsStream("icons/max.png")));

    @FXML
    protected void exit() {
        System.exit(0);
    }

    public void init(Stage stage, VBox root) {
        min.setOnAction((e)->{
            stage.setIconified(true);
        });
        close.setOnMouseMoved((e)->{
            closeImg.setImage(closeHover);
        });
        close.setOnMouseExited((e)->{
            closeImg.setImage(closeOri);
        });
        maximum.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                maxImg.setImage(restoreImg);
                stage.setMaximized(true);
                maximum.setOnMousePressed(e->{
                    stage.setMaximized(false);
                    maxImg.setImage(oriMax);
                });
            }
        });
        /*maximum.setOnMousePressed((e)->{
            stage.setMaximized(true);
        });*/
        DragUtil.addDragListener(stage, titlePane);
        DrawUtil.addDrawFunc(stage, root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}