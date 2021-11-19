package com.bezkoder.springjwt.service;

import java.util.List;

import com.bezkoder.springjwt.models.StatSys;

public interface ReportStatService {

    public List<Object> getSysCpuMemory(String id,Integer cpu,Integer network, Integer disk, String startDate,String endDate);
}
