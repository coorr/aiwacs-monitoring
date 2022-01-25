package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.TopologyNode;
import com.bezkoder.springjwt.payload.request.EquipmentRequest;
import com.bezkoder.springjwt.service.GroupService;
import com.bezkoder.springjwt.service.TopologyNodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class TopologyNodeController {

    private final TopologyNodeService topologyNodeService;

    @GetMapping("/topology/getTopologyNode/{diagramId}")
    public Map<String, Object> getTopologyNode(@PathVariable("diagramId") Integer diagramId)  {
        return topologyNodeService.getTopologyNode(diagramId);
    }
    
    @PostMapping("/topology/insertTopologyNode/{diagramId}")
    public void insertTopologyNode(@PathVariable("diagramId") Integer diagramId, @RequestBody String topologyNode)  {
        topologyNodeService.insertTopologyNode(diagramId,topologyNode);
    }
    
    @PostMapping("/topology/diagramInsertImage/{diagramId}")
    public void diagramInsertImage(@PathVariable("diagramId") Integer diagramId, @RequestParam("file") MultipartFile multipartFile)  {
        topologyNodeService.diagramInsertImage(diagramId,multipartFile);
    }
    
    @GetMapping("/topology/getDiagramGroup")
    public List<DiagramGroup> getDiagramGroup()  {
        return topologyNodeService.getDiagramGroup();
    }
    
    @PostMapping("/topology/insertDiagramGroup")
    public List<DiagramGroup> insertDiagramGroup(@RequestBody String diagramGroup)  {
        return topologyNodeService.insertDiagramGroup(diagramGroup);
    }
    
    @PostMapping("/topology/updateDiagramGroup")
    public List<DiagramGroup> updateDiagramGroup(@RequestBody String diagramGroup)  {
        return topologyNodeService.updateDiagramGroup(diagramGroup);
    }
    
    @PostMapping("/topology/deleteDiagramGroup/{groupId}")
    public List<DiagramGroup> deleteDiagramGroup(@PathVariable("groupId") String groupId)  {
        return topologyNodeService.deleteDiagramGroup(groupId);
    }
}
