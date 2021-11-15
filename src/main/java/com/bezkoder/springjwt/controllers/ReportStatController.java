package com.bezkoder.springjwt.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.StatSys;
import com.bezkoder.springjwt.service.ReportStatService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ReportStatController {

    private final ReportStatService reportStatService; 
    
    @GetMapping("/getStat")
    public List<Object> getSysCpuMemory(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer cpu, 
                                        @RequestParam(required = false) Integer network, @RequestParam(required = false) Integer disk,
                                        @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        return reportStatService.getSysCpuMemory(id,cpu,network,disk,startDate,endDate);
    }
}
