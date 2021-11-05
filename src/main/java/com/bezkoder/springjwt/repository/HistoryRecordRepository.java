package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;

@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {

    @Query("select h from HistoryRecord h order by h.id desc")
    List<HistoryRecord> getHistoryRecord();
    
    @Query("select h from HistoryRecord h")
    List<HistoryRecord> getHistoryRecordExcel();
    
    @Query("select u from User u")
    List<User> getHistoryUser();
    
    @Query("select h from HistoryRecord h where h.userName in (?1) "
            + "and h.actionType in (?2) and h.workDate between ?3 and ?4 "
            + "order by h.id desc")
      List<HistoryRecord> getSelectHistory(String[] user, String[] action, LocalDateTime firstDates, LocalDateTime secondDates);

    @Query("select h from HistoryRecord h where h.userName in (?1) and h.actionType in (?2)")
      List<HistoryRecord> getTest(String[] user, String[] action);

}
