package com.aiwacs.spring.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aiwacs.spring.models.Equipment;
import com.aiwacs.spring.payload.request.EquipmentRequest;

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
