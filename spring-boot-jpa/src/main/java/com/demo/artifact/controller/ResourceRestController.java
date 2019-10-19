package com.demo.artifact.controller;

import com.demo.artifact.service.ResourceService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceRestController {

    @Autowired
    private ResourceService service;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ObjectNode countResources() {
        return service.numberOfResources();
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ObjectNode findAllData() {
        return service.findAllResources();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ObjectNode findById(@PathVariable("id") int id) {
        return service.findByResourceId(id);
    }
}