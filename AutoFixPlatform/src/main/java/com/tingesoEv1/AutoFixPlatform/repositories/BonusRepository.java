package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusRepository extends JpaRepository<BonusEntity, Long> {
    List<BonusEntity> findByBrand(String brand);
    List<BonusEntity> findByYear(int year);
    List<BonusEntity> findByYearAndMonth(Integer year, Integer month);
    List<BonusEntity> findByAmountLessThan(double amount);
}
