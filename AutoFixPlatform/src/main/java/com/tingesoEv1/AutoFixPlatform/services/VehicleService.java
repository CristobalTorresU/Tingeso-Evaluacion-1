package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public ArrayList<VehicleEntity> getVehicles() {
        return (ArrayList<VehicleEntity>) vehicleRepository.findAll();
    }

    public VehicleEntity saveVehicle(VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public VehicleEntity getVehicleById(Long id) {
        return vehicleRepository.findById(id).get();
    }

    public VehicleEntity getVehicleByPlate(String plate) {
        return vehicleRepository.findByPlate(plate);
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean deleteVehicle(Long id) throws Exception {
        try{
            vehicleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}