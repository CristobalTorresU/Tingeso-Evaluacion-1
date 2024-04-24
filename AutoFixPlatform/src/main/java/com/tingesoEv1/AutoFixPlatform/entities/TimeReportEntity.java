package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "time_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int reparationType;
    private int quantity;
    private LocalTime sumTime;
    private LocalTime averageTime;
}
