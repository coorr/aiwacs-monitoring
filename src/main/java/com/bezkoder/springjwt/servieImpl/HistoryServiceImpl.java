package com.bezkoder.springjwt.servieImpl;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.common.Constants;
import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.GroupEquipmentJoinRepository;
import com.bezkoder.springjwt.repository.GroupRepository;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;
import com.bezkoder.springjwt.service.HistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryServiceImpl  implements HistoryService{
    
    private final HistoryRecordRepository historyRecordRepository;
    private final HttpServletRequest request;
    
    @Override
    public List<HistoryRecord> getHistoryRecord() {
        return historyRecordRepository.getHistoryRecord();
    }

    @Override
    public List<User> getUserHistory() {
        return historyRecordRepository.getUserHistory();
    }

    @Override
    public List<HistoryRecord> getSelectHistory(String[] user,String[] action,String firstDate,String secondDate) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       LocalDateTime firstDates = LocalDateTime.parse(firstDate, formatter);
       LocalDateTime secondDates = LocalDateTime.parse(secondDate, formatter);
        return historyRecordRepository.getSelectHistory(user,action,firstDates,secondDates);
    }


    @Transactional
    @Override
    public ByteArrayInputStream  historyDownloadExcel(String user,String firstDate,String outDate) {
        String version = "xls";
        int rowIndex = 8;
        
        Workbook workbook = createWorkbook(version);
        Sheet sheet = workbook.createSheet("감사이력리스트");
        Cell cell =null;
        Row row=null;
        
        Font headFont = workbook.createFont();
        headFont.setBold(true);
        
        Font headFontSecond = workbook.createFont();
        headFontSecond.setFontHeightInPoints((short) 15);
        
        CellStyle titleStyle  = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFont(headFontSecond);
        
        CellStyle titleStyleSecond  = workbook.createCellStyle();
        titleStyleSecond.setAlignment(HorizontalAlignment.RIGHT);
        titleStyleSecond.setFont(headFont);
        
        CellStyle titleStyleThird  = workbook.createCellStyle();
        titleStyleThird.setAlignment(HorizontalAlignment.LEFT);
        titleStyleThird.setFont(headFont);
        
        CellStyle headStyle  = workbook.createCellStyle();
        headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setFont(headFont);
        
        CellStyle bodyStyle  = workbook.createCellStyle();
        bodyStyle.setAlignment(HorizontalAlignment.LEFT);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
            
        CellStyle bodyStyleCenter  = workbook.createCellStyle();
        bodyStyleCenter.setAlignment(HorizontalAlignment.CENTER);
        bodyStyleCenter.setBorderTop(BorderStyle.THIN);
        bodyStyleCenter.setBorderBottom(BorderStyle.THIN);
        bodyStyleCenter.setBorderLeft(BorderStyle.THIN);
        bodyStyleCenter.setBorderRight(BorderStyle.THIN);
        
        row=sheet.createRow(0); Cell headerCell1 = row.createCell(2); headerCell1.setCellValue("감사 이력 리스트"); headerCell1.setCellStyle(titleStyle);
        row=sheet.createRow(3); Cell headerCell3 = row.createCell(0); headerCell3.setCellValue("출력 일시 :"); headerCell3.setCellStyle(titleStyleSecond);
        Cell headerCell31 = row.createCell(1); headerCell31.setCellValue(firstDate); headerCell31.setCellStyle(titleStyleThird);
        row=sheet.createRow(4); Cell headerCell4 = row.createCell(0); headerCell4.setCellValue("사용자 :"); headerCell4.setCellStyle(titleStyleSecond);
        Cell headerCell41 = row.createCell(1); headerCell41.setCellValue(user); headerCell41.setCellStyle(titleStyleThird);
        row=sheet.createRow(5); Cell headerCell5 = row.createCell(0); headerCell5.setCellValue("기간 :"); headerCell5.setCellStyle(titleStyleSecond);
        Cell headerCell51 = row.createCell(1); headerCell51.setCellValue(outDate); headerCell51.setCellStyle(titleStyleThird);
        
        String[] headerKey= {"번호","사용자","작업 구분","메뉴 1","메뉴 2","메뉴 3","메뉴 4","작업 대상","사용자 IP","작업 URL","작업 일자"};
        row=sheet.createRow(7);
        for(int i=0; i<headerKey.length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
            cell.setCellStyle(headStyle);
        }
        
        List<HistoryRecord> historyRecords = historyRecordRepository.getHistoryRecordExcel();
        
        for(HistoryRecord dto : historyRecords) {
            Row bodyRow = sheet.createRow(rowIndex++);
            LocalDateTime date = dto.getWorkDate();
            String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Cell bodyCell0 = bodyRow.createCell(0); bodyCell0.setCellValue(dto.getId());  bodyCell0.setCellStyle(bodyStyleCenter);
            Cell bodyCell1 = bodyRow.createCell(1);  bodyCell1.setCellValue(dto.getUserName());    bodyCell1.setCellStyle(bodyStyle);
            Cell bodyCell2 = bodyRow.createCell(2); bodyCell2.setCellValue(dto.getActionType()); bodyCell2.setCellStyle(bodyStyle);
            Cell bodyCell3 = bodyRow.createCell(3); bodyCell3.setCellValue(dto.getMenuDepth1()); bodyCell3.setCellStyle(bodyStyle);
            Cell bodyCell4 = bodyRow.createCell(4); bodyCell4.setCellValue(dto.getMenuDepth2()); bodyCell4.setCellStyle(bodyStyle);
            Cell bodyCell5 = bodyRow.createCell(5); bodyCell5.setCellValue(dto.getMenuDepth3()); bodyCell5.setCellStyle(bodyStyle);
            Cell bodyCell6 = bodyRow.createCell(6); bodyCell6.setCellValue(dto.getMenuDepth4()); bodyCell6.setCellStyle(bodyStyle);
            Cell bodyCell7 = bodyRow.createCell(7); bodyCell7.setCellValue(dto.getTargetName());    bodyCell7.setCellStyle(bodyStyle);
            Cell bodyCell8 = bodyRow.createCell(8); bodyCell8.setCellValue(dto.getSettingIp()); bodyCell8.setCellStyle(bodyStyle);
            Cell bodyCell9 = bodyRow.createCell(9); bodyCell9.setCellValue(dto.getPageURL()); bodyCell9.setCellStyle(bodyStyle);
            Cell bodyCell10 = bodyRow.createCell(10); bodyCell10.setCellValue(formatDate);  bodyCell10.setCellStyle(bodyStyle); 
            
        }
        
        for(int i = 2; 11 >= i; i++) {
            sheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
          workbook.write(out);
          workbook.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setUserName(auth.getName());
        historyRecord.setActionType(Constants.STATUS_DOWNLOAD_STRING);
        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_HISTORY_RECORD);
        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_DOWNLOAD);
        historyRecord.setTargetName("감사이력리스트.xls");
        historyRecord.setSettingIp(request.getRemoteAddr());
        historyRecord.setPageURL(Constants.STATUS_URL_HISTORY_RECORD);
        LocalDateTime date = LocalDateTime.now().withNano(0);
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
        return new ByteArrayInputStream(out.toByteArray()); 
         
    }
    
    private Workbook createWorkbook(String version) {
        if("xls".equals(version)) {
            return new HSSFWorkbook();
        } else if("xlsx".equals(version)) {
            return new HSSFWorkbook();
        }
        throw new NoClassDefFoundError();
    }
}

















