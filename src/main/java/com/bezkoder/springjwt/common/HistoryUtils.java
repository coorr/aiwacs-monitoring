package com.bezkoder.springjwt.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.repository.EquipmentRepository;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;
import com.bezkoder.springjwt.service.EquipmentService;
import com.bezkoder.springjwt.service.GroupService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.This;

@RequiredArgsConstructor
@RestController
public class HistoryUtils {
    
    private static HistoryRecordRepository historyRecordRepository;
    private static HttpServletRequest request;
    private static EquipmentRepository equipmentRepository;
    
    @Autowired
    public void set(HttpServletRequest request, HistoryRecordRepository historyRecordRepository,EquipmentRepository equipmentRepository) {
        HistoryUtils.request=request;
        HistoryUtils.historyRecordRepository=historyRecordRepository;
        HistoryUtils.equipmentRepository=equipmentRepository;
    }
    
    private static String getHostIp() throws UnknownHostException {
        String ipString = "";
        
        InetAddress address = null;
        
        address = InetAddress.getLocalHost();
        
        return ipString;
    }
    
    public static void login(String name) throws UnknownHostException {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(request.getContextPath());
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_LOGIN_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_LOGIN);
        historyRecord.setTargetName(name);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getServletPath());
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }

    public static void insertHistory(String targetName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setTargetName(targetName);
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void updateHistory(String targetName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setTargetName(targetName);
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void onActiveHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_ACTIVE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(request.getRemoteAddr());
            historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void offActiveHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_ACTIVE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(request.getRemoteAddr());
            historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void deleteHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_DELETE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(request.getRemoteAddr());
            historyRecord.setPageURL(Constants.STATUS_URL_MANAGE_EQUIPMENT_LIST);
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void allTooltipHistory(int[] id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_HARDWARECYCLE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_ALLAPPLY);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_SYSTEMCYCLE);
        historyRecord.setTargetName(equipmentRepository.findName(id));
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void eachTooltipHistory(int[] id, Integer hwCpu, Integer hwDisk, Integer hwNic, Integer hwSensor) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_HARDWARECYCLE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EACHAPPLY);
        if(hwCpu != null) {
            historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_CPU);
         } else if (hwDisk != null ) {
            historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_DISK);
         } else if (hwNic != null ) {
            historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_NICK);
         } else if (hwSensor != null ) {
            historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_SENSOR);
         }
        historyRecord.setTargetName(equipmentRepository.findName(id));
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void excelHistory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_DOWNLOAD_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_DOWNLOAD);
        historyRecord.setTargetName("Devices.xls");
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void uploadEquipment() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_UPLOADCREATE);
        historyRecord.setTargetName("Equipment.xls");
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
   
    

}












