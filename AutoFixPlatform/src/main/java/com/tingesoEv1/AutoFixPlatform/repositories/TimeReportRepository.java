package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeReportRepository extends JpaRepository<TimeReportEntity, Long> {
    public TimeReportEntity findByBrand(String brand);
    @Query(value = "SELECT * FROM time_report ORDER BY time_report.total_seconds ASC", nativeQuery = true)
    List<TimeReportEntity> orderByTime();
}
