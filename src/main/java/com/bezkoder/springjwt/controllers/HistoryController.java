package com.bezkoder.springjwt.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.User;
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
    
    @GetMapping("/getHistoryUser")
    public List<User> getHistoryUser() {
        return historyService.getHistoryUser();
    }
    
    @GetMapping("/getSelectHistory")
    public List<HistoryRecord> getSelectHistory(@RequestParam(required = false) int size,     @RequestParam(required = false) String user, 
                                                @RequestParam(required = false) String action,@RequestParam(required = false) String firstDate,
                                                @RequestParam(required = false) String secondDate) {
        return historyService.getSelectHistory(size,user,action,firstDate,secondDate);  
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











