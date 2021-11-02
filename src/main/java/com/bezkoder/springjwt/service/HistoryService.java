package com.bezkoder.springjwt.service;

import java.util.List;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.User;

public interface HistoryService {

    public List<HistoryRecord> getHistoryRecord();
    public List<User> getUserHistory();
}
