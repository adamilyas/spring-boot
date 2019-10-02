package com.demo;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TestController {
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public DemoObj returnGetResponse(){
        DemoObj obj = new DemoObj(942528, "Hello World");
        return obj;
    }
}
