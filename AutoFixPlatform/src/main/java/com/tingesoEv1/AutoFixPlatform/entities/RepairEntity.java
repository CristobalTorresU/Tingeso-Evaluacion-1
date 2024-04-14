package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long repair_id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    private String plate;
    private LocalDate checkinDate;
    private LocalTime checkinHour;
    private int reparationType;
    private int totalAmount;
    private LocalDate exitDate;
    private LocalTime exitHour;
    private LocalDate collectDate;
    private LocalTime collectHour;
    // TODO: ¿Qué hacer cuando es más de una sola reparación a la vez?
    private int repairAmount;
    private int repairsDiscount;
    private int dayDiscount;
    private int bonusDiscount;
    private int mileageRecharge;
    private int yearRecharge;
    private int lateRecharge;
    private int IVA;
}
