package com.demo.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class TaskResponse {
    String message;
    ArrayNode data;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public ArrayNode getData(){
        return data;
    }

    public void AddtoData(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.valueToTree(object);
        this.data.add(node);
    }

    public TaskResponse(){
        this.message = "";
        this.data = JsonNodeFactory.instance.arrayNode();
    }

    public TaskResponse(String message){
        this.message = message;
        this.data = JsonNodeFactory.instance.arrayNode();
    }
}