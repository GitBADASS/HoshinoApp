package com.hoshino.hoshinoscene;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hoshino.hoshinoscene.tools.GenWarehouse;
import com.hoshino.hoshinoscene.tools.WordsWarehouse;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public VBox root;
    public Button createWarehouse;
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
        //创建新窗口
        createWarehouse.setOnMousePressed(e->{
            //检查新窗口是否已存在，若为否，则新建，若为是，则将它拽到前面
            if(!gen.isShowing()) {
                gen.show();
            } else {
                gen.toFront();
            }
        });

    }

    public void addExitListener(Stage stage) {
        root.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }

    //加载文件并展示
    public static void load() throws IOException {
        //遍历文件夹读取文件
        File jsons = new File("json\\warehouses");
        File[] list = jsons.listFiles();//获取库文件夹下所有文件
        assert list != null;
        for (File file : list) {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));//获取文件内容
            //String jContent = JSONObject.toJSONString(content);
            //WordsWarehouse wordsWarehouse = JSON.parseObject(jContent, WordsWarehouse.class);
            //System.out.println(wordsWarehouse);
        }
    }

    public static void main(String[] args) throws IOException {
        load();
    }
}