package com.hoshino.hoshinoscene.tools;

import com.hoshino.hoshinoscene.custom.WarehouseStyle;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    public static ArrayList<WordsWarehouse> wh = new ArrayList<>();
    static {
        HashMap<String, String> h = new HashMap<>();
        h.put("test", "测试");
        h.put("hello", "你好");
        wh.add(new WordsWarehouse("testName", "test test test", h, Storage.wh.size()));
    }

    //保存方法
    public static void save(WordsWarehouse wordsWarehouse) {
        wh.add(wordsWarehouse);
    }

    //删除方法
    public static void delete(int index) {
        wh.remove(index);
    }

    //遍历打印集合
    public static void print() {
        for(WordsWarehouse w : wh) {
            System.out.println(w.toString());
        }
    }

    public static void main(String[] args) {
        print();
    }

}
