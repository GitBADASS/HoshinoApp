package com.hoshino.hoshinoscene.tools;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Set;

public class WordsShowing extends HBox {
    public WordsShowing(String cn, String en) {
        HBox left = new HBox();
        HBox right = new HBox();
        left.setAlignment(Pos.CENTER_LEFT);
        right.setAlignment(Pos.CENTER_LEFT);
        Label cnL = new Label(cn);
        setHgrow(left, Priority.ALWAYS);
        setHgrow(right, Priority.ALWAYS);
        left.getChildren().add(cnL);
        Label enL = new Label(en);
        right.getChildren().add(enL);
        this.getChildren().addAll(left, right);
    }
}
