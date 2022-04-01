package com.aiwacs.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aiwacs.spring.models.StatNetwork;

@Repository
public interface StatNetworkRepository  extends JpaRepository<StatNetwork, Integer> {

    @Query("select s from StatNetwork s where s.deviceId in (?1)  and s.generateTime between ?2 and ?3 order by s.id desc ")
    List<StatNetwork> getSysNetwork(int[] ids, LocalDateTime startDate, LocalDateTime endDate);

}
