package com.demo;

public class DemoObj {
    private final Integer id;
    private final String content;

    public DemoObj(Integer id, String content){
        // def __init__
        this.id = id;
        this.content = content;
    }

    public Integer getId(){
        return id;
    }

    public String getContent(){
        return content;
    }
}