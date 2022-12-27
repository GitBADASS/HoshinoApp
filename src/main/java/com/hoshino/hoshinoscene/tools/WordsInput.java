package com.hoshino.hoshinoscene.tools;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class WordsInput extends HBox {
    private static final String TF_STYLE = "-fx-background-color: none; -fx-pref-width: 120px; -fx-pref-height: 20px; -fx-border-color: #a8a8a8; -fx-border-width: 0 0 0 1";
    public WordsInput() {
        CheckBox ck = new CheckBox();//TODO:待完成checkbox相关功能
        TextField cn = new TextField();
        TextField en = new TextField();
        cn.setStyle(TF_STYLE);
        en.setStyle(TF_STYLE);
        this.getChildren().addAll(cn, en, ck);
    }
}
