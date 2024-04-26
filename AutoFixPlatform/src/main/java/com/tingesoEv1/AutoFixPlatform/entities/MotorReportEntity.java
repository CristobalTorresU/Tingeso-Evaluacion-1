package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "motor_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotorReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int reparationType;
    private String repairName;
    private int quantity;
    private String motor;
    private int totalAmount;
}
