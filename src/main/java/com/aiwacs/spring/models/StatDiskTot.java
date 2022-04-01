package com.aiwacs.spring.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="stat_disk_tot")
public class StatDiskTot {
    
    @Id
    @SequenceGenerator(name="stat_disk_tot_seq", sequenceName="stat_disk_tot_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stat_disk_tot_seq")
    @Column(name="disktot_id", unique=true, nullable=false)
    private Integer id;
    
    private LocalDateTime generateTime;
    
    @Column(name="used_bytes")
    private BigDecimal usedBytes;
    
    @Column(name="used_percentage")
    private BigDecimal usedPercentage;

    @Column(name="device_id")
    private Integer deviceId;
    
    @Column(name="device_name")
    private String deviceName;


}
