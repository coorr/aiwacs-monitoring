package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatSys;

@Repository
public interface StatSysRepository  extends JpaRepository<StatSys, Integer> {

    @Query("select s from StatSys s where s.deviceId = ?1  and s.generateTime between ?2 and ?3 order by s.id desc ")
    List<StatSys> getSysCpuDisk(Integer id, LocalDateTime startDate, LocalDateTime endDate);

}
