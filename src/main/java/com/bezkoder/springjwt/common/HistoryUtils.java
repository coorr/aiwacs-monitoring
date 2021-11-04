package com.bezkoder.springjwt.common;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;

public class HistoryUtils {
    
    @Autowired
    private HistoryRecordRepository historyRecordRepository;
    
    public static void insertHistory() {
      
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        HistoryRecord historyRecord = new HistoryRecord();
//        historyRecord.setUserName(auth.getName());
//        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
//        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
//        historyRecord.setTargetName(equipmentRequest.getEquipment());
//        historyRecord.setSettingIp(request.getRemoteAddr());
//        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
//        LocalDateTime date = LocalDateTime.now().withNano(0);
//        historyRecord.setWorkDate(date);
//        historyRecordRepository.save(historyRecord);
        
    }
    

}
