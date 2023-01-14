package com.hoshino.hoshinoscene.tools;

import com.hoshino.hoshinoscene.Controller;
import com.hoshino.hoshinoscene.HoshinoApp;
import com.hoshino.hoshinoscene.tools.animations.AnimatedGen;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

//TODO:需要增添对中英文内容的判断（使用正则表达式
public class GenController implements Initializable {
    public Button save;//保存按钮
    public TextArea description;//学习库描述
    public TextField name;//学习库名称
    public VBox root;//最底部VBox
    public Label warnText;//警告文本
    public Button clearName;//清空名称输入框按钮
    public Button addElement;
    public ListView<WordsInput> elementsList;
    public Button deleteElement;
    public Button cancel;//取消按钮
    public Button clearElements;

    //将所有文本框放入
    List<TextInputControl> textInputList = new ArrayList<>();

    //实现方法
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<WordsInput> elementsArrayList = new ArrayList<>();
        addElement.setOnAction(e->{
            //TODO:增加对选中元素的处理（比如“-”按钮按下时删除选中元素
            //TODO:待新增功能： “清空“ 按钮、ListView 多选 功能
            //此处解释：判断如果上一个元素内容为空，则不继续添加
            if(elementsList.getItems().size() == 0 || elementsList.getItems().get(elementsArrayList.size()-1).cn.getLength() != 0 && elementsList.getItems().get(elementsArrayList.size()-1).en.getLength() != 0) {
                //创建ListView的元素——WordsInput类的对象
                WordsInput h = new WordsInput();
                //将它放到元素ArrayList里
                elementsArrayList.add(h);
                //将ArrayList放到ObservableList里
                ObservableList<WordsInput> el = FXCollections.observableList(elementsArrayList);
                //将el放到ListView中
                elementsList.setItems(el);
                //默认聚焦新创建的文本框
                h.cn.requestFocus();
                //默认选中最后一个
                elementsList.getSelectionModel().select(elementsArrayList.size() - 1);
                //防止一直输入空格
                deleteSpace(h.cn);
                deleteSpace(h.en);
                //放入输入框集合以便后期清空
                textInputList.add(h.cn);
                textInputList.add(h.en);
            }
        });
        deleteElement.setOnAction(e->{
            int index = elementsList.getSelectionModel().getSelectedIndex();
            if(elementsArrayList.size() != 0) {
                elementsList.getItems().remove(index);
            }
        });

        //清空方法
        addClearFun(name, clearName);//名称输入框清空
        //addClearFun(description, clearDescription);//描述输入框清空
        deleteSpace(name);
        deleteSpace(description);
        //TODO:需要增添对中英文内容的判断（使用正则表达式

        //检查是否最基本地合格：
        solve(name);
        solve(description);

