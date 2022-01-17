package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.Equipment;

@Repository
public interface DiagramGroupRepository extends JpaRepository<DiagramGroup, Integer> {

    @Query("select d from DiagramGroup d order by d.id asc")
    List<DiagramGroup> getDiagramGroup();
    
    @Query("select d from DiagramGroup d where d.id =:id")
    DiagramGroup findOne(Integer id);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from DiagramGroup d where d.id in (?1) ")
    Integer deleteDiagramGroup(int[] groupId);
    
//    @Modifying(clearAutomatically=true)
//    @Query("delete from TopologyLink l where l.id = ?1 ")
//    Integer deleteTopogolyLink(int id);
}
