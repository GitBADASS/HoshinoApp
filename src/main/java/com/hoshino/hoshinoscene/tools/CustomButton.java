package com.hoshino.hoshinoscene.tools;

import javafx.scene.control.Button;

public class CustomButton extends Button {
    private String text;
    public CustomButton(String text) {
        this.text = text;
        setText(text);
        setStyle("-fx-background-color: #e1e1e1;-fx-padding: 5 15 5 15;");
        setOnMouseMoved(e->setStyle("-fx-background-color: #d5d5d5;"));
    }
}
