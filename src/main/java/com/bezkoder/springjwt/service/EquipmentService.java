package com.bezkoder.springjwt.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.payload.request.EquipmentRequest;

public interface EquipmentService {
	
	public ResponseEntity<?> updateEquipmentByNo(Integer equipId,EquipmentRequest equipmentRequest);
	public ResponseEntity<?> createEquipment(EquipmentRequest equipmentRequest);
	public List<Equipment> getEquipments(); 
	public List<Equipment> getEquipmentsSnmp();
	public void onActiveEquipment(String equipId);
	public void offActiveEquipment(String equipId);
	public void deleteEquipment(String equipId);
	public List<Equipment> searchFilterEquipment(String equipType,String equipCatagory);
	public void allTooltipHwUpdateEquipment(Integer hwCpu,Integer hwDisk,Integer hwNic, Integer hwSensor,String hwid);
    public void eachTooltipHwUpdateEquipment(Integer hwCpu,Integer hwDisk,Integer hwNic, Integer hwSensor,String hwid);
    public List<Equipment> getTooltipByNo(Integer hwid);
    public ByteArrayInputStream  downloadExcel();
    public ResponseEntity<?> uploadExcel( String data);
    
    
	

	
}
