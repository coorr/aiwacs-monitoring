package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.HistoryRecord;

@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Integer> {

    @Query("select h from HistoryRecord h ")
    List<HistoryRecord> getHistoryRecord();
}
