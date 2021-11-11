package com.bezkoder.springjwt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.payload.request.EquipmentRequest;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
   
    @Query("select e from Equipment e where e.deletedFlag =true order by e.id desc")
    List<Equipment> findAlls();
    
    @Query("select e from Equipment e where e.deletedFlag =true and e.settingType != 'ICMP' order by e.id desc")
    List<Equipment> findAllsSnmp();
    
    @Query("select e.equipment from Equipment e where e.id in (:id)")
    String findName(@Param("id")int[] id);
    
    @Query("select e.equipment from Equipment e where e.id =:id")
    String findNameInteger(@Param("id")Integer id);
   
    @Query("select e from Equipment e where e.id =:id")
    Equipment findOne(Integer id);
    
    @Query("select e.settingIp from Equipment e where e.id =:id")
    String findSettingIpOne(Integer id);
   
    @Query("select e from Equipment e where e.id =:id")
    List<Equipment> getTooltipByNo(Integer id);

    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.deletedFlag=false where e.id in (:id) ")   
    Integer deleteOne(@Param("id") int[] id);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.settingActive =true where e.id in (:id) ") 
    Integer onActiveUpdate(@Param("id") int[] id);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.settingActive = false where  e.id in (:id) ")
    Integer offActiveUpdate(@Param("id") int[] id);
   
    @Query("select e from Equipment e where e.deletedFlag = true and e.settingType in (:equipTypes) and e.settingCatagory in (:equipCatagorys) order by e.id asc ") 
    List<Equipment> searchFilterEquipment(@Param("equipTypes") String[] equipTypes, @Param("equipCatagorys") String[] equipCatagorys);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.hwCpu = :hwCpu , e.hwDisk = :hwDisk , e.hwNic = :hwNic , e.hwSensor = :hwSensor where e.id in (:id) ")  // 개별,통합
    Integer tooltipHwUpdate(@Param("hwCpu") Integer hwCpu , @Param("hwDisk") Integer hwDisk,@Param("hwNic") Integer hwNic,@Param("hwSensor") Integer hwSensor,@Param("id") int[] id);

    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.hwCpu = :hwCpu where e.id in (:id) ")
    Integer cpuHwUpdate(@Param("hwCpu") Integer hwCpu, @Param("id") int[] id);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.hwDisk = :hwDisk where e.id in (:id) ")
    Integer diskHwUpdate(@Param("hwDisk") Integer hwDisk, @Param("id") int[] id);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.hwNic = :hwNic where e.id in (:id) ")
    Integer nicHwUpdate(@Param("hwNic") Integer hwNic, @Param("id") int[] id);
   
    @Modifying(clearAutomatically=true)
    @Query("update Equipment e set e.hwSensor = :hwSensor where e.id in (:id) ")
    Integer sensorHwUpdate(@Param("hwSensor") Integer hwSensor, @Param("id") int[] id);
    
    @Query("select count(e) > 0 from Equipment e where e.settingIp = :settingIp  AND e.deletedFlag = true ")
    boolean checkSettingIp(@Param("settingIp") String settingIp   );
    
    
    
}




















