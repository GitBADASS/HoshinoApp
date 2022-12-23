package com.hoshino.hoshinoscene.custom;

import com.alibaba.fastjson2.JSONObject;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//目前该类仍然是比较呆板，日后会弃用也说不定
//这种展示类应该侧重与存储信息的JSON文件的联动，并且应该有各种各样的鼠标事件（比如右键菜单，左键打开学习库等等等
public class WarehouseStyle extends VBox {
    String title;
    String description;
    public WarehouseStyle(String title, String description) {
        this.title = title;
        this.description = description;
        Label titleText = new Label(title);
        setId(title);//设置ID为title方便日后操作（比如删除、防重等
        Label descriptionText = new Label(description);
        Label test = new Label();
        getChildren().add(titleText);
        getChildren().add(descriptionText);
        getChildren().add(test);
        setOnMousePressed(e->test.setText("NONONONONO"));
        //待补充...
        //...
    }

    //更应该被选用的一种方法（到时候可以解析JSON文件为JSONObject，然后传入，待定
    public WarehouseStyle(JSONObject jsonObject) {
        //施工中...
    }

    //更改方法
    public static WarehouseStyle change(String newName, String newDescription, WarehouseStyle whs) {
        if(newName.equals("")||newName.equals(" ")||newName.equals(whs.title)||newDescription.equals("")||newDescription.equals(" ")||newDescription.equals(whs.description)) {
            return whs;
        } else {
            //待补充...
            //...
            return new WarehouseStyle(newName, newDescription);
        }
    }
}
