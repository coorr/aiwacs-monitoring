package com.bezkoder.springjwt.models;

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
@Table(name="stat_sys")
public class StatSys {
    
    @Id
    @SequenceGenerator(name="stat_sys_seq", sequenceName="stat_sys_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stat_sys_seq")
    @Column(name="sys_id", unique=true, nullable=false)
    private Integer id;
    
    private LocalDateTime generateTime;
    private BigDecimal cpuContextswitch;
    private BigDecimal cpuGuest;
    private BigDecimal cpuGuestnice;
    private BigDecimal cpuIdle;
    private BigDecimal cpuIrq;
    private BigDecimal cpuNice;
    private BigDecimal cpuProcessor;
    private BigDecimal cpuSoftirq;
    private BigDecimal cpuSteal;
    private BigDecimal cpuSwap;
    private BigDecimal cpuSyscall;
    private BigDecimal cpuSystem;
    private BigDecimal cpuUser;
    private BigDecimal cpuWait;
    private BigDecimal cpuWinqueuedepth;
    private BigDecimal cpuLoadavg;
    private BigDecimal memoryBuffers;
    private BigDecimal memoryBuffersPercentage;
    private BigDecimal memoryCached;
    private BigDecimal memoryCachedPercentage;
    private BigDecimal memoryShared;
    private BigDecimal memorySharedPercentage;
    private BigDecimal memoryPagefault;
    private BigDecimal totalMemory;
    private BigDecimal totalSwap;
    private BigDecimal usedMemory;
    private BigDecimal usedMemoryPercentage;
    private BigDecimal usedSwap;
    private BigDecimal usedSwapPercentage;
    private Integer deviceId;
    private String deviceName;
    
    



}
