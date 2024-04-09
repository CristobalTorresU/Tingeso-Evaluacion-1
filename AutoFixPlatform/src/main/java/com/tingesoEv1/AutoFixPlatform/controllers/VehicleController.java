package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin("*")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehicles() {
        List<VehicleEntity> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long id) {
        VehicleEntity vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEntity> getVehicleByPlate(@PathVariable String plate) {
        VehicleEntity vehicle = vehicleService.getVehicleByPlate(plate);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehiclesByBrand(@PathVariable String brand) {
        List<VehicleEntity> vehicles = vehicleService.getVehicleByBrand(brand);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehiclesByType(@PathVariable String type){
        List<VehicleEntity> vehicles = vehicleService.getVehicleByType(type);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehiclesByMotor(@PathVariable String motor) {
        List<VehicleEntity> vehicles = vehicleService.getVehicleByMotor(motor);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehiclesBySeats(@PathVariable int seats) {
        List<VehicleEntity> vehicles = vehicleService.getVehicleBySeats(seats);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehiclesByModel(@PathVariable String model) {
        List<VehicleEntity> vehicles = vehicleService.getVehicleByModel(model);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> listVehicleByYear(@PathVariable int year) {
        List<VehicleEntity> vehicles = vehicleService.getVehicleByYear(year);
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/")
    public ResponseEntity<VehicleEntity> saveVehicle(@RequestBody VehicleEntity vehicle) {
        VehicleEntity vehicleNew = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(vehicleNew);
    }

    @PutMapping("/")
    public ResponseEntity<VehicleEntity> updateVehicle(@RequestBody VehicleEntity vehicle) {
        VehicleEntity vehicleUpdated = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(vehicleUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicleById(@PathVariable Long id) throws Exception {
        var isDeleted = vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
