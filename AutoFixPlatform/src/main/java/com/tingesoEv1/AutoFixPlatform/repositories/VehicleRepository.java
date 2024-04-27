package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    public VehicleEntity findByPlate(String plate);

    @Query(value = "SELECT DISTINCT vehicle.brand FROM vehicle", nativeQuery = true)
    List<String> findBrands();
}
