package com.bezkoder.springjwt.servieImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.common.HistoryUtils;
import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.TopologyImage;
import com.bezkoder.springjwt.models.TopologyLink;
import com.bezkoder.springjwt.models.TopologyNode;
import com.bezkoder.springjwt.repository.DiagramGroupRepository;
import com.bezkoder.springjwt.repository.TopologyImageRepository;
import com.bezkoder.springjwt.repository.TopologyLinkRepository;
import com.bezkoder.springjwt.repository.TopologyNodeRepository;
import com.bezkoder.springjwt.service.TopologyNodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.conditional.IfAction;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TopologyNodeServiceImpl implements TopologyNodeService {
    
    private final TopologyNodeRepository topologyNodeRepository;
    private final TopologyLinkRepository topologyLinkRepository;
    private final DiagramGroupRepository diagramGroupRepository;
    private final TopologyImageRepository topologyImageRepository;
    
    @Override
    public Map<String, Object> getTopologyNode(Integer diagramId) {
        Map<String, Object> resultList = new HashMap<String, Object>();
        
        List<TopologyNode> topologyNodes = topologyNodeRepository.getByNoTopologyNode(diagramId);
        resultList.put("nodeDataArray", topologyNodes);
        
        List<TopologyLink> topologyLinks = topologyLinkRepository.getByNoTopologyLink(diagramId);
        resultList.put("linkDataArray", topologyLinks);
        
        List<DiagramGroup> diagramGroups = diagramGroupRepository.getByNoDiagramGroup(diagramId);
        JSONArray dArray = new JSONArray(diagramGroups);
        JSONObject dObject = dArray.getJSONObject(0);
//        if(dObject.getString("imageLocation") != und) {
//            String location =  dObject.getString("imageLocation");
//            resultList.put("image", location);
//        }
        
        
        return resultList;
    }

    @Transactional
    @Override
    public void insertTopologyNode(Integer diagramId, String topologyNode) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime date = LocalDateTime.now().withNano(0);
        JSONObject jObject = new JSONObject(topologyNode);
        
        JSONArray nodeDataArray = jObject.getJSONArray("nodeDataArray");
        JSONArray linkDataArray = jObject.getJSONArray("linkDataArray");
        
        List<TopologyNode> topologyNodes = topologyNodeRepository.getByNoTopologyNode(diagramId);
        List<String> keyNode = new ArrayList<String>();
        
        // 노드 추가
        for (int i=0;i<nodeDataArray.length();i++) {
            TopologyNode tNode = new TopologyNode();
            
            JSONObject obj = (JSONObject) nodeDataArray.get(i);   
            tNode.setId(obj.getString("id"));
            tNode.setEquipment(obj.getString("equipment"));
            tNode.setLoc(obj.getString("loc"));
            tNode.setSettingIp(obj.getString("settingIp"));
            tNode.setDiagramId(diagramId);
            topologyNodeRepository.saveAndFlush(tNode);
              
            keyNode.add(obj.getString("id"));
        }
        
        // 노드 삭제
        for(int j=0; j< topologyNodes.size(); j++) {
            TopologyNode nodeDeleteKey = topologyNodes.get(j);
            if(!keyNode.contains(nodeDeleteKey.getId())) {
                topologyNodeRepository.deleteTopogolyNode(nodeDeleteKey.getId());
            }
        }
        
        List<TopologyLink> topologyLinks = topologyLinkRepository.getByNoTopologyLink(diagramId);
        List<Integer> keyLink = new ArrayList<Integer>();
        
        // 링크 추가
        for(int k=0; k< linkDataArray.length(); k++) {
            TopologyLink tLink = new TopologyLink();
            
            JSONObject obj = (JSONObject) linkDataArray.get(k);   
            tLink.setId(obj.getInt("id"));
            tLink.setFroms(obj.getString("froms"));
            tLink.setTos(obj.getString("tos"));
            tLink.setDiagramId(diagramId);
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
        
        DiagramGroup diagramGroups = diagramGroupRepository.findOne(diagramId);
        diagramGroups.setEndCreatedName(auth.getName());
        diagramGroups.setUpdatedAt(date);
    }
    
    @Transactional
    @Override
    public void diagramInsertImage(Integer diagramId, MultipartFile file) {
        try {
            String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt());
            String originName = file.getOriginalFilename(); // 파일이름과 확장자
            String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // 파일확장자
            originName = originName.substring(0, originName.lastIndexOf(".")); // 파일이름
            long fileSize = file.getSize();
            
            String absolutePath = new File("").getAbsolutePath() + "\\";
            String path = "src/main/resources/static/";
            
            File fileSave = new File(path); // ex) fileId.jpg
            if(!fileSave.exists()) {
                fileSave.mkdirs();
            }
            fileSave = new File(absolutePath + path+ "/" +fileId+ "." +fileExtension);
            file.transferTo(fileSave);
            
            System.out.println("fileId= " + fileId);
            System.out.println("originName= " + originName);
            System.out.println("fileExtension= " + fileExtension);
            System.out.println("fileSize= " + fileSize);
            System.out.println("fileSize=2 " + absolutePath + path+ "/" +fileId+ "." +fileExtension);
            
            
//            TopologyImage tImage = new TopologyImage();
//            tImage.setDiagramId(diagramId);
//            tImage.setLocation(fileId+ "." +fileExtension);
//            topologyImageRepository.save(tImage);
            DiagramGroup diagramGroups = diagramGroupRepository.findOne(diagramId);
            diagramGroups.setImageLocation(fileId+ "." +fileExtension);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
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

    @Transactional
    @Override
    public List<DiagramGroup> deleteDiagramGroup(String groupId) {
        String[] arrayId = groupId.split("\\|");
        int[] id=Arrays.stream(arrayId).mapToInt(Integer::parseInt).toArray();   
        diagramGroupRepository.deleteDiagramGroup(id);
        topologyLinkRepository.deleteAllTopogolyLink(id);
        topologyNodeRepository.deleteAllTopogolyNode(id);
        
        return diagramGroupRepository.getDiagramGroup();
    }

    

}




















