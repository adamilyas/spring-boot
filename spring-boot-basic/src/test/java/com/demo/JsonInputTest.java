package com.demo;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
// check how to set as properties
public class JsonInputTest {

    @Test
    public void readJsonAbsolutePathTest () throws Exception {
        String absolutePath = "C:\\Users\\d942528\\Desktop\\learn\\spring-boot\\spring-boot-basic\\src\\test\\java\\com\\demo\\test-parameters.json";
        File file = new File(absolutePath);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.readValue(file, ObjectNode.class);
        assertTrue(objectNode.has("name"));
    }

    @Test
    public void readJsonRelativePathTest () throws Exception {
        String relativePath = "./src/test/java/com/demo/test-parameters.json";
        File file = new File(relativePath);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.readValue(file, ObjectNode.class);
        assertTrue(objectNode.has("name"));
    }
}