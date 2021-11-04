package com.bezkoder.springjwt.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/getSelectHistory/{user}/{action}/{firstDate}/{secondDate}")
    public List<HistoryRecord> getSelectHistory(@PathVariable("user")String[] user,@PathVariable("action")String[] action,
                                     @PathVariable("firstDate")String firstDate, @PathVariable("secondDate")String secondDate) {
       return historyService.getSelectHistory(user,action,firstDate,secondDate);  
    }
    
    @GetMapping("/history/historyDownloadExcel/{user}/{firstDate}/{outDate}")
    public ResponseEntity<InputStreamResource> downloadExcel(@PathVariable("user")String user,@PathVariable("firstDate")String firstDate,@PathVariable("outDate")String outDate) {
        ByteArrayInputStream  responseFile = historyService.historyDownloadExcel(user,firstDate,outDate);
         return ResponseEntity
                 .ok()
                 .header("Content-Disposition", "attachment; filename=filename.xls")
                 .body(new InputStreamResource(responseFile));
    }

    
}











