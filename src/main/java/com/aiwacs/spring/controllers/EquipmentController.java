package com.aiwacs.spring.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiwacs.spring.models.Equipment;
import com.aiwacs.spring.payload.request.EquipmentRequest;
import com.aiwacs.spring.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class EquipmentController {
	
	private final EquipmentService equipmentService;
	
	@PostMapping("/equipment")
	public ResponseEntity<?> createEquipment(@RequestBody EquipmentRequest equipmentRequest,HttpServletRequest request) {
	     return equipmentService.createEquipment(equipmentRequest);	
	}
	
	@GetMapping("/getEquipment")
	public List<Equipment> getEquipments() {
		return equipmentService.getEquipments();
	} 
	
	@GetMapping("/getEquipmentSnmp")
    public List<Equipment> getEquipmentsSnmp() {
        return equipmentService.getEquipmentsSnmp();
    }
	
	@PostMapping("/equipment/{equipId}")
	public ResponseEntity<?> updateEquipmentByNo
					(@PathVariable("equipId") Integer equipId,@RequestBody EquipmentRequest equipmentRequest) {
		return equipmentService.updateEquipmentByNo(equipId, equipmentRequest);
	}
	
	@PostMapping("/equipment/delete/{equipId}")
	public void deleteEquipment(@PathVariable("equipId") String equipId) {
			equipmentService.deleteEquipment(equipId);
	}
	
	@PostMapping("/equipment/onActive/{equipId}")
	public void onActiveEquipment(@PathVariable("equipId") String equipId,HttpServletRequest request) {
	     equipmentService.onActiveEquipment(equipId);
	}
	
	@PostMapping("/equipment/offActive/{equipId}")
	public void offActiveEquipment(@PathVariable("equipId") String equipId) {
	 	equipmentService.offActiveEquipment(equipId);
	}
	
	@GetMapping("/equipment/filterType/{equipType}/{equipCatagory}")
	public List<Equipment> searchFilterEquipment(@PathVariable("equipType") String equipType, @PathVariable("equipCatagory") String equipCatagory) {
		return equipmentService.searchFilterEquipment(equipType,equipCatagory);
	}
	
	@PostMapping("/equipment/allTooltipHwUpdate/{hwCpu}/{hwDisk}/{hwNic}/{hwSensor}/{hwid}") 
	public void allTooltipHwUpdateEquipment
	        (@PathVariable("hwCpu") Integer hwCpu, @PathVariable("hwSensor") Integer hwSensor , @PathVariable("hwDisk") Integer hwDisk
	       , @PathVariable("hwNic") Integer hwNic, @PathVariable("hwid") String hwid) {
	      equipmentService.allTooltipHwUpdateEquipment(hwCpu,hwDisk,hwNic,hwSensor,hwid);
	}
	
	@PostMapping("/equipment/eachTooltip/{hwCpu}/{hwDisk}/{hwNic}/{hwSensor}/{hwid}") 
    public void eachTooltipHwUpdateEquipment
	        (@PathVariable("hwCpu") Integer hwCpu, @PathVariable("hwSensor") Integer hwSensor , @PathVariable("hwDisk") Integer hwDisk
	       , @PathVariable("hwNic") Integer hwNic, @PathVariable("hwid") String hwid) {
	        equipmentService.eachTooltipHwUpdateEquipment(hwCpu,hwDisk,hwNic,hwSensor,hwid);
	   }
	
	@GetMapping("/equipment/getTooltipByNo/{hwid}")
	public List<Equipment> getTooltipByNo(@PathVariable("hwid") Integer hwid) {
	    return equipmentService.getTooltipByNo(hwid);
	}
	
	@GetMapping("/equipment/downloadExcel")
	public ResponseEntity<InputStreamResource> downloadExcel() {
	    ByteArrayInputStream  responseFile = equipmentService.downloadExcel();
	     return ResponseEntity
	             .ok()
	             .header("Content-Disposition", "attachment; filename=filename.xls")
	             .body(new InputStreamResource(responseFile));
	}
	
	
    @PostMapping("/equipment/uploadExcel")
	   public ResponseEntity<?> uploadExcel(@RequestBody String data){
         return  equipmentService.uploadExcel(data);
	   }
	




	

	
	
}

















