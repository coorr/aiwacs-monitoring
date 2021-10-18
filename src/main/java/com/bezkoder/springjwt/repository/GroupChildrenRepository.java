//package com.bezkoder.springjwt.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.bezkoder.springjwt.models.Equipment;
//import com.bezkoder.springjwt.models.Group;
//import com.bezkoder.springjwt.models.GroupChildren;
//
//@Repository
//public interface GroupChildrenRepository extends JpaRepository<GroupChildren, Integer> {
//
//    @Modifying(clearAutomatically=true)
//    @Query(value="insert into group_children(parent, tree_name) values (:parentKey, :treeName)", nativeQuery = true)
//    void insertGroupSecond(@Param("parentKey") Integer parentKey,@Param("treeName") String treeName);
//    
//    @Query("select c from GroupChildren c,Group g where g.id = c.parent and g.id =:id")
//    List<GroupChildren> getGroupChildren(@Param("id") Integer id);
//    
//    @Query("select e from Equipment e, GroupChildren c where e.id = c.equipment and c.id =:id")
//    List<Equipment> getGroupChildrenEquipment(@Param("id") Integer id);
//
//    
//}
