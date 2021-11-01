package com.bezkoder.springjwt.servieImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.HistoryRecord;
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

}
