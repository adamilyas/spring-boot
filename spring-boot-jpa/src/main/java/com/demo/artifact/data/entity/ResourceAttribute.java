package com.demo.artifact.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RESOURCE_ATTRIBUTE")
@Data
public class ResourceAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESOURCE_ATTRIBUTE_ID")
    private long resourceAttributeId;
    @Column(name = "RESOURCE_ID")
    private long resourceId;
    @Column(name = "RESOURCE_ATTRIBUTE_NAME")
    private String resourceAttributeName;
    @Column(name = "RESOURCE_ATTRIBUTE_VALUE")
    private String resourceAttributeValue;
    @Override
    public String toString(){
        return String.format("[ resourceId=%d, resourceAttributeName=%s, resourceAttributeValue=%s ]", 
            resourceId, resourceAttributeName, resourceAttributeValue);
    }
}