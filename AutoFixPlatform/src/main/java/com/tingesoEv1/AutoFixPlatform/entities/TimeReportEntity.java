package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

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

    private String brand;
    private int quantity;
    private Duration sumTime;
    private Long hours;
    private int minutes;
    private int seconds;
    private Long totalSeconds;
}
