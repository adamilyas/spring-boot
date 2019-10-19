package com.demo.artifact.data.repository;

import com.demo.artifact.data.entity.ResourceAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAttributeRepository extends JpaRepository<ResourceAttribute, Long>{
}
