package com.hoshino.hoshinoscene.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WarehouseStyle extends VBox {
    public WarehouseStyle(String title, String description) {
        Label titleText = new Label(title);
        setId(title);//设置ID为title方便日后操作（比如删除、防重等
        Label descriptionText = new Label(description);
        Label test = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(test);
        setOnMousePressed(e->test.setText("NONONONONO"));
    }

    //TODO:考虑：是否要创建几个方法专门用于删除、设置（比如重命名、重新定义描述等）？
}
