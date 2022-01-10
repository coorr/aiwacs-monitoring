package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.TopologyNode;

@Repository
public interface TopologyNodeRepository  extends JpaRepository<TopologyNode, Integer> {

    
    @Query("select t from TopologyNode t order by t.id asc")
    List<TopologyNode> getTopologyNode();
    
    @Modifying(clearAutomatically=true)
    @Query(value = "insert into topology_node(node_id,equipment,loc,setting_ip) values (:id,:equipment,:loc,:settingIp)" , nativeQuery = true)
    void insertTopologyNode(Integer id, String equipment,String loc, String settingIp);

//    @Modifying(clearAutomatically=true)
//    @Query(value="insert into group_equipment_join(group_id, equipment_id) values (:parentKey, :deviceKeys)", nativeQuery = true)
//    void insertDeviceMapping(@Param("parentKey") Integer parentKey,@Param("deviceKeys") int[] deviceKeys);
    
//    @Query("select e,j from Equipment e, GroupEquipmentJoin j where e.id = j.equipment_id and j.group_id = ?1 ")
//    List<Equipment> getEquipment(Integer id);
    
}
