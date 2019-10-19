package com.demo.artifact.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "RESOURCE")
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESOURCE_ID")
    private int resourceId;
    @Column(name = "RESOURCE_NAME")
    private String resourceName;    
    @Column(name = "RESOURCE_VALUE")
    private long resourceValue;
    
    @Override
    public String toString(){
        return String.format("[ resourceId=%d, resourceValue=%d ]", resourceId, resourceValue );
    }
}