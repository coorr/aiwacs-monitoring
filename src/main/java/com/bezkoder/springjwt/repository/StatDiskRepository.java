package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatDisk;

@Repository
public interface StatDiskRepository  extends JpaRepository<StatDisk, Integer> {

    @Query("select s from StatDisk s where s.deviceId = ?1  and s.generateTime between ?2 and ?3 order by s.id desc ")
    List<StatDisk> getSysCpuDisk(Integer id, LocalDateTime startDate, LocalDateTime endDate);

}
