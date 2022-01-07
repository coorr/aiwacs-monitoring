package com.bezkoder.springjwt.servieImpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.TopologyNode;
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
    
    @Override
    public List<TopologyNode> getTopologyNode() {
        return topologyNodeRepository.getTopologyNode();
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
        
        List<TopologyNode> test = topologyNodeRepository.getTopologyNode();
        System.out.println(test.size());
        
        for (int i=0;i<nodeDataArray.length();i++) {
            TopologyNode tNode = new TopologyNode();
            
            JSONObject obj = (JSONObject) nodeDataArray.get(i);   
            System.out.println((JSONObject) nodeDataArray.get(i));
            //equipment: "테스트"
//            id: 1
//            loc: "0 0"
//            settingIp: "10.10.10.80"
            if(obj.getString("equipment") != null && obj.get("loc") != null && obj.get("settingIp") != null) {
                tNode.setEquipment(obj.getString("equipment"));
                System.out.println("asdasdasdasd");
                System.out.println(obj.getInt("id"));
                tNode.setId(obj.getInt("id"));
//                tNode.setId((Integer) obj.get("id"));
                tNode.setLoc((String) obj.get("loc"));
                tNode.setSettingIp((String) obj.get("settingIp"));
                topologyNodeRepository.saveAndFlush(tNode);
            }
            
        }
        
        

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(topologyNode);
            String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(test);
            System.out.println(json2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        topologyNodeRepository.save(topologyNode);
        
    }

}




















