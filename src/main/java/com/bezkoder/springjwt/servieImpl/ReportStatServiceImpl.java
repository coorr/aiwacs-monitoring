package com.bezkoder.springjwt.servieImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.StatDisk;
import com.bezkoder.springjwt.models.StatDiskTot;
import com.bezkoder.springjwt.models.StatNetwork;
import com.bezkoder.springjwt.models.StatSys;
import com.bezkoder.springjwt.repository.StatDiskRepository;
import com.bezkoder.springjwt.repository.StatDiskTotRepository;
import com.bezkoder.springjwt.repository.StatNetworkRepository;
import com.bezkoder.springjwt.repository.StatSysRepository;
import com.bezkoder.springjwt.service.ReportStatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportStatServiceImpl implements ReportStatService {
    
    private final StatSysRepository statSysRepository;
    private final StatDiskRepository statDiskRepository;
    private final StatDiskTotRepository statDiskTotRepository;
    private final StatNetworkRepository statNetworkRepository;
    
    @Override
    public List<Object> getSysCpuMemory(String id,Integer cpu,Integer network,Integer disk, String startDate,String endDate) {
        System.out.println("aa" + id);
        String[] arrayId = id.split("\\|");
        
        int[] ids=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
        System.out.println(ids);
        
        DateTimeFormatter aformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),aformatter);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),aformatter);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime firstDates = LocalDateTime.parse(stringStartDate,formatter);
        LocalDateTime secondDates = LocalDateTime.parse(stringEndDate, formatter);
        
        List<Object> resultList = new ArrayList<Object>();
        
        if(cpu == 1) {
            List<StatSys> statSys = statSysRepository.getSysCpuMemory(ids, firstDates, secondDates);
            resultList.add(statSys); 
        } 
        if(network == 1) {
            List<StatNetwork> statNetworks = statNetworkRepository.getSysNetwork(ids, firstDates, secondDates);
            resultList.add(statNetworks); 
        }
        if(disk == 1) {
            List<StatDisk> statDisks = statDiskRepository.getSysCpuDisk(ids, firstDates, secondDates);   
            resultList.add(statDisks); 
        }
        if(disk == 1) {
            List<StatDiskTot> statDiskTotks = statDiskTotRepository.getSysCpuDiskTotal(ids, startDateTimeChange, endDateTimeChange);
            resultList.add(statDiskTotks);
        }
        
         
         return resultList;
    }
    
    @Override
    public Map<String, Object> getStatistics(String id, Integer sys, Integer disk, Integer nic, String startDate, String endDate) {
        String[] arrayId = id.split("\\|");
        
        int[] ids=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
        
        DateTimeFormatter aformatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime startDateTimeChange= LocalDateTime.parse(startDate.substring(0,14),aformatter);
        LocalDateTime endDateTimeChange= LocalDateTime.parse(endDate.substring(0,14),aformatter);
        String stringStartDate = startDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String stringEndDate = endDateTimeChange.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime firstDates = LocalDateTime.parse(stringStartDate,formatter);
        LocalDateTime secondDates = LocalDateTime.parse(stringEndDate, formatter);
        
        Map<String, Object> resultList = new HashMap<String, Object>();
        
        if(sys == 1) {
            List<StatSys> statSys = statSysRepository.getSysCpuMemory(ids, firstDates, secondDates);
            resultList.put("sys", statSys);
        } 
        
        if(disk == 1) {
            List<StatDisk> statDisks = statDiskRepository.getSysCpuDisk(ids, firstDates, secondDates);   
            List<StatDiskTot> statDiskTotks = statDiskTotRepository.getSysCpuDiskTotal(ids, startDateTimeChange, endDateTimeChange);
            resultList.put("disk", statDisks);
            resultList.put("diskTot", statDiskTotks);
        }
        
        if(nic == 1) {
            List<StatNetwork> statNetworks = statNetworkRepository.getSysNetwork(ids, firstDates, secondDates);
            resultList.put("nic", statNetworks);
        }
         
         return resultList;
    }
}















