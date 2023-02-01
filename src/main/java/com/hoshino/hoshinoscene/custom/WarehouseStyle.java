package com.hoshino.hoshinoscene.custom;

import com.hoshino.hoshinoscene.Controller;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import com.hoshino.hoshinoscene.tools.contextMenu.ForWarehouse;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

//TODO:将文字 Label 换成 TextArea
//更改方法原则：清除重建，而非表面上的更改
//目前该类仍然是比较呆板，日后会弃用也说不定
//这种展示类应该侧重与存储信息的JSON文件的联动，并且应该有各种各样的鼠标事件（比如右键菜单，左键打开学习库等等等
public class WarehouseStyle extends VBox {
    String title;
    String description;
    int index;
    WordsWarehouse wordsWarehouse;

    public static final int WAREHOUSE_WIDTH = 200;
    //public static final int WAREHOUSE_HEIGHT = 120;
    //传入一个WordsWarehouse的对象
    public WarehouseStyle(WordsWarehouse wh, Controller controller) {

        this.wordsWarehouse = wh;

        this.title = wh.getName();

        this.description = wh.getDescription();

        Label titleText = new Label(title);
        Tooltip titleTT = new Tooltip(wh.getName());
        titleText.setTooltip(titleTT);
        titleText.setStyle(/*"-fx-border-width: 0 0 1 0; -fx-border-color: #a2a2a2;" +*/ "-fx-pref-width: " + (WAREHOUSE_WIDTH - 50) + "; -fx-padding: 3; -fx-font-weight: bold; -fx-font-size: 15");
        setId(title);//设置ID为title方便日后操作（比如删除、防重等

        Label descriptionText = new Label(description);
        Tooltip descriptionTT = new Tooltip(wh.getDescription());
        descriptionText.setTooltip(descriptionTT);
        descriptionText.setWrapText(true);
        descriptionText.setStyle("-fx-pref-width: " + WAREHOUSE_WIDTH + ";  -fx-padding: 3;"/* -fx-pref-height: 60; -fx-min-height: 35;*/);
        this.setHeight(titleText.getHeight()+descriptionText.getHeight()*2);
        this.setMaxHeight(150);

        Label space = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(space);

        this.getStyleClass().add("warehouseShowing");
        titleText.setContextMenu(new ForWarehouse(this));
        descriptionText.setContextMenu(new ForWarehouse(this));
        this.setOnMouseClicked(e->{
            //显示
            controller.setToShow(wh);
            controller.setShowingWarehouse(wh);
            controller.findFocus();
            //System.out.println(controller.getShowingWarehouse());
            //System.out.println("正在展示 "+controller.showingWarehouse+".json 文件的详情");
        });
    }

    public int getIndex() {
        return this.index;
    }

}
