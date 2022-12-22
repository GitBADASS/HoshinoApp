package com.hoshino.hoshinoscene.tools;


import com.alibaba.fastjson2.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Storage {

    static JSONObject warehouse = new JSONObject();


    public void save(WordsWarehouse wh) {
        warehouse.put(wh.getName(), wh);
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> h = new HashMap<>();
        h.put("test", "测试");
        WordsWarehouse w = new WordsWarehouse("Test", "it is a test", h);
        warehouse.put("test", w);
    }

}
