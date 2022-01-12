package com.bezkoder.springjwt.servieImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.TopologyLink;
import com.bezkoder.springjwt.models.TopologyNode;
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
        
        System.out.println(jObject.getJSONArray("nodeDataArray"));
        JSONArray nodeDataArray = jObject.getJSONArray("nodeDataArray");
        System.out.println(nodeDataArray.length());
        JSONObject nodeDataIndex = (JSONObject) nodeDataArray.get(0);
        String equipment = nodeDataIndex.getString("equipment");
        System.out.println(equipment);
        
        
        List<TopologyNode> topologyNodes = topologyNodeRepository.getTopologyNode();
        List<Number> keyNode = new ArrayList<Number>();
        
        // 노드 추가
        for (int i=0;i<nodeDataArray.length();i++) {
            TopologyNode tNode = new TopologyNode();
            
            JSONObject obj = (JSONObject) nodeDataArray.get(i);   
            System.out.println((JSONObject) nodeDataArray.get(i));
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
        
        
        
        
        
        
        
        

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(topologyNode);
//            String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(test);
//            System.out.println(json2);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        
    }

}




















