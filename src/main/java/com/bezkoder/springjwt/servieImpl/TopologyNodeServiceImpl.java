package com.bezkoder.springjwt.servieImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.common.HistoryUtils;
import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.TopologyLink;
import com.bezkoder.springjwt.models.TopologyNode;
import com.bezkoder.springjwt.repository.DiagramGroupRepository;
import com.bezkoder.springjwt.repository.TopologyLinkRepository;
import com.bezkoder.springjwt.repository.TopologyNodeRepository;
import com.bezkoder.springjwt.service.TopologyNodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopologyNodeServiceImpl implements TopologyNodeService {
    
    private final TopologyNodeRepository topologyNodeRepository;
    private final TopologyLinkRepository topologyLinkRepository;
    private final DiagramGroupRepository diagramGroupRepository;
    
    @Override
    public Map<String, Object> getTopologyNode() {
        Map<String, Object> resultList = new HashMap<String, Object>();
        
        List<TopologyNode> topologyNodes = topologyNodeRepository.getTopologyNode();
        resultList.put("nodeDataArray", topologyNodes);
        
        List<TopologyLink> topologyLinks = topologyLinkRepository.getTopologyLink();
        resultList.put("linkDataArray", topologyLinks);
        
        return resultList;
    }

    @Transactional
    @Override
    public void insertTopologyNode(String topologyNode) {
        JSONObject jObject = new JSONObject(topologyNode);
        
        JSONArray nodeDataArray = jObject.getJSONArray("nodeDataArray");
        JSONArray linkDataArray = jObject.getJSONArray("linkDataArray");
        
        List<TopologyNode> topologyNodes = topologyNodeRepository.getTopologyNode();
        List<Number> keyNode = new ArrayList<Number>();
        
        // 노드 추가
        for (int i=0;i<nodeDataArray.length();i++) {
            TopologyNode tNode = new TopologyNode();
            
            JSONObject obj = (JSONObject) nodeDataArray.get(i);   
            tNode.setId(obj.getInt("id"));
            tNode.setEquipment(obj.getString("equipment"));
            tNode.setLoc(obj.getString("loc"));
            tNode.setSettingIp(obj.getString("settingIp"));
            topologyNodeRepository.saveAndFlush(tNode);
            
            keyNode.add(obj.getInt("id"));
        }
        
        // 노드 삭제
        for(int j=0; j< topologyNodes.size(); j++) {
            TopologyNode nodeDeleteKey = topologyNodes.get(j);
            if(!keyNode.contains(nodeDeleteKey.getId())) {
                topologyNodeRepository.deleteTopogolyNode(nodeDeleteKey.getId());
            }
        }
        
        List<TopologyLink> topologyLinks = topologyLinkRepository.getTopologyLink();
        List<Number> keyLink = new ArrayList<Number>();
        
        // 링크 추가
        for(int k=0; k< linkDataArray.length(); k++) {
            TopologyLink tLink = new TopologyLink();
            
            JSONObject obj = (JSONObject) linkDataArray.get(k);   
            tLink.setId(obj.getInt("id"));
            tLink.setFroms(obj.getInt("froms"));
            tLink.setTos(obj.getInt("tos"));
            tLink.setBorderColor(1);
            topologyLinkRepository.saveAndFlush(tLink);
            
            keyLink.add(obj.getInt("id"));
        }
        
        // 링크 삭제
        for(int n=0; n< topologyLinks.size(); n++) {
            TopologyLink linkDeleteKey = topologyLinks.get(n);
            if(!keyLink.contains(linkDeleteKey.getId())) {
                topologyLinkRepository.deleteTopogolyLink(linkDeleteKey.getId());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(linkDataArray);
//            String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(test);
            System.out.println(json);
        } catch (JsonProcessingException e) {}
        
    }

    @Transactional
    @Override
    public List<DiagramGroup> insertDiagramGroup(String diagramGroup) {
        System.out.println(diagramGroup);
        JSONObject obj = new JSONObject(diagramGroup);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime date = LocalDateTime.now().withNano(0);
        
        
        System.out.println(auth.getName());
        DiagramGroup dGroup = new DiagramGroup();
        dGroup.setGroupName(obj.getString("groupName"));
        dGroup.setContent(obj.getString("content"));
        dGroup.setStartCreatedName(auth.getName());
        dGroup.setCreatedAt(date);  
        diagramGroupRepository.save(dGroup);
        
        return diagramGroupRepository.getDiagramGroup();
   
    }

    @Override
    public List<DiagramGroup> getDiagramGroup() {
        return diagramGroupRepository.getDiagramGroup();
    }

    @Transactional
    @Override
    public List<DiagramGroup> updateDiagramGroup(String diagramGroup) {
        System.out.println(diagramGroup);
        
        JSONObject obj = new JSONObject(diagramGroup);
        DiagramGroup dGroup = diagramGroupRepository.findOne(obj.getInt("id"));
        dGroup.setId(obj.getInt("id"));
        dGroup.setContent(obj.getString("content"));
        dGroup.setGroupName(obj.getString("groupName"));
        return diagramGroupRepository.getDiagramGroup();
    }

}




















