package com.bezkoder.springjwt.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.User;

public interface HistoryService {

    public List<HistoryRecord> getHistoryRecord();
    public List<User> getUserHistory();
    public List<HistoryRecord> getSelectHistory(String[] user,String[] action);
    public ByteArrayInputStream  downloadExcel();
}
