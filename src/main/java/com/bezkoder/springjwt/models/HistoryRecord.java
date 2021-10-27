package com.bezkoder.springjwt.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history_record")
@NoArgsConstructor
@Getter
@Setter
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer id;

    private String actionType;  
    private Integer settingIp;  
    private String menuDepth1;  
    private String menuDepth2;  
    private String menuDepth3;  
    private String menuDepth4s;  
    private String pageURL;  
    private String targetName;  
    private String userName;  
    private LocalDateTime workDate;


}
