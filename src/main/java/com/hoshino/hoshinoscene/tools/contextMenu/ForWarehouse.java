package com.hoshino.hoshinoscene.tools.contextMenu;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class ForWarehouse extends ContextMenu {
    public static final String ICON_STYLE = "-fx-font-size: 15";
    public ForWarehouse() {
        Label iconOfDel = new Label("🚮 ");
        Label iconOfSet = new Label("🛠 ");
        iconOfDel.setStyle(ICON_STYLE);
        iconOfSet.setStyle(ICON_STYLE);
        MenuItem del = new MenuItem("删除", iconOfDel);
        del.setOnAction(e-> System.out.println("删除"));
        MenuItem set = new MenuItem("设置", iconOfSet);
        set.setOnAction(e-> System.out.println("设置"));
        this.getItems().addAll(set, del);
    }
    /*📄📃📕🧾✏✒⚙🔧🚮🛠*/
}
