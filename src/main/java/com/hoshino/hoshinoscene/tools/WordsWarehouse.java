package com.hoshino.hoshinoscene.tools;

import com.alibaba.fastjson2.annotation.JSONField;
import com.hoshino.hoshinoscene.custom.WarehouseStyle;

import java.util.HashMap;

//单词库bean类
public class WordsWarehouse {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "content")
    private HashMap<String, String> content;

    private WarehouseStyle targetWs;

    public WordsWarehouse(String id, String name, String description, HashMap<String, String> content) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public WarehouseStyle getTargetWs() {
        return targetWs;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTargetWs(WarehouseStyle targetWs) {
        this.targetWs = targetWs;
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
