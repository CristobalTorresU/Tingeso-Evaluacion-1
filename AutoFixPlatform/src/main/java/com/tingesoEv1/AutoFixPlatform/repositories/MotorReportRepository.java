package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorReportRepository extends JpaRepository<MotorReportEntity, Long> {
    @Query(value = "SELECT * FROM motor_report WHERE motor_report.reparation_type = :reparationType AND motor_report.motor = :motor", nativeQuery = true)
    public MotorReportEntity findByReparationTypeAndMotor(@Param("reparationType") int reparationType,
                                                        @Param("motor") String motor);

    @Query(value = "SELECT * FROM motor_report ORDER BY motor_report.total_amount DESC", nativeQuery = true)
    List<MotorReportEntity> orderByTotalAmount();
}
