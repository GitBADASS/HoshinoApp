package com.hoshino.hoshinoscene.tools;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WarehouseInVBox extends VBox {

    public WarehouseInVBox(String name, String description) {
        setWarehouseSize();
        Label nameText = new Label(name);
        Label descriptionText = new Label(description);
        getChildren().add(nameText);
        getChildren().add(descriptionText);
    }

    public WarehouseInVBox() {
        setWarehouseSize();
        Label nameText = new Label("name");
        Label descriptionText = new Label("This is a test description, wow.wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        getChildren().add(nameText);
        getChildren().add(descriptionText);
    }

    private void setWarehouseSize() {
        int a = 60;
        int b = 100;
        setPrefHeight(a);
        setMinHeight(a);
        setMaxHeight(a);
        setPrefWidth(b);
        setMinWidth(b);
        setMaxWidth(b);
    }
}
