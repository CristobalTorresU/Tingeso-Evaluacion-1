package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeReportRepository extends JpaRepository<TimeReportEntity, Long> {
    public TimeReportEntity findByReparationType(int reparationType);
    // TODO: ASUMIR QUE SE PUEDE ORDENAR POR TIEMPO.
    @Query(value = "SELECT * FROM time_report ORDER BY time_report.average_time DESC", nativeQuery = true)
    List<TimeReportEntity> orderByTime();
}