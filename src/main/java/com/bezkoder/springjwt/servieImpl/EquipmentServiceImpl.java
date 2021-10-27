package com.bezkoder.springjwt.servieImpl;



import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.tomcat.util.bcel.Const;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.common.Constants;
import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.payload.request.EquipmentRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.EquipmentRepository;
import com.bezkoder.springjwt.repository.HistoryRecordRepository;
import com.bezkoder.springjwt.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService{

	private final EquipmentRepository equipmentRepository;
	private final HistoryRecordRepository historyRecordRepository;

	@Transactional
    @Override
    public ResponseEntity<?> createEquipment(EquipmentRequest equipmentRequest) {
	    if (equipmentRepository.checkSettingIp(equipmentRequest.getSettingIp())) {
            System.out.println("settingIp");
            return ResponseEntity
                    .badRequest()  
                    .body(new MessageResponse("setting IP Check faild"));
        }
        Equipment equipment = Equipment.creatEquipment(
                equipmentRequest.getEquipment(), 
                equipmentRequest.getNickname(), 
                equipmentRequest.getSettingType(), 
                equipmentRequest.getSettingTemplate(), 
                equipmentRequest.getSettingIp(), 
                equipmentRequest.getSettingCatagory(),
                equipmentRequest.getSettingOs(),
                equipmentRequest.getSettingPerson(), 
                equipmentRequest.getSettingProxy(),
                equipmentRequest.isSettingActive(),
                equipmentRequest.getHwCpu(), 
                equipmentRequest.getHwDisk(), 
                equipmentRequest.getHwNic(), 
                equipmentRequest.getHwSensor(),
                equipmentRequest.isDeletedFlag()
                );
        equipmentRepository.save(equipment);
        
        HistoryRecord historyRecord = new HistoryRecord();
//        historyRecord.setUserName(historyRecord.getUserName());
        historyRecord.setActionType(Constants.STATUS_CREATE_STRING);
//        historyRecord.setMenuDepth1(historyRecord.getMenuDepth1());
//        historyRecord.setSettingIp(historyRecord.getSettingIp());
        historyRecord.setPageURL(Constants.STATUS_URL_GROUPDEVICEMANAGE);
        historyRecord.setTargetName(equipmentRequest.getEquipment());
        LocalDateTime date = LocalDateTime.now();
        historyRecord.setWorkDate(date);
        historyRecordRepository.save(historyRecord);
        return null;
    }
	
	@Transactional 
	@Override
	public ResponseEntity<?> updateEquipmentByNo(Integer equipId,EquipmentRequest equipmentRequest) {
	    String settingIp = equipmentRequest.getSettingIp();
	    String beforeSettingIp = equipmentRepository.findSettingIpOne(equipId);
	    if(settingIp.equals(beforeSettingIp)) {
	        Equipment equipment = equipmentRepository.findOne(equipId);
	        
	        equipment.setId(equipId);
	        equipment.setEquipment(equipmentRequest.getEquipment());
	        equipment.setNickname(equipmentRequest.getNickname());
	        equipment.setSettingType(equipmentRequest.getSettingType());
	        equipment.setSettingTemplate(equipmentRequest.getSettingTemplate());
	        equipment.setSettingIp(equipmentRequest.getSettingIp());
	        equipment.setSettingCatagory(equipmentRequest.getSettingCatagory());
	        equipment.setSettingOs(equipmentRequest.getSettingOs());
	        equipment.setSettingPerson(equipmentRequest.getSettingPerson());
	        equipment.setSettingProxy(equipmentRequest.getSettingProxy());
	        equipment.setSettingActive(equipmentRequest.isSettingActive());
	        equipment.setHwCpu(equipmentRequest.getHwCpu());
	        equipment.setHwDisk(equipmentRequest.getHwDisk());
	        equipment.setHwNic(equipmentRequest.getHwNic());
	        equipment.setHwSensor(equipmentRequest.getHwSensor());
	        
	        return null;
	    } else if (equipmentRepository.checkSettingIp(equipmentRequest.getSettingIp())) {
            System.out.println("settingIp");
            return ResponseEntity
                    .badRequest()  
                    .body(new MessageResponse("setting IP Check faild"));
        }
	    
		Equipment equipment = equipmentRepository.findOne(equipId);
		
		equipment.setId(equipId);
		equipment.setEquipment(equipmentRequest.getEquipment());
		equipment.setNickname(equipmentRequest.getNickname());
		equipment.setSettingType(equipmentRequest.getSettingType());
		equipment.setSettingTemplate(equipmentRequest.getSettingTemplate());
		equipment.setSettingIp(equipmentRequest.getSettingIp());
		equipment.setSettingCatagory(equipmentRequest.getSettingCatagory());
		equipment.setSettingOs(equipmentRequest.getSettingOs());
		equipment.setSettingPerson(equipmentRequest.getSettingPerson());
		equipment.setSettingProxy(equipmentRequest.getSettingProxy());
		equipment.setSettingActive(equipmentRequest.isSettingActive());
		equipment.setHwCpu(equipmentRequest.getHwCpu());
		equipment.setHwDisk(equipmentRequest.getHwDisk());
		equipment.setHwNic(equipmentRequest.getHwNic());
		equipment.setHwSensor(equipmentRequest.getHwSensor());
		
		return null;
	}

	@Override
	public List<Equipment> getEquipments() {
		return equipmentRepository.findAlls();
	}

	@Transactional 
	@Override
	public void onActiveEquipment(String equipId) {
		String[] arrayId = equipId.split("\\|");
	 	int[] id=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
	 	equipmentRepository.onActiveUpdate(id);
	}

	@Transactional 
	@Override
	public void offActiveEquipment(String equipId) {
		String[] arrayId = equipId.split("\\|");
	 	int[] id=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
	 	equipmentRepository.offActiveUpdate(id);
		
	}

	@Transactional 
	@Override
	public void deleteEquipment(String equipId) {
		String[] arrayId = equipId.split("\\|");
		int[] id=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();	
		equipmentRepository.deleteOne(id);
		
	}

	@Override
    public List<Equipment> searchFilterEquipment(String equipType, String equipCatagory) {
		String[] equipTypes = equipType.split(",");
		String[] equipCatagorys = equipCatagory.split(",");
		return equipmentRepository.searchFilterEquipment(equipTypes, equipCatagorys);
	   }

	@Transactional 
    @Override
    public void allTooltipHwUpdateEquipment(Integer hwCpu,Integer hwDisk,Integer hwNic, Integer hwSensor,String hwid) {
	    String[] hwids= hwid.split(",");
	    int[] id=Arrays.stream(hwids).mapToInt(Integer::parseInt).toArray();
	    equipmentRepository.tooltipHwUpdate(hwCpu, hwDisk,hwNic,hwSensor, id);
    }
	
    @Transactional 
    @Override
    public void eachTooltipHwUpdateEquipment(Integer hwCpu, Integer hwDisk, Integer hwNic, Integer hwSensor,String hwid) {
       String[] hwids= hwid.split(",");
       int[] id=Arrays.stream(hwids).mapToInt(Integer::parseInt).toArray();
      if(hwCpu != null) {
          equipmentRepository.cpuHwUpdate(hwCpu, id);
       } else if (hwDisk != null ) {
          equipmentRepository.diskHwUpdate(hwDisk, id);
       } else if (hwNic != null ) {
          equipmentRepository.nicHwUpdate(hwNic, id);
       } else if (hwSensor != null ) {
          equipmentRepository.sensorHwUpdate(hwSensor, id);
       }
      
   }
    
   @Override
    public List<Equipment> getTooltipByNo(Integer hwid) {
        return equipmentRepository.getTooltipByNo(hwid);
    }

    @Override
    public ByteArrayInputStream  downloadExcel() {

        String version = "xls";
        int rowIndex = 1;
        
        Workbook workbook = createWorkbook(version);
        Sheet sheet = workbook.createSheet("Equipment");
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
        
        String[] headerKey= {"번호","장비 그룹","별칭","장비 명","IP","타입","유형","MAC","OS",
                                    "제조사","Port","활성 여부","프록시","템플릿 그룹","시스템 주기(초)","CPU/MEM 주기(초)","DISK 주기(초)","NIC 주기(초)","센서 주기(초)"};
        row=sheet.createRow(0);
        for(int i=0; i<headerKey.length;i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
            cell.setCellStyle(headStyle);
        }
        
        List<Equipment> equipments = equipmentRepository.findAlls();
        
        for(Equipment dto : equipments) {
            System.out.println(equipments);
            Row bodyRow = sheet.createRow(rowIndex++);
            
            Cell bodyCell0 = bodyRow.createCell(0); bodyCell0.setCellValue(dto.getId());  bodyCell0.setCellStyle(bodyStyleCenter);
            Cell bodyCell1 = bodyRow.createCell(1);     bodyCell1.setCellStyle(bodyStyle);
            Cell bodyCell2 = bodyRow.createCell(2); bodyCell2.setCellValue(dto.getNickname()); bodyCell2.setCellStyle(bodyStyle);
            Cell bodyCell3 = bodyRow.createCell(3); bodyCell3.setCellValue(dto.getEquipment()); bodyCell3.setCellStyle(bodyStyle);
            Cell bodyCell4 = bodyRow.createCell(4); bodyCell4.setCellValue(dto.getSettingIp()); bodyCell4.setCellStyle(bodyStyle);
            Cell bodyCell5 = bodyRow.createCell(5); bodyCell5.setCellValue(dto.getSettingType()); bodyCell5.setCellStyle(bodyStyle);
            Cell bodyCell6 = bodyRow.createCell(6); bodyCell6.setCellValue(dto.getSettingCatagory());bodyCell6.setCellStyle(bodyStyle);
            Cell bodyCell7 = bodyRow.createCell(7);     bodyCell7.setCellStyle(bodyStyle);
            Cell bodyCell8 = bodyRow.createCell(8); bodyCell8.setCellValue(dto.getSettingOs()); bodyCell8.setCellStyle(bodyStyle);
            Cell bodyCell9 = bodyRow.createCell(9); bodyCell9.setCellValue(dto.getSettingPerson()); bodyCell9.setCellStyle(bodyStyle);
            Cell bodyCell10 = bodyRow.createCell(10);   bodyCell10.setCellStyle(bodyStyle);
            Cell bodyCell11 = bodyRow.createCell(11); bodyCell11.setCellValue(dto.isSettingActive() ? "활성"  : "비활성" ); bodyCell11.setCellStyle(bodyStyleCenter);
            Cell bodyCell12 = bodyRow.createCell(12); bodyCell12.setCellValue(dto.getSettingProxy()); bodyCell12.setCellStyle(bodyStyle);
            Cell bodyCell13 = bodyRow.createCell(13); bodyCell13.setCellValue(dto.getSettingTemplate()); bodyCell13.setCellStyle(bodyStyle);
            Cell bodyCell14 = bodyRow.createCell(14); 
                if(dto.getHwCpu() != null) { bodyCell14.setCellValue(dto.getHwCpu());} else { bodyCell14.setCellValue(""); } bodyCell14.setCellStyle(bodyStyleRight);
                
            Cell bodyCell15 = bodyRow.createCell(15); 
                if(dto.getHwCpu() != null) { bodyCell15.setCellValue(dto.getHwCpu());} else { bodyCell15.setCellValue(""); } bodyCell15.setCellStyle(bodyStyleRight);
                
            Cell bodyCell16 = bodyRow.createCell(16);  
                if(dto.getHwDisk() != null) { bodyCell16.setCellValue(dto.getHwDisk());} else { bodyCell16.setCellValue(""); } bodyCell16.setCellStyle(bodyStyleRight);
                
            Cell bodyCell17 = bodyRow.createCell(17); 
                if(dto.getHwNic() != null) { bodyCell17.setCellValue(dto.getHwNic());} else { bodyCell17.setCellValue(""); } bodyCell17.setCellStyle(bodyStyleRight);
                
            Cell bodyCell18 = bodyRow.createCell(18);  
                if(dto.getHwSensor() != null) { bodyCell18.setCellValue(dto.getHwSensor());} else { bodyCell18.setCellValue(""); } bodyCell18.setCellStyle(bodyStyleRight); 
        }
        
        for(int i = 0; 18 >= i; i++) {
            sheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
          workbook.write(out);
          workbook.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
        return new ByteArrayInputStream(out.toByteArray()); 
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

    @Transactional
    @Override
    public ResponseEntity<?> uploadExcel( String data) {
       JSONArray json = new JSONArray(data);
       List<Object> notIpdeviceList = new ArrayList<>();
       
       for(int i=0; i < json.length(); i++) {
           JSONObject checkIpData = (JSONObject) json.get(i);
           
           if(equipmentRepository.checkSettingIp(checkIpData.getString("settingIp"))) {
              Map<String, Object> obj = new HashMap<String, Object>();
              obj.put("equipment", checkIpData.getString("equipment"));
              obj.put("nickname", checkIpData.getString("nickname"));
              notIpdeviceList.add(obj);
           } else {
               Equipment equipment = new Equipment();
               equipment.setEquipment(checkIpData.getString("equipment"));
               equipment.setNickname(checkIpData.getString("nickname"));
               equipment.setSettingIp(checkIpData.getString("settingIp"));   // int
               if(checkIpData.has("settingType")) {
                   equipment.setSettingType(checkIpData.getString("settingType"));
                }
                if(checkIpData.has("settingPerson")) {
                   equipment.setSettingPerson(checkIpData.getString("settingPerson"));
                }
                if(checkIpData.has("settingTemplate")) {
                   equipment.setSettingTemplate(checkIpData.getString("settingTemplate"));
                }
                if(checkIpData.has("settingCatagory")) {
                   equipment.setSettingCatagory(checkIpData.getString("settingCatagory"));
                }
               equipment.setHwCpu(checkIpData.getInt("hwCpu"));
               equipment.setHwDisk(checkIpData.getInt("hwDisk"));
               equipment.setHwNic(checkIpData.getInt("hwNic"));
               equipment.setHwSensor(checkIpData.getInt("hwSensor"));
               equipment.setDeletedFlag(true);
               
               equipmentRepository.save(equipment);
           }    
       } 
       return  !notIpdeviceList.isEmpty() ?  ResponseEntity.ok(notIpdeviceList) : null; 
           
        
       
    }

   
  

	
	
	

}










