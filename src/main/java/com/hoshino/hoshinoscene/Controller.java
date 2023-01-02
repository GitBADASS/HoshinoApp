package com.hoshino.hoshinoscene;

import com.alibaba.fastjson2.JSON;
import com.hoshino.hoshinoscene.custom.WarehouseStyle;
import com.hoshino.hoshinoscene.tools.GenWarehouse;
import com.hoshino.hoshinoscene.tools.WordsInput;
import com.hoshino.hoshinoscene.tools.WordsShowing;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable{
    public VBox root;
    public Button createWarehouse;
    public FlowPane content;
    public Button flush;
    public HBox contentHBox;
    public Label nameLabel;
    public Label descriptionLabel;
    public ListView<WordsShowing> wordsShowing;
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
        ScrollPane sc = new ScrollPane();
        sc.setPrefWidth(200);
        sc.setStyle("-fx-padding: 0");
        sc.setContent(content);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentHBox.getChildren().add(2, sc);
        /*root.setOnMouseEntered(e-> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/
        ArrayList<WordsShowing> elementsList = new ArrayList<>();
        HashMap<String, String> h = new HashMap<>();
        h.put("nice", "好的");
        h.put("bad", "不好的");
        h.put("one", "一");
        h.put("tow", "二");
        h.put("three", "三");
        h.put("apple", "苹果");
        h.put("pear", "梨子");
        h.put("four", "四");
        h.put("five", "五");
        h.put("banana", "香蕉");
        WordsWarehouse wh = new WordsWarehouse("测试", "好的测试", h);
        Set<String > keySet = wh.getContent().keySet();
        for(String key : keySet) {
            WordsShowing ws = new WordsShowing(wh.getContent().get(key), key);
            elementsList.add(ws);
        }
        ObservableList<WordsShowing> el = FXCollections.observableList(elementsList);
        wordsShowing.setItems(el);
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