package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatDiskTot;
import com.bezkoder.springjwt.models.StatNetwork;

@Repository
public interface StatDiskTotRepository  extends JpaRepository<StatDiskTot, Integer> {

    @Query("select s from StatDiskTot s where s.deviceId in (?1)  and s.generateTime between ?2 and ?3 order by s.id desc ")
    List<StatDiskTot> getSysCpuDiskTotal(int[] ids, LocalDateTime startDate, LocalDateTime endDate);

}
