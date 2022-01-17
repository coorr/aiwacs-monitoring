package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.TopologyNode;

@Repository
public interface TopologyNodeRepository  extends JpaRepository<TopologyNode, String> {

    
    @Query("select t from TopologyNode t")
    List<TopologyNode> getTopologyNode();
    
    @Query("select t from TopologyNode t where t.diagramId = ?1 ")
    List<TopologyNode> getByNoTopologyNode(Integer diagramId);
    
    @Query("select t from TopologyNode t where t.diagramId = ?1 ")
    TopologyNode getByNoTopologyNodes(Integer diagramId);
    
    @Modifying(clearAutomatically=true)
    @Query("update TopologyNode t set t.loc = ?1 where t.id = ?2 and t.diagramId = ?3 ")
    void updateTopologyNode(String loc, Integer id,Integer diagramId);
//    @Query("update Equipment e set e.hwCpu = :hwCpu , e.hwDisk = :hwDisk , e.hwNic = :hwNic , e.hwSensor = :hwSensor where e.id in (:id) ")  // 개별,통합
//    @Query("update Equipment e set e.settingActive =true where e.id in (:id) ") 
    
    @Modifying(clearAutomatically=true)
    @Query(value = "insert into topology_node(node_id,equipment,loc,setting_ip) values (:id,:equipment,:loc,:settingIp)" , nativeQuery = true)
    void insertTopologyNode(Integer id, String equipment,String loc, String settingIp);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from TopologyNode t where t.id = ?1 ")
    Integer deleteTopogolyNode(String id);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from TopologyNode t where t.diagramId in (?1) ")
    Integer deleteAllTopogolyNode(int[] diagramId);
    
    
    

//    @Modifying(clearAutomatically=true)
//    @Query(value="insert into group_equipment_join(group_id, equipment_id) values (:parentKey, :deviceKeys)", nativeQuery = true)
//    void insertDeviceMapping(@Param("parentKey") Integer parentKey,@Param("deviceKeys") int[] deviceKeys);
    
//    @Query("select e,j from Equipment e, GroupEquipmentJoin j where e.id = j.equipment_id and j.group_id = ?1 ")
//    List<Equipment> getEquipment(Integer id);
    
}
