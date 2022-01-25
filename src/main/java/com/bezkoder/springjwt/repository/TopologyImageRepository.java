package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.TopologyImage;
import com.bezkoder.springjwt.models.TopologyLink;

@Repository
public interface TopologyImageRepository extends JpaRepository<TopologyImage, Integer> {

    @Query("select i from TopologyImage i ")
    List<TopologyImage> getTopologyImage();
    
    @Query("select i from TopologyImage i where i.diagramId = ?1 ")
    List<TopologyImage> getByNoTopologyImage(Integer diagramId);
    
}
