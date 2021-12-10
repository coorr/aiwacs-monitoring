package com.bezkoder.springjwt.service;

import java.util.List;
import java.util.Map;

import com.bezkoder.springjwt.models.StatSys;

public interface ReportStatService {

    public List<Object> getSysCpuMemory(String id,Integer cpu,Integer network, Integer disk, String startDate,String endDate);
    public Map<String, Object> getStatistics(String id, Integer sys, Integer disk, Integer nic, String startDate, String endDate);
}
