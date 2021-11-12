package com.bezkoder.springjwt.service;

import java.util.List;

import com.bezkoder.springjwt.models.StatSys;

public interface ReportStatService {

    public List<StatSys> getSysCpuDisk(Integer id,String startDate,String endDate);
}
