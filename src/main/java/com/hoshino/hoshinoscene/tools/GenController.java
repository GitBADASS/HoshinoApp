package com.hoshino.hoshinoscene.tools;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

//TODO:需要增添对中英文内容的判断（使用正则表达式
public class GenController implements Initializable {
    public TextField en;//英文输入框
    public TextField cn;//中文输入框
    public Button clearEn;//英文清空框
    public Button clearCn;//中文清空框
    public Button save;//保存按钮
    public TextArea description;//学习库描述
    public TextField name;//学习库名称
    public VBox root;//最底部VBox

    //实现方法
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //清空方法
        addClearFun(en, clearEn);//英文输入框清空
        addClearFun(cn, clearCn);//中文输入框清空
        deleteEmpty(en);
        deleteEmpty(cn);
        deleteEmpty(name);
        deleteEmpty(description);
        //需要增添对中英文内容的判断（使用正则表达式

        //检查是否最基本地合格：
        solve(en);
        solve(cn);
        solve(name);
        solve(description);
    }

    //保存退出的实现
    public void init(Stage stage) {
        //添加点击事件监听
        save.setOnMousePressed(e->{
            //为添加单词学习库做准备
            HashMap<String, String> h = new HashMap<>();//创建哈希映射
            //判断内容是否合格
            if(!unconformable(en) && !unconformable(cn)) {
                h.put(en.getText(), cn.getText()); //TODO:这里要根据后期需求整改（需要它添加多个中英文映射而不是只一个
            }
            //创建单词学习库对象
            WordsWarehouse wh = new WordsWarehouse(name.getText(), description.getText(), h);
            //判断名称与描述是否符合规范
            if(!unconformable(name) && !unconformable(description) && h.size() != 0) {
                //如果保存成功
                if(Storage.save(wh)){
                    System.out.println("Save Successfully!");//控制台输出语句
                    stage.close();//退出
                }
            }
        });

        //TODO:后期添加 ctrl + s 保存事件
        //添加键盘事件监听（esc退出
        root.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }

    /*待完成：
       利用正则表达式判断是否是中/英文 （中：如果含数字或英文就不规范；英文：如果不是26个字母就不规范）
       更改不规范提示样式，最好是加上显示悬浮文本
       等等
    * */

    //防止无限制使用空格，这里添加一个判断是否为空的方法
    private void deleteEmpty(TextInputControl t) {
        //添加文本值改变事件
        //如果文本值等于 " " 则：
        t.textProperty().addListener((observableValue, s, t1)-> {
            if(t.getText().equals(" ")) {
                t.setText("");//清空
            }
        });
    }
    //判断是否不符合规范方法（这里把TF和TA分开而不用它们的父类是因为对文字内容总长的需求不同
    private boolean unconformable(TextField tf) {
        //为空、空格且字数大于等于50的内容是不符合规范的
        return tf.getLength() >= 50 || Objects.equals(tf.getText(), " ") || tf.getLength()  == 0;
    }

    private boolean unconformable(TextArea ta) {
        //为空、空格且字数大于等于200的内容是不符合规范的
        return ta.getLength() >= 200 || Objects.equals(ta.getText(), " ") || ta.getLength() == 0;
    }

    //两个不符合规范就急急急急急的方法
    private void solve(TextField tf) {
        tf.textProperty().addListener((observableValue, aBoolean, t1) -> {
            if(unconformable(tf)) {
                //TODO:这里过于死板，后期需要改掉
                tf.setStyle("-fx-background-color: #ffe1e1;-fx-border-style:solid;-fx-border-width: 0 0 1 0;-fx-border-color: #ffb0b0;");
                Timer t = new Timer();
                TimerTask tk = new TimerTask() {
                    @Override
                    public void run() {
                        tf.setStyle(null);
                    }
                };
                t.schedule(tk, 2000);
            }
        });
    }
    private void solve(TextArea ta) {
        ta.textProperty().addListener((observableValue, aBoolean, t1) -> {
            if(unconformable(ta)) {
                //TODO:这里也是过于死板，后期也需要改掉
                ta.setStyle("-fx-control-inner-background:#ffe1e1;;-fx-border-style:solid;-fx-border-width: 1 1 1 1;-fx-border-color: #ffb0b0;");
                Timer t = new Timer();
                TimerTask tk = new TimerTask() {
                    @Override
                    public void run() {
                        ta.setStyle(null);
                    }
                };
                t.schedule(tk, 2000);
            }
        });
    }

    //指定按钮按下时清空指定输入框
    private void addClearFun(TextInputControl t, Button clearBtn) {
        clearBtn.setOnAction(e->t.clear());
    }
}
