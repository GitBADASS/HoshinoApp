package com.hoshino.hoshinoscene;

import com.alibaba.fastjson2.JSON;
import com.hoshino.hoshinoscene.custom.WarehouseStyle;
import com.hoshino.hoshinoscene.tools.GenWarehouse;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public VBox root;
    public Button createWarehouse;
    public FlowPane content;
    public Button flush;
    /*
    * 要求：点击创建库按钮进入创建学习库窗口
    * 我们应该提前先创建好一个窗口，然后在触发点击事件的时候调用show()方法，否则会出现多开bug
    * */
    //创建一个新窗口
    GenWarehouse gen = new GenWarehouse();

    //创建库

    public Controller() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*root.setOnMouseEntered(e-> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/
        //刷新
        flush.setOnAction(e-> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        //创建新窗口
        createWarehouse.setOnMousePressed(e->{
            //检查新窗口是否已存在，若为否，则新建，若为是，则将它拽到前面
            if(!gen.isShowing()) {
                gen.show();
            } else {
                gen.toFront();
            }
        });
        try {
            this.load();//加载
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addExitListener(Stage stage) {
        root.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }

    //加载文件并展示
    public void load() throws IOException {
        ArrayList<WordsWarehouse> warehouseList = new ArrayList<>();//用于存放扫描出的该类
        //遍历文件夹读取文件
        File jsons = new File("json\\warehouses");
        File[] fileList = jsons.listFiles();//获取库文件夹下所有文件
        assert fileList != null;
        for (File file : fileList) {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));//获取文件内容
            WordsWarehouse wordsWarehouse = JSON.parseObject(content, WordsWarehouse.class);
            System.out.println("找到"+wordsWarehouse);
            warehouseList.add(wordsWarehouse);
        }
        //率先清空，防止反复添加
        content.getChildren().clear();
        for (WordsWarehouse wh : warehouseList) {
            System.out.println("展示"+wh);
            content.getChildren().add(new WarehouseStyle(wh));//展示
        }
        System.out.println("加载&展示完毕，共有" + warehouseList.size() + "个文件被加载并展示");
    }

/*    public static void main(String[] args) throws IOException {
        load();
    }*/
}