package com.aiwacs.spring.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

public interface ReportStatService {

    public List<Object> getSysCpuMemory(String id,Integer cpu,Integer network, Integer disk, String startDate,String endDate);
    public Map<String, Object> getStatistics(String id, Integer sys, Integer disk, Integer nic, String startDate, String endDate);
    public ByteArrayInputStream getReportDownloadPdf(String chartData) throws IOException, ParseException;
}
