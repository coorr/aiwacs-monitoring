package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.service.EquipmentService;
import com.bezkoder.springjwt.service.GroupService;
import com.bezkoder.springjwt.service.HistoryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    
    @GetMapping("/getHistoryRecord")
    public List<HistoryRecord> getHistoryRecord() {
        return historyService.getHistoryRecord();
    }
    
    @GetMapping("/getUserHistory")
    public List<User> getUserHistory() {
        return historyService.getUserHistory();
    }
    
    @GetMapping("/getSelectHistory/{user}/{action}")
    public List<HistoryRecord> getSelectHistory(@PathVariable("user")String[] user,@PathVariable("action")String[] action) {
        return historyService.getSelectHistory(user,action);
    }
    
}
