package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "repairHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Date checkinDate;
    private String checkinHour;
    private String reparationType;
    private int totalAmount;
    private Date exitDate;
    private String exitHour;
    private Date collectDate;
    private String collectHour;
}
