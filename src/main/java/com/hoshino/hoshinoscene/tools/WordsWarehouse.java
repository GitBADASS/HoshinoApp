package com.hoshino.hoshinoscene.tools;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.HashMap;

//单词库bean类
public class WordsWarehouse {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "content")
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(HashMap<String, String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WordsWarehouse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content=" + content +
                '}';
    }
}
