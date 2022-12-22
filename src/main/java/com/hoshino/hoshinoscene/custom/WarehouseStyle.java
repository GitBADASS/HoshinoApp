package com.hoshino.hoshinoscene.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WarehouseStyle extends VBox {
    public WarehouseStyle(String title, String description, int index) {
        Label titleText = new Label(title);
        setId(String.valueOf(index));//index变量分析：添加右键菜单选择删除的时候，将这个id作为索引删除Storage.wh对应元素
        Label descriptionText = new Label(description);
        Label test = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(test);
        setOnMousePressed(e->test.setText("NONONONONO"));
    }
}
