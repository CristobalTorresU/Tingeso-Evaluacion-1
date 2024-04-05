package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repairHistory")
// TODO: Buscar que significan estos hashs
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String checkinDate;
    private String checkinHour;
    // Podria ser int
    private String reparationType;
    private int totalAmount;
    private String exitDate;
    private String exitHour;
    private String collectDate;
    private String collectHour;
}
