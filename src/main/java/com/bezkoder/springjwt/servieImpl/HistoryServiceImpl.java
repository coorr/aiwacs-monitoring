package com.bezkoder.springjwt.servieImpl;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Override
    public List<HistoryRecord> getHistoryRecord() {
        return historyRecordRepository.getHistoryRecord();
    }

    @Override
    public List<User> getUserHistory() {
        return historyRecordRepository.getUserHistory();
    }

    @Override
    public List<HistoryRecord> getSelectHistory(String[] user,String[] action) {
        return historyRecordRepository.getSelectHistory(user,action);
    }

    @Transactional
    @Override
    public ByteArrayInputStream  downloadExcel() {
        String version = "xls";
        int rowIndex = 1;
        
        Workbook workbook = createWorkbook(version);
        Sheet sheet = workbook.createSheet("감사이력리스트");
        Cell cell =null;
        Row row=null;
        
        Font headFont = workbook.createFont();
        headFont.setBold(true);
        
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
        
        CellStyle bodyStyleRight  = workbook.createCellStyle();
        bodyStyleRight.setAlignment(HorizontalAlignment.RIGHT);
        bodyStyleRight.setBorderTop(BorderStyle.THIN);
        bodyStyleRight.setBorderBottom(BorderStyle.THIN);
        bodyStyleRight.setBorderLeft(BorderStyle.THIN);
        bodyStyleRight.setBorderRight(BorderStyle.THIN);
        
        String[] headerKey= {"번호","사용자","작업 구분","메뉴 1","메뉴 2","메뉴 3","메뉴 4","작업 대상","사용자 IP","작업 URL","작업 일자"};
        row=sheet.createRow(0);
        for(int i=0; i<headerKey.length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
            cell.setCellStyle(headStyle);
        }
        
//        List<Equipment> equipments = equipmentRepository.findAlls();
//        
//        for(Equipment dto : equipments) {
//            System.out.println(equipments);
//            Row bodyRow = sheet.createRow(rowIndex++);
//            
//            Cell bodyCell0 = bodyRow.createCell(0); bodyCell0.setCellValue(dto.getId());  bodyCell0.setCellStyle(bodyStyleCenter);
//            Cell bodyCell1 = bodyRow.createCell(1);     bodyCell1.setCellStyle(bodyStyle);
//            Cell bodyCell2 = bodyRow.createCell(2); bodyCell2.setCellValue(dto.getNickname()); bodyCell2.setCellStyle(bodyStyle);
//            Cell bodyCell3 = bodyRow.createCell(3); bodyCell3.setCellValue(dto.getEquipment()); bodyCell3.setCellStyle(bodyStyle);
//            Cell bodyCell4 = bodyRow.createCell(4); bodyCell4.setCellValue(dto.getSettingIp()); bodyCell4.setCellStyle(bodyStyle);
//            Cell bodyCell5 = bodyRow.createCell(5); bodyCell5.setCellValue(dto.getSettingType()); bodyCell5.setCellStyle(bodyStyle);
//            Cell bodyCell6 = bodyRow.createCell(6); bodyCell6.setCellValue(dto.getSettingCatagory());bodyCell6.setCellStyle(bodyStyle);
//            Cell bodyCell7 = bodyRow.createCell(7);     bodyCell7.setCellStyle(bodyStyle);
//            Cell bodyCell8 = bodyRow.createCell(8); bodyCell8.setCellValue(dto.getSettingOs()); bodyCell8.setCellStyle(bodyStyle);
//            Cell bodyCell9 = bodyRow.createCell(9); bodyCell9.setCellValue(dto.getSettingPerson()); bodyCell9.setCellStyle(bodyStyle);
//            Cell bodyCell10 = bodyRow.createCell(10);   bodyCell10.setCellStyle(bodyStyle);
//            Cell bodyCell11 = bodyRow.createCell(11); bodyCell11.setCellValue(dto.isSettingActive() ? "활성"  : "비활성" ); bodyCell11.setCellStyle(bodyStyleCenter);
//            Cell bodyCell12 = bodyRow.createCell(12); bodyCell12.setCellValue(dto.getSettingProxy()); bodyCell12.setCellStyle(bodyStyle);
//            Cell bodyCell13 = bodyRow.createCell(13); bodyCell13.setCellValue(dto.getSettingTemplate()); bodyCell13.setCellStyle(bodyStyle);
//            Cell bodyCell14 = bodyRow.createCell(14); 
//                if(dto.getHwCpu() != null) { bodyCell14.setCellValue(dto.getHwCpu());} else { bodyCell14.setCellValue(""); } bodyCell14.setCellStyle(bodyStyleRight);
//                
//            Cell bodyCell15 = bodyRow.createCell(15); 
//                if(dto.getHwCpu() != null) { bodyCell15.setCellValue(dto.getHwCpu());} else { bodyCell15.setCellValue(""); } bodyCell15.setCellStyle(bodyStyleRight);
//                
//            Cell bodyCell16 = bodyRow.createCell(16);  
//                if(dto.getHwDisk() != null) { bodyCell16.setCellValue(dto.getHwDisk());} else { bodyCell16.setCellValue(""); } bodyCell16.setCellStyle(bodyStyleRight);
//                
//            Cell bodyCell17 = bodyRow.createCell(17); 
//                if(dto.getHwNic() != null) { bodyCell17.setCellValue(dto.getHwNic());} else { bodyCell17.setCellValue(""); } bodyCell17.setCellStyle(bodyStyleRight);
//                
//            Cell bodyCell18 = bodyRow.createCell(18);  
//                if(dto.getHwSensor() != null) { bodyCell18.setCellValue(dto.getHwSensor());} else { bodyCell18.setCellValue(""); } bodyCell18.setCellStyle(bodyStyleRight); 
//        }
//        
//        for(int i = 0; 18 >= i; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        
//        try {
//          workbook.write(out);
//          workbook.close();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        HistoryRecord historyRecord = new HistoryRecord();
//        historyRecord.setUserName(auth.getName());
//        historyRecord.setActionType(Constants.STATUS_DOWNLOAD_STRING);
//        historyRecord.setMenuDepth1(Constants.STATUS_DEPTH_EQUIPMENT_MANAGE);
//        historyRecord.setMenuDepth2(Constants.STATUS_DEPTH_DOWNLOAD);
//        historyRecord.setTargetName("Devices.xls");
//        historyRecord.setSettingIp(request.getRemoteAddr());
//        historyRecord.setPageURL(Constants.STATUS_URL_EQUIPMENT_MANAGE);
//        LocalDateTime date = LocalDateTime.now();
//        historyRecord.setWorkDate(date);
//        historyRecordRepository.save(historyRecord);
//        return new ByteArrayInputStream(out.toByteArray()); 
         return null;
    }
    
    private Workbook createWorkbook(String version) {
        if("xls".equals(version)) {
            System.out.println("xls");
            return new HSSFWorkbook();
        } else if("xlsx".equals(version)) {
            System.out.println("XlSX");
            return new HSSFWorkbook();
        }
        throw new NoClassDefFoundError();
    }
}

















