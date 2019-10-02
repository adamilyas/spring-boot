package com.demo;
// for request
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// for request input
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.atomic.AtomicInteger;

/*
1. 
    GET /demo 
        return {id: int, content: String "from GET request"}
    POST /demo 
        return {id: int, content: String "from POST request"}

2. With request body
    GET /welcome/{company}/{userId} 
        return String "Welcome " + userId + " from " + company
    POST /welcome + {id, content}
        return String
*/

@RestController
public class DemoController {

    private final AtomicInteger idCounterForGet = new AtomicInteger(0);
    private final AtomicInteger idCounterForPost = new AtomicInteger(0);

    //@RequestMapping(value = "demo", method = RequestMethod.GET)
    @GetMapping("demo")
    public DemoObj returnGetRequest(){
        return new DemoObj(idCounterForGet.getAndIncrement(), "from GET request");
    }

    //@RequestMapping(value = "demo", method = RequestMethod.POST)
    @PostMapping("demo")
    public DemoObj returnPostRequest(){
        return new DemoObj(idCounterForPost.getAndIncrement(), "from POST request");
    }

    //using path variable
    @RequestMapping(value = "/welcome/{company}/{userId}", method = RequestMethod.GET)
    public String printGetWelcome(@PathVariable("userId") String userId,
                                  @PathVariable("company") String company){
        return "Welcome " + userId + " from " + company;
    }

    // with request body
    @PostMapping("welcome")
    public String printPostWelcome(@RequestBody DemoObj obj){
        return obj.getContent();
    }
}