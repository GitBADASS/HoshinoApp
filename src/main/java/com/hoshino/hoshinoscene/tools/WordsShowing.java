package com.hoshino.hoshinoscene.tools;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Set;

public class WordsShowing extends HBox {
    public WordsShowing(String cn, String en) {
        Label cnL = new Label(cn);
        Label enL = new Label(en);
        this.setSpacing(10);
        this.getChildren().addAll(cnL, enL);
    }
}
