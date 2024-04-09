package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.RepairHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface RepairHistoryRepository extends JpaRepository<RepairHistoryEntity, Long> {
    public List<RepairHistoryEntity> findByReparationType(String type);
    public List<RepairHistoryEntity> findByCheckinDate(Date checkinDate);
    public List<RepairHistoryEntity> findByExitDate(Date exitDate);
    public List<RepairHistoryEntity> findByTotalAmountBetween(Integer startTotalAmount, Integer endTotalAmount);
}
