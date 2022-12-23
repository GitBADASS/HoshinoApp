package com.hoshino.hoshinoscene.tools;


import com.alibaba.fastjson2.JSONObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

//存储类，负责存储数据以及数据通信
public class Storage {

    //创建一个JSON对象用于存放学习库信息
    static JSONObject warehouse = new JSONObject();

    //保存方法，传入一个要保存的学习库类，此方法为工具方法，所以设为静态
    public static void save(WordsWarehouse wh) {
        //将该学习库信息存储进JSON对象中
        warehouse.put(wh.getName(), wh);
        //将该JSON对象转为字符串以便后面写入
        String s = warehouse.toJSONString();
        //创建JSON文件并写入
        try {
            FileOutputStream wos = new FileOutputStream(wh.getName()+".json");//开放一个文件输出流（文件名用学习库类的“name”以便日后的操作，文件格式为JSON
            OutputStreamWriter writer = new OutputStreamWriter(wos, StandardCharsets.UTF_8);//构建OutputStreamWriter对象以兼容编码
            writer.append(s);//写入到文件
            writer.close();//关闭 OutputStreamWriter ，保存
            wos.close();//关闭文件输出流，释放资源
        } catch (IOException e) {
            System.out.print("JSON文件创建和写入过程出错");//出错时提示
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> h = new HashMap<>();
        h.put("test", "测试");
        WordsWarehouse w = new WordsWarehouse("Test", "it is a test warehouse", h);
        warehouse.put("test", w);
        String s = warehouse.toJSONString();
        System.out.println(s + "\n" + s.length());
    }*/

}
