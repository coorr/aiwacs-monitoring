package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.DiagramGroup;

@Repository
public interface DiagramGroupRepository extends JpaRepository<DiagramGroup, Integer> {

    @Query("select d from DiagramGroup d order by d.id asc")
    List<DiagramGroup> getDiagramGroup();
    
//    @Modifying(clearAutomatically=true)
//    @Query("delete from TopologyLink l where l.id = ?1 ")
//    Integer deleteTopogolyLink(int id);
}
