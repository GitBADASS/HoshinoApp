package com.hoshino.hoshinoscene;

import com.alibaba.fastjson2.JSON;
import com.hoshino.hoshinoscene.custom.WarehouseStyle;
import com.hoshino.hoshinoscene.tools.GenWarehouse;
import com.hoshino.hoshinoscene.tools.WordsShowing;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public TextArea descriptionLabel;
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
        //添加单词库展示的滚动板
        ScrollPane sc = new ScrollPane();
        sc.setPrefWidth(200);
        sc.setStyle("-fx-padding: 0");
        sc.setContent(content);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentHBox.getChildren().add(2, sc);//设置它的位置
        /*root.setOnMouseEntered(e-> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/

        //添加初始内容
        ArrayList<WordsShowing> elementsList = new ArrayList<>();
        HashMap<String, String> h = new HashMap<>();
        h.put("nice", "好的");
        h.put("bad", "不好的");
        h.put("Hoshino", "[罗马字]星野");
        h.put("one", "一");
        h.put("tow", "二");
        h.put("three", "三");
        h.put("apple", "苹果");
        h.put("pear", "梨子");
        h.put("four", "四");
        h.put("five", "五");
        h.put("banana", "香蕉");
        nameLabel.setText("星夜社单词库");
        descriptionLabel.setText("欢迎来到[Hoshino]星夜社单词管理库，它的作用是辅助使用者的英语学习。使用者可以[有针对性]地添加[个性化]单词库，有计划地对单词进行分类学习，之后会推出的功能：\n1.对单词库内单词进行抽查练习（比如释义单选、中英互译翻译考察等）；\n2.更加方便的单词库管理；\n3.加入翻译API以增强用户管理、练习单词等操作的体验\n4.以及更多个性化设置...\n规范的单词库：\n1.单词库名称、描述、单词中英文均不能为空\n2.单词库名称不能超过25个字且不能重复、单词库描述不能超过200个字");
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
            content.getChildren().add(new WarehouseStyle(wh, nameLabel, descriptionLabel, wordsShowing));//展示
        }
        System.out.println("加载&展示完毕，共有" + warehouseList.size() + "个文件被加载并展示");
    }

/*    public static void main(String[] args) throws IOException {
        load();
    }*/
}