package com.hoshino.hoshinoscene.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WarehouseStyle extends VBox {
    public WarehouseStyle(String title, String description) {
        Label titleText = new Label(title);
        setId(title);
        Label descriptionText = new Label(description);
        Label test = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(test);
        setOnMousePressed(e->test.setText("NONONONONO"));
    }
}
