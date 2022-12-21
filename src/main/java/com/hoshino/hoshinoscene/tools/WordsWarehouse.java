package com.hoshino.hoshinoscene.tools;

import java.util.HashMap;

//单词库bean类
public class WordsWarehouse {
    private String name;

    private String description;

    private HashMap<String, String> content = new HashMap<>();

    public WordsWarehouse(String name, String description, HashMap<String, String> content) {
        this.name = name;
        this.description = description;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getContent() {
        return content;
    }
}
