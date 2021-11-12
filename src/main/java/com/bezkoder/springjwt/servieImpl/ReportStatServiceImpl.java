package com.bezkoder.springjwt.servieImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.StatSys;
import com.bezkoder.springjwt.repository.StatSysRepository;
import com.bezkoder.springjwt.service.ReportStatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportStatServiceImpl implements ReportStatService {
    
    private final StatSysRepository statSysRepository;
    
    @Override
    public List<StatSys> getSysCpuDisk(Integer id,String startDate,String endDate) {
        
        DateTimeFormatter aformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),aformatter);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),aformatter);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime firstDates = LocalDateTime.parse(stringStartDate,formatter);
        LocalDateTime secondDates = LocalDateTime.parse(stringEndDate, formatter);
        return statSysRepository.getSysCpuDisk(id, firstDates, secondDates);
    }

}
