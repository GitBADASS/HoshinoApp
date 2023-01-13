package com.hoshino.hoshinoscene.custom;

import com.hoshino.hoshinoscene.tools.WordsShowing;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import com.hoshino.hoshinoscene.tools.contextMenu.ForWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Set;

//TODO:将文字 Label 换成 TextArea
//更改方法原则：清除重建，而非表面上的更改
//目前该类仍然是比较呆板，日后会弃用也说不定
//这种展示类应该侧重与存储信息的JSON文件的联动，并且应该有各种各样的鼠标事件（比如右键菜单，左键打开学习库等等等
public class WarehouseStyle extends VBox {
    String title;
    String description;

    public static final int WAREHOUSE_WIDTH = 200;
    //public static final int WAREHOUSE_HEIGHT = 120;
    //传入一个WordsWarehouse的对象
    public WarehouseStyle(WordsWarehouse wh, Label nameL, TextArea descriptionL,  ListView<WordsShowing> wsList) {
        this.title = wh.getName();

        this.description = wh.getDescription();

        Label titleText = new Label(title);
        titleText.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: #a2a2a2; -fx-pref-width: " + (WAREHOUSE_WIDTH - 50) + "; -fx-padding: 3;");
        setId(title);//设置ID为title方便日后操作（比如删除、防重等

        Label descriptionText = new Label(description);
        descriptionText.setWrapText(true);
        descriptionText.setStyle("-fx-pref-width: " + WAREHOUSE_WIDTH + ";  -fx-padding: 3;"/* -fx-pref-height: 60; -fx-min-height: 35;*/);
        this.setHeight(titleText.getHeight()+descriptionText.getHeight());
        this.setMaxHeight(120);

        Label test = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(test);

        //TODO:将如下繁杂的更改简化为一个方法
        this.getStyleClass().add("warehouseShowing");
        titleText.setContextMenu(new ForWarehouse(this));
        descriptionText.setContextMenu(new ForWarehouse(this));
        ArrayList<WordsShowing> wsAL = new ArrayList<>();
        this.setOnMouseClicked(e->{
            wsList.getItems().clear();
            Set<String > keySet = wh.getContent().keySet();
            for(String key : keySet) {
                WordsShowing ws = new WordsShowing(wh.getContent().get(key), key);
                wsAL.add(ws);
            }
            ObservableList<WordsShowing> el = FXCollections.observableList(wsAL);
            wsList.setItems(el);
            nameL.setText(wh.getName());
            descriptionL.setText(wh.getDescription());
            System.out.println("正在展示 "+wh.getName()+".json 文件的详情");
        });
    }

}
