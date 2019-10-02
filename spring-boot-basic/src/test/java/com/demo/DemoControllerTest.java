package com.demo;

import org.junit.jupiter.api.Test;

// additional
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;

// ??
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test() {
        String jsonString = "{\"activityName\": \"Search\", \"resourceType\": \"Fixed\",  \"resourceType\": \"Fixed\" } ";
        try {
            MvcResult result = mvc.perform(
                post("/demo")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            )
            .andExpect(jsonPath("$.content").value("from POST request"))
            .andReturn();
        } catch (Exception e) {
            assertEquals("pass", e);
        }
    }
}

