package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    List<RepairEntity> findByPlate(String plate);
    List<RepairEntity> findByReparationType(int reparationType);
    List<RepairEntity> findByCheckinDateBefore(LocalDate checkinDate);
    List<RepairEntity> findByExitDateBefore(LocalDate exitDate);
    List<RepairEntity> findByTotalAmountBetween(Integer startTotalAmount, Integer endTotalAmount);
    List<RepairEntity> findByCollectDateBefore(LocalDate collectDate);
}
