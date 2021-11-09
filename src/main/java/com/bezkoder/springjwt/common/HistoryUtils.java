package com.bezkoder.springjwt.common;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.catalina.core.ApplicationContext;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.repository.EquipmentRepository;
import com.bezkoder.springjwt.repository.GroupRepository;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;
import com.bezkoder.springjwt.service.EquipmentService;
import com.bezkoder.springjwt.service.GroupService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.asm.Advice.This;

@RequiredArgsConstructor
@RestController
public class HistoryUtils {
    
    private static HistoryRecordRepository historyRecordRepository;
    private static HttpServletRequest request;
    private static EquipmentRepository equipmentRepository;
    private static GroupRepository groupRepository;
    
    @Autowired
    public void set(HttpServletRequest request, HistoryRecordRepository historyRecordRepository,
            EquipmentRepository equipmentRepository,GroupRepository groupRepository) {
        HistoryUtils.request=request;
        HistoryUtils.historyRecordRepository=historyRecordRepository;
        HistoryUtils.equipmentRepository=equipmentRepository;
        HistoryUtils.groupRepository=groupRepository;
    }
    

    
   public static String getHostIp() {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            return ip;
        } catch (Exception e) {}
        return null;
    }
    
    public static void login(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(request.getContextPath());
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_LOGIN_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_LOGIN);
        historyRecord.setTargetName(name);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void historyExcel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_DOWNLOAD_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_HISTORY_RECORD);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_DOWNLOAD);
        historyRecord.setTargetName("감사이력리스트.xls");
        historyRecord.setSettingIp(HistoryUtils.getHostIp());
        historyRecord.setPageURL(Constants.STATUS_URL_HISTORY_RECORD);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }

    public static void equipmentCreateHistory(String targetName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setTargetName(targetName);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void equipmentUpdateHistory(String targetName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setTargetName(targetName);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void equipmentOnActiveHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_ACTIVE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(getHostIp());
            historyRecord.setPageURL(request.getHeader("referers"));
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void equipmentOffActiveHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_ACTIVE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(getHostIp());
            historyRecord.setPageURL(request.getHeader("referers"));
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void equipmentDeleteHistory(int[] id) {
        for(Integer ids : id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setUserName(auth.getName());
            historyRecord.setActionType(Constants.STATUS_DELETE_STRING);
            historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
            historyRecord.setTargetName(equipmentRepository.findNameInteger(ids));
            historyRecord.setSettingIp(getHostIp());
            historyRecord.setPageURL(request.getHeader("referers"));
            LocalDateTime date = LocalDateTime.now().withNano(0);
            historyRecord.setWorkDate(date);
            historyRecordRepository.save(historyRecord);
        }
    }
    
    public static void equipmentAllTooltipHistory(int[] id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_HARDWARECYCLE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_ALLAPPLY);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_SYSTEMCYCLE);
        historyRecord.setTargetName(equipmentRepository.findName(id));
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void equipmentEachTooltipHistory(int[] id, Integer hwCpu, Integer hwDisk, Integer hwNic, Integer hwSensor) {
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
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void equipmentExcelHistory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_DOWNLOAD_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_DOWNLOAD);
        historyRecord.setTargetName("Devices.xls");
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    
    public static void equipmentUploadEquipment() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_UPLOADCREATE);
        historyRecord.setTargetName("Equipment.xls");
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
   public static void deleteGroupHistory(int[] id, String name) {
       if(name.equals("parent")) {
           for(Integer ids : id) {
               System.out.println(ids);
               Authentication auth = SecurityContextHolder.getContext().getAuthentication();
               HistoryRecord historyRecord = new HistoryRecord();
               historyRecord.setUserName(auth.getName());
               historyRecord.setActionType(Constants.STATUS_DELETE_STRING);
               historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
               historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
               historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT);
               historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_ASSIGN);
               historyRecord.setTargetName(groupRepository.findNameGroupInteger(ids));
               System.out.println(groupRepository.findNameGroupInteger(ids));
               historyRecord.setSettingIp(getHostIp());
               historyRecord.setPageURL(request.getHeader("referers"));
               LocalDateTime date = LocalDateTime.now().withNano(0);
               historyRecord.setWorkDate(date);
               historyRecordRepository.save(historyRecord);
           }
       } else if(name.equals("children")) {
           for(Integer ids : id) {
               Authentication auth = SecurityContextHolder.getContext().getAuthentication();
               HistoryRecord historyRecord = new HistoryRecord();
               historyRecord.setUserName(auth.getName());
               historyRecord.setActionType(Constants.STATUS_DELETE_STRING);
               historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
               historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
               historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT);
               historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_ASSIGN);
               historyRecord.setTargetName(groupRepository.findNameEquipmentInteger(ids));
               historyRecord.setSettingIp(getHostIp());
               historyRecord.setPageURL(request.getHeader("referers"));
               LocalDateTime date = LocalDateTime.now().withNano(0);
               historyRecord.setWorkDate(date);
               historyRecordRepository.save(historyRecord);
               }
           }
       }
    public static void insertGroupHistory(String treeName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT_GROUP);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_NAME);
        historyRecord.setTargetName(treeName);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }



    public static void insertMappingGroupHistory(Integer equipment_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_ASSIGN);
        historyRecord.setTargetName(groupRepository.findNameEquipmentInteger(equipment_id));
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    public static void updateNameGroupHistory(String treeName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_UPDATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT_GROUP);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_NAME);
        historyRecord.setTargetName(treeName);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }
    public static void insertSecondNameGroupHistory(String treeName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_MANAGE);
        historyRecord.setMenuDepth3(Constants.STATUS_DEPTH_EQUIPMENT_GROUP);
        historyRecord.setMenuDepth4(Constants.STATUS_DEPTH_EQUIPMENT_GROUP_NAME);
        historyRecord.setTargetName(treeName);
        historyRecord.setSettingIp(getHostIp());
        historyRecord.setPageURL(request.getHeader("referers"));
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
    }


    
    

}












