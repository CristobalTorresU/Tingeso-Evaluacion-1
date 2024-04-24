package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "repair_id")
    private RepairEntity repair;

    private int repairAmount;
    private int repairsDiscount;
    private int dayDiscount;
    private int bonusDiscount;
    private int mileageRecharge;
    private int yearRecharge;
    private int lateRecharge;
    private int IVA;
}
