package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;

@Repository
public interface GroupRepository  extends JpaRepository<Group, Integer> {

    @Query("select g from Group g order by g.id asc")
    List<Group> getGroup();
    
    @Query("select e,j from Equipment e, GroupEquipmentJoin j where e.id = j.equipment_id and j.group_id =:id ")
    List<Equipment> getEquipment(@Param("id") Integer id);
    
    @Query("select j from GroupEquipmentJoin j where j.group_id =:id ")
    List<GroupEquipmentJoin> getGroupEquipmentJoin(@Param("id") Integer id);
    
    @Query("select e from Equipment e where e.id not in (select g.equipment_id from GroupEquipmentJoin g) AND e.deletedFlag = true order by e.id desc ") //  
    List<Equipment>  unGroupEquipment();
    
    @Modifying(clearAutomatically=true)
    @Query("delete from Group g where g.id in (:id)")
    Integer deleteGroup(@Param("id") int[] id);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from GroupEquipmentJoin j where j.equipment_id in (:id)")
    Integer deleteEquipment(@Param("id") int[] id);
    
    @Modifying(clearAutomatically=true)
    @Query(value="insert into group_equipment_join(group_id, equipment_id) values (:parentKey, :deviceKeys)", nativeQuery = true)
    void insertDeviceMapping(@Param("parentKey") Integer parentKey,@Param("deviceKeys") int[] deviceKeys);
    
    @Modifying(clearAutomatically=true)
    @Query("update Group g set g.treeName =:treeName where g.id =:id ") 
    void updateGroupName(@Param("id") Integer id,@Param("treeName") String treeName  );
    
    @Query("select g from Group g where g.id =:id")
    List<Group> getGroupName(@Param("id") Integer id);
    
//    @Query("select c from GroupChildren c,Group g where g.id = c.parent and g.id =:id")
//    List<Group> getGroupChildren(@Param("id") Integer id);
//  
//    @Query("select e from Equipment e, GroupChildren c where e.id = c.equipment and c.id =:id")
//    List<Equipment> getGroupChildrenEquipment(@Param("id") Integer id);


}
