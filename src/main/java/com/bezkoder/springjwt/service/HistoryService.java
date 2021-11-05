package com.bezkoder.springjwt.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.User;

public interface HistoryService {

    public List<HistoryRecord> getHistoryRecord();
    public List<User> getHistoryUser();
    public List<HistoryRecord> getSelectHistory(String[] user,String[] action,String firstDate,String secondDate);
    public ByteArrayInputStream  historyDownloadExcel(String user,String firstDate,String outDate);
    public List<HistoryRecord> getTest(String[] user, String[] action);
}
