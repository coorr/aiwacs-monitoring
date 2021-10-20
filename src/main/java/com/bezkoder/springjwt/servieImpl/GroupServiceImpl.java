package com.bezkoder.springjwt.servieImpl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;
import com.bezkoder.springjwt.payload.response.GroupResponse;
import com.bezkoder.springjwt.repository.EquipmentRepository;
import com.bezkoder.springjwt.repository.GroupEquipmentJoinRepository;
import com.bezkoder.springjwt.repository.GroupRepository;
import com.bezkoder.springjwt.service.GroupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;
import lombok.RequiredArgsConstructor;

//json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultList);

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
    
    private final GroupRepository groupRepository;
    private final GroupEquipmentJoinRepository groupEquipmentJoinRepository;
  
    
    @Override
    public List<Object> getGroupEquipment() {
        ObjectMapper mapper = new ObjectMapper();
        
        List<Object> resultList = new ArrayList<Object>();
        List<Group> groupList = groupRepository.getGroup();  // 0 그룹 출력
        
        
        for(Group g: groupList) {    // depth 0 
            Map<String, Object> groupMap = new HashMap<String, Object>();
            List<Object> chilrentStrings = new ArrayList<Object>();
            
            if(g.getParent() == null) {
                groupMap.put("key", g.getId()+"-");
                groupMap.put("title", g.getTreeName());
                groupMap.put("isLeaf", false);
                groupMap.put("children",chilrentStrings );
                resultList.add(groupMap);
            }
            List<Group> childrenList = groupRepository.getGroupSecond(g.getId());  // depth 1
            
            if(childrenList.size() > 0) {
               for(Group c: childrenList) {
                	Map<String, Object> childrenMap = new HashMap<String, Object>();
                	List<Object> secondChildren = new ArrayList<Object>();
                	childrenMap.put("key", c.getId()+"-");   
                	childrenMap.put("title", c.getTreeName());
                	childrenMap.put("isLeaf", false);
                	childrenMap.put("children",secondChildren );
                    
                	List<Group> childrenList3 = groupRepository.getGroupSecond(c.getId());  // depth 2
                	
                	for(Group c3: childrenList3 ) {
                	    Map<String, Object> childrenMap3 = new HashMap<String, Object>();
                        List<Object> secondChildren3 = new ArrayList<Object>();
                        childrenMap3.put("key", c3.getId()+"-");
                        childrenMap3.put("title", c3.getTreeName());
                        childrenMap3.put("isLeaf", false);
                        childrenMap3.put("children",secondChildren3 );
                        
                        List<Equipment> equipment = groupRepository.getEquipment(c3.getId());  // depth 3
                	
                        if(equipment.size() > 0 ) {
                            for(Equipment e3: equipment) {
                              Map<String, Object> childrenEquipmentMap3 = new HashMap<String, Object>();
                              childrenEquipmentMap3.put("key", e3.getId());
                              childrenEquipmentMap3.put("title", e3.getEquipment());
                              childrenEquipmentMap3.put("settingIp", e3.getSettingIp());
                              childrenEquipmentMap3.put("settingOs", e3.getSettingOs());
                              childrenEquipmentMap3.put("settingPerson", e3.getSettingPerson());
                              childrenEquipmentMap3.put("settingTemplate", e3.getSettingTemplate());
                              childrenEquipmentMap3.put("settingActive", e3.isSettingActive());
                              childrenEquipmentMap3.put("settingProxy", e3.getSettingProxy());
                              childrenEquipmentMap3.put("settingType", e3.getSettingType());
                              secondChildren3.add(childrenEquipmentMap3);
                            }
                        }
                        secondChildren.add(childrenMap3);
                	}
                	
                	
                	List<Equipment> equipment = groupRepository.getEquipment(c.getId());
                	
                    if(equipment.size() > 0 ) {
                    	for(Equipment e2: equipment) {
                          Map<String, Object> childrenEquipmentMap = new HashMap<String, Object>();
                          childrenEquipmentMap.put("key", e2.getId());
                          childrenEquipmentMap.put("title", e2.getEquipment());
                          childrenEquipmentMap.put("settingIp", e2.getSettingIp());
                          childrenEquipmentMap.put("settingOs", e2.getSettingOs());
                          childrenEquipmentMap.put("settingPerson", e2.getSettingPerson());
                          childrenEquipmentMap.put("settingTemplate", e2.getSettingTemplate());
                          childrenEquipmentMap.put("settingActive", e2.isSettingActive());
                          childrenEquipmentMap.put("settingProxy", e2.getSettingProxy());
                          childrenEquipmentMap.put("settingType", e2.getSettingType());
                          secondChildren.add(childrenEquipmentMap);
                    	}
                    }
                    chilrentStrings.add(childrenMap);
               }
            }
            
            List<Equipment> equipment = groupRepository.getEquipment(g.getId());
            
            if(equipment.size() > 0) {
               for(Equipment e : equipment) {
                  Map<String, Object> equipmentMap = new HashMap<String, Object>();
                  equipmentMap.put("key", e.getId());
                  equipmentMap.put("title", e.getEquipment());
                  equipmentMap.put("settingIp", e.getSettingIp());
                  equipmentMap.put("settingOs", e.getSettingOs());
                  equipmentMap.put("settingPerson", e.getSettingPerson());
                  equipmentMap.put("settingTemplate", e.getSettingTemplate());
                  equipmentMap.put("settingActive", e.isSettingActive());
                  equipmentMap.put("settingProxy", e.getSettingProxy());
                  equipmentMap.put("settingType", e.getSettingType());
                  chilrentStrings.add(equipmentMap); 
               }
            }  
        }

        
        return resultList;
    }

    @Override
    public Map<String, Object> unGroupEquipment() {
        
        List<Equipment> notDeviceList= groupRepository.unGroupEquipment();
        
        Map<String, Object> notDeviceListMap  = new HashMap<String, Object>();
        notDeviceListMap.put("group_id", "9999");
        notDeviceListMap.put("group_name", "notGroup");
        notDeviceListMap.put("groups", "미지정");
        notDeviceListMap.put("devices", notDeviceList);
        
        return notDeviceListMap;
    }

    @Transactional
    @Override
    public void deleteGroupEquipmentMapping(String name,String groupId) {
        System.out.println(name);
        String[] arrayId = groupId.split(",");
        int[] id=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();
        if(name.equals("parent")) {
            groupRepository.deleteGroup(id);
        } else if(name.equals("children")) {
            groupRepository.deleteEquipment(id);
        } 
        
    }

    @Transactional
    @Override
    public void insertGroupFirst(Group group) {
        group.setTreeName(group.getTreeName());
        groupRepository.save(group);
    }

    @Transactional
    @Override
    public void insertGroupEquipmentMapping(GroupEquipmentJoin groupEquipmentJoin) {
        groupEquipmentJoin.setGroup_id(groupEquipmentJoin.getGroup_id());
        groupEquipmentJoin.setEquipment_id(groupEquipmentJoin.getEquipment_id());
        groupEquipmentJoinRepository.save(groupEquipmentJoin);
    }

    @Transactional
    @Override
    public void updateGroupName(Integer id,Group group) {
        groupRepository.updateGroupName(id,group.getTreeName());
    }

    @Override
    public List<Group> getGroupName(Integer id) {
        return groupRepository.getGroupName(id);
    }

    @Transactional
	@Override
	public void insertGroupSecond(Group group) {
		groupRepository.save(group);
	}
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
