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
    public List<StatSys> getSysCpuDisk(@RequestParam(required = false) Integer id, @RequestParam(required = false) String startDate, 
                                        @RequestParam(required = false) String endDate) {
        return reportStatService.getSysCpuDisk(id,startDate,endDate);
    }
}
