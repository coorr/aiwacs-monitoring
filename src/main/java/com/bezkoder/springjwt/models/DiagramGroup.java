package com.bezkoder.springjwt.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "diagram_group")
@NoArgsConstructor
@Getter
@Setter
public class DiagramGroup {

      @Id
      @SequenceGenerator(name="diagram_group_seqs", sequenceName="diagram_group_seqs", allocationSize=1)
      @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="diagram_group_seqs")  
      @Column(name = "diagram_id")
      private Integer id;
    
      private String groupName;  
      private String content;
      private String startCreatedName;
      private String endCreatedName;
      
      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
      private LocalDateTime createdAt;
      
      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
      private LocalDateTime updatedAt;
      private String imageLocation;
}


















