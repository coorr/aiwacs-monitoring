package com.bezkoder.springjwt.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.service.ReportStatService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ReportStatController {

    private final ReportStatService reportStatService; 
    
    @GetMapping("/getStat/{id}")
    public List<Object> getSysCpuMemory(@PathVariable("id") String id, @RequestParam(required = false) Integer cpu, 
                                        @RequestParam(required = false) Integer network, @RequestParam(required = false) Integer disk,
                                        @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        return reportStatService.getSysCpuMemory(id,cpu,network,disk,startDate,endDate);
    }
    
    @GetMapping("/getStatistics/{id}")
    public Map<String, Object> getStatistics(@PathVariable("id") String id, 
            @RequestParam(required = false) Integer sys, @RequestParam(required = false) Integer disk, @RequestParam(required = false) Integer nic, 
            @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws ParseException {
        
        return reportStatService.getStatistics(id, sys, disk, nic, startDate, endDate);
    }
    
    @PostMapping("/getReportDownloadPdf") 
    public ResponseEntity<InputStreamResource> getReportDownloadPdf(@RequestBody String chartData) throws IOException {
        ByteArrayInputStream  responseFile = reportStatService.getReportDownloadPdf(chartData);
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=filename.pdf")
                .body(new InputStreamResource(responseFile));
    }
}





