        //警告文字默认隐藏
        warnText.setOpacity(0);
    }

    //保存退出的实现
    public void init(Stage stage) {
        //取消按钮的实现
        cancel.setOnAction(e->{
            stage.close();
            System.out.println("退出成功");//控制台输出语句
            //将所有输入框对象放入集合并清空（下方ESC退出并不会清空
            textInputList.add(name);//名称
            textInputList.add(description);//描述
            clear(textInputList);//清空所有输入框
            //清空单词框
            elementsList.getItems().clear();
            System.out.println("清空成功");//控制台输出语句
        });
        //添加动画 TODO:后期考虑是否单独创建一个类继承自 FadeTransition 用于添加动画
        //淡出：
        FadeTransition animationOut = new FadeTransition(Duration.millis(500), warnText);
        animationOut.setFromValue(1);
        animationOut.setToValue(0);
        animationOut.setAutoReverse(true);
        //淡入：
        FadeTransition animationIn = new FadeTransition(Duration.millis(500), warnText);
        animationIn.setFromValue(0);
        animationIn.setToValue(1);
        animationIn.setAutoReverse(true);

        //清空单词录入框
        clearElements.setOnAction(e->{
            elementsList.getItems().clear();
            System.out.println("单词成功清空");
        });
        //添加点击事件监听
        save.setOnMousePressed(e->{
            //为添加单词学习库做准备
            HashMap<String, String> words = new HashMap<>();//创建哈希映射
            //创建单词学习库对象
            WordsWarehouse wh = new WordsWarehouse(name.getText(), description.getText(), words);
            //判断是否符合规范
            boolean canBeSaved = !unconformable(name) && !unconformable(description) && elementsList.getItems().size() != 0 && elementsList.getItems().get(elementsList.getItems().size()-1).cn.getLength() != 0 && elementsList.getItems().get(elementsList.getItems().size()-1).en.getLength() != 0;
            if(canBeSaved) {
                System.out.println("[√]单词映射符合条件");
                //保存
                for(WordsInput w : elementsList.getItems()) {
                    words.put(w.en.getText(), w.cn.getText());
                }
                //如果保存成功
                if(Storage.save(wh) && words.size() != 0){
                    System.out.println("文件成功保存！");//控制台输出语句
                    //将所有输入框对象放入集合并清空（下方ESC退出并不会清空
                    textInputList.add(name);//名称
                    textInputList.add(description);//描述
                    clear(textInputList);//清空所有输入框
                    //清空单词框
                    elementsList.getItems().clear();
                    stage.close();//退出
                    //Controller类加载
                    Controller c = HoshinoApp.fxmlLoader.getController();
                    try {
                        c.load();
                    } catch (IOException ex) {
                        System.out.println("文件加载失败");
                    }
                } else {
                    if(warnText.getOpacity()==0) {
                        warnText.setText("文件已存在");
                        animationIn.play();//显示警告文本
                        //设置延时
                        Timer t = new Timer();
                        //延时任务
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                animationOut.play();
                            }
                        };
                        t.schedule(tt, 3000);//3s后执行
                    }
                }
            } else {
                //检查按钮触发的时候控件不透明度是否为零，防止出现动画乱抽
                /*if(warnText.getOpacity()==0) {
                    warnText.setText("保存失败，请检查书写是否规范");
                    animationIn.play();//显示警告文本
                    //设置延时
                    Timer t = new Timer();
                    //延时任务
                    TimerTask tt = new TimerTask() {
                        @Override
                        public void run() {
                            animationOut.play();
                        }
                    };
                    t.schedule(tt, 3000);//3s后执行
                }*/
                ArrayList<Label> ls = new ArrayList<>();
                ls.add(warnText);
                boolean emptyDescription = description.getText().length()==0;
                boolean emptyName = name.getText().length()==0;
                boolean noWords = elementsList.getItems().size()==0;
                WordsInput lastOne = elementsList.getItems().get(elementsList.getItems().size()-1);
                boolean emptyWord = lastOne.cn.getText().length()==0 || lastOne.en.getText().length()==0;
                if(emptyDescription && emptyName && noWords) {
                    AnimatedGen.warningAnimation("试着添加一些东西吧", ls);
                }
                if(emptyWord) {
                    AnimatedGen.warningAnimation("单词还没有填写完毕", ls);
                }else if(emptyName&&emptyDescription) {
                    AnimatedGen.warningAnimation("名称和描述都是必填项", ls);
                }
                else if(emptyName) {
                    AnimatedGen.warningAnimation("名称是必须添加的", ls);
                } else if(emptyDescription) {
                    AnimatedGen.warningAnimation("描述也是必须添加的", ls);
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
    private void deleteSpace(TextInputControl t) {
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
        //为空、空格且字数大于等于25的内容是不符合规范的
        return tf.getLength() >= 20 || Objects.equals(tf.getText(), " ") || tf.getLength()  == 0;
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

    //清空方法
    private void clear(List<TextInputControl> t) {
        for(TextInputControl e : t) {
            e.setText("");
        }
    }

    //增添内容到ListView


}
