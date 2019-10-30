package com.demo.artifact.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.demo.artifact.data.entity.Resource;
import com.demo.artifact.data.repository.ResourceAttributeRepository;
import com.demo.artifact.data.repository.ResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private static final Logger log = Logger.getLogger(ResourceService.class.getName());

    private ResourceRepository resourceRepository;
    private ResourceAttributeRepository resourceAttributeRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository,
            ResourceAttributeRepository resourceAttributeRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceAttributeRepository = resourceAttributeRepository;
    }

    public ObjectNode numberOfResources() {
        log.info("Executing ResourceService.numberOfResources");
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        long count = resourceRepository.count();
        response.put("numberOfResource", count);
        return response;
    }

    public ObjectNode findByResourceId(int id) {
        log.info("Executing ResourceService.findByResourceId");    
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        try {
            Resource resource = resourceRepository.findFirstByResourceId(id);
            long resourceId = resource.getResourceId();
            String resourceName = resource.getResourceName();
            long resourceValue = resource.getResourceValue();
            response.put("resourceId", resourceId);
            response.put("resourceName", resourceName);
            response.put("resourceValue", resourceValue);
        } catch (Exception e) {
            response.put("message", "no resource found with id: " + id);
            response.put("error", e.toString());
        }
        return response;
    }

    public ObjectNode findAllResources() {
        log.info("Executing ResourceService.findAllResources");    
        
        ObjectMapper mapper = new ObjectMapper();
        Iterable<Resource> allResource = resourceRepository.findAll();
        ArrayNode array = mapper.valueToTree(allResource);
    
        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.putArray("resources").addAll(array);
        long size = resourceRepository.count();
        response.put("size", size);

        return response;
    }

    private int totalItemsInStorage;
    /**
     * @return the totalItemsInStorage
     */
    public int getTotalItemsInStorage() {
        log.info("Executing ResourceService.getTotalItemsInStorage");  
        return totalItemsInStorage;
    }

    @PostConstruct
    public void init() throws Exception {
        log.info("This init methods executes once");
        totalItemsInStorage = (int) resourceRepository.count();
    }
} 