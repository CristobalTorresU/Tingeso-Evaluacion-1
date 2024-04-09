package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    public VehicleEntity findByPlate(String plate);
    List<VehicleEntity> findByBrand(String brand);
    List<VehicleEntity> findByModel(String model);
    List<VehicleEntity> findByType(String type);
    List<VehicleEntity> findByMotor(String motor);
    List<VehicleEntity> findBySeats(int seats);
    List<VehicleEntity> findByYear(int year);
}
