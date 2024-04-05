package com.tingesoEv1.AutoFixPlatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bonus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String bonusDate;
    private String bonusBrand;
    private int bonusAmount;
    private int bonusQuantity;
}
