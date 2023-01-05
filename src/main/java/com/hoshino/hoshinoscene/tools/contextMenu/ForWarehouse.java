package com.hoshino.hoshinoscene.tools.contextMenu;

import com.hoshino.hoshinoscene.Controller;
import com.hoshino.hoshinoscene.HoshinoApp;
import com.hoshino.hoshinoscene.custom.WarehouseStyle;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import java.io.File;
import java.io.IOException;

public class ForWarehouse extends ContextMenu {
    public static final String ICON_STYLE = "-fx-font-size: 12";
    public ForWarehouse(WarehouseStyle ws) {
        Label iconOfDel = new Label(/*"🚮 "*/);
        Label iconOfSet = new Label(/*"🛠 "*/);
        iconOfDel.setStyle(ICON_STYLE);
        iconOfSet.setStyle(ICON_STYLE);

        MenuItem del = new MenuItem("删除"/*, iconOfDel*/);
        del.setOnAction(e-> {
            File file = new File("json\\warehouses\\"+ws.getId()+".json");
            if(file.delete()){
                System.out.println("成功删除了"+ws.getId()+".json");
                Controller c = HoshinoApp.fxmlLoader.getController();
                try {
                    c.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                System.out.println("删除失败");
            }
        });

        MenuItem set = new MenuItem("设置"/*, iconOfSet*/);
        set.setOnAction(e-> System.out.println("设置"));

        this.getItems().addAll(set, del);
    }
    /*📄📃📕🧾✏✒⚙🔧🚮🛠*/
}
