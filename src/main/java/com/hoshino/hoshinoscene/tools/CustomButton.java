package com.hoshino.hoshinoscene.tools;

import javafx.scene.control.Button;

public class CustomButton extends Button {
    private String text;
    private final String STYLE = "-fx-background-color: #e1e1e1;-fx-padding: 5 15 5 15;";
    private final String HOVER_STYLE = "-fx-background-color: #d5d5d5;";
    public CustomButton(String text) {
        this.text = text;
        setText(text);
        setStyle(STYLE);
        setOnMouseMoved(e->setStyle(HOVER_STYLE));
    }

    public CustomButton(){
        setStyle(STYLE);
        setOnMouseMoved(e->setStyle(HOVER_STYLE));
    }
}
