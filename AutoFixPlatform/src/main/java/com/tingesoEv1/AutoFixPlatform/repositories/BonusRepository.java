package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BonusRepository extends JpaRepository<BonusEntity, Long> {
    List<BonusEntity> findByBrandAndUsed(String brand, boolean used);
    //List<BonusEntity> findByYear(int year);
    //List<BonusEntity> findByYearAndMonth(Integer year, Integer month);
    List<BonusEntity> findByAmountLessThan(int amount);
    /*
    @Query(value = "SELECT * FROM bonus WHERE bonus.brand = :brand, nativeQuery = true")
    List<BonusEntity> getBonusesByBrandNonUsed(@Param("brand") String brand);
     */
}
