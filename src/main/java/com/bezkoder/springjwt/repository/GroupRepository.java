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
    
    @Query("select e,j from Equipment e, GroupEquipmentJoin j where e.id = j.equipment_id and j.group_id = ?1 ")
    List<Equipment> getEquipment(Integer id);
    
    @Query("select j from GroupEquipmentJoin j where j.group_id =:id ")
    List<GroupEquipmentJoin> getGroupEquipmentJoin(@Param("id") Integer id);
    
    @Query("select e from Equipment e where e.id not in (select g.equipment_id from GroupEquipmentJoin g) AND e.deletedFlag = true order by e.id asc ") //  
    List<Equipment> unGroupEquipment();
    
    @Modifying(clearAutomatically=true)
    @Query("delete from Group g where g.id in (:id)")
    Integer deleteGroup(@Param("id") int[] id);
    
    @Query("select g.treeName from Group g where g.id in (:id)")
    String findName(@Param("id") int[] id);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from GroupEquipmentJoin j where j.equipment_id in (:id)")
    Integer deleteEquipment(@Param("id") int[] id);
    
    @Query("select e.equipment from Equipment e where e.id in (:id)")
    String findNameEquipment(@Param("id") int[] id);
    
    @Query("select e.equipment from Equipment e where e.id =:id")
    String findNameEquipmentInteger(@Param("id") Integer id);
    
    @Query("select g.treeName from Group g where g.id = ?1")
    String findNameGroupInteger(Integer id);
    
    @Modifying(clearAutomatically=true)
    @Query(value="insert into group_equipment_join(group_id, equipment_id) values (:parentKey, :deviceKeys)", nativeQuery = true)
    void insertDeviceMapping(@Param("parentKey") Integer parentKey,@Param("deviceKeys") int[] deviceKeys);
    
    @Modifying(clearAutomatically=true)
    @Query("update Group g set g.treeName =:treeName where g.id =:id ") 
    void updateGroupName(@Param("id") Integer id,@Param("treeName") String treeName  );
    
    @Query("select g from Group g where g.id =:id")
    List<Group> getGroupName(@Param("id") Integer id);
    
    @Query("select g from Group g where g.parent =:id order by g.id asc ")
    List<Group> getGroupSecond(@Param("id")Integer id);
    
    @Query("select e,j from Equipment e, GroupEquipmentJoin j where e.id = j.equipment_id and j.group_id =:id "
            + "and e.deletedFlag = true and e.settingType in (:equipTypes) and e.settingCatagory in (:equipCatagorys) order by e.id asc ") 
    List<Equipment> searchFilterGroup(@Param("id") Integer id, @Param("equipTypes") String[] equipTypes, @Param("equipCatagorys") String[] equipCatagorys);

    @Modifying(clearAutomatically=true)
    @Query("delete from GroupEquipmentJoin j where j.equipment_id in (:id)")
    Integer deleteGroupEquipByNo(@Param("id") int[] id);


}
