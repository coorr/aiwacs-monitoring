package com.bezkoder.springjwt.servieImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.GroupEquipmentJoinRepository;
import com.bezkoder.springjwt.repository.GroupRepository;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;
import com.bezkoder.springjwt.service.HistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryServiceImpl  implements HistoryService{
    
    private final HistoryRecordRepository historyRecordRepository;
    
    @Override
    public List<HistoryRecord> getHistoryRecord() {
        return historyRecordRepository.getHistoryRecord();
    }

    @Override
    public List<User> getUserHistory() {
        return historyRecordRepository.getUserHistory();
    }

}

















