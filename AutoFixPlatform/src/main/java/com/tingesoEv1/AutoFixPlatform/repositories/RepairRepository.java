package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.util.Map;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    List<RepairEntity> findByPlate(String plate);
    List<RepairEntity> findByReparationType(int reparationType);
    List<RepairEntity> findByCheckinDateBefore(LocalDate checkinDate);
    List<RepairEntity> findByExitDateBefore(LocalDate exitDate);
    List<RepairEntity> findByTotalAmountBetween(Integer startTotalAmount, Integer endTotalAmount);
    List<RepairEntity> findByCollectDateBefore(LocalDate collectDate);

    // TODO: Verificar que funcionen con "rep.vehicle veh"
    @Query(value = "SELECT rep.reparationType, SUM(rep.totalAmount) AS sumita, COUNT(rep.reparationType), veh.type " +
            "FROM repair AS rep " +
            "LEFT JOIN vehicle AS veh ON veh.vehicle_id = rep.vehicle_id" +
            "GROUP BY rep.reparationType, veh.type " +
            "ORDER BY sumita DESC", nativeQuery = true)
    List<Object[]> getTypeReport();

    // TODO: Intentar generar el reporte R3 sin usar AGE().

    // TODO: Verificar que funcionen con "rep.vehicle veh"
    @Query(value = "SELECT rep.reparationType, SUM(rep.totalAmount) AS sumita, COUNT(rep.reparationType), veh.motor " +
            "FROM repair rep " +
            "LEFT JOIN rep.vehicle veh " +
            "GROUP BY rep.reparationType, veh.motor " +
            "ORDER BY sumita DESC", nativeQuery = true)
    List<Map<String, Object>> getMotorReport();
}
