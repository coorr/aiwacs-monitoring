package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topology_image")
@NoArgsConstructor
@Getter
@Setter
public class TopologyImage {
    
        @Id
        @SequenceGenerator(name="topology_image_seq", sequenceName="topology_image_seq", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="topology_image_seq")
        @Column(name = "Image_id")  
        private Integer id;
        
        private String location;  
        private Integer diagramId;   
        
        
}
