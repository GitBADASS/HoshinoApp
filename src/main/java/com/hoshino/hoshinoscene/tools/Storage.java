package com.hoshino.hoshinoscene.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    public static ArrayList<WordsWarehouse> wh = new ArrayList<>();
    static {
        HashMap<String, String> h = new HashMap<>();
        h.put("test", "测试");
        h.put("hello", "你好");
        wh.add(new WordsWarehouse("testName", "test test test", h));
    }

    public static void save(WordsWarehouse wordsWarehouse) {
        wh.add(wordsWarehouse);
    }
}
