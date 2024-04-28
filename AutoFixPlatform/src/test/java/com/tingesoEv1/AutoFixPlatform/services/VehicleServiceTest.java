package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(VehicleService.class)
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    void whenGetVehicles_thenCorrect() {
        //Given
        VehicleEntity vehicle1 = new VehicleEntity();
        VehicleEntity vehicle2 = new VehicleEntity();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        //When
        when(vehicleRepository.findAll()).thenReturn(vehicles);
        ArrayList<VehicleEntity> vehiclesExample = vehicleService.getVehicles();

        //Then
        assertNotNull(vehicles);
        assertThat(vehiclesExample.size()).isEqualTo(2);
    }

    @Test
    void whenSaveVehicle_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        VehicleEntity vehicleExample = vehicleService.saveVehicle(vehicle);

        //Then
        assertNotNull(vehicle);
        assertThat(vehicleExample.getPlate()).isEqualTo("AAAA11");
    }

    @Test
    void whenSaveVehicle_thenNull() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.findByPlate(vehicle.getPlate())).thenReturn(vehicle);
        VehicleEntity vehicleExample = vehicleService.saveVehicle(vehicle);

        //Then
        assertThat(vehicleExample).isNull();
    }

    @Test
    void whenGetVehicleById_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1L);
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.findById(1L)).thenReturn(java.util.Optional.of(vehicle));
        VehicleEntity vehicleExample = vehicleService.getVehicleById(1L);

        //Then
        assertNotNull(vehicle);
        assertThat(vehicleExample.getPlate()).isEqualTo("AAAA11");
    }

    @Test
    void whenGetVehicleByPlate_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1L);
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.findByPlate("AAAA11")).thenReturn(vehicle);
        VehicleEntity vehicleExample = vehicleService.getVehicleByPlate("AAAA11");

        //Then
        assertNotNull(vehicle);
        assertThat(vehicleExample.getPlate()).isEqualTo("AAAA11");
    }

    @Test
    void whenUpdateVehicle_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1L);
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        VehicleEntity vehicleExample = vehicleService.updateVehicle(vehicle);

        //Then
        assertNotNull(vehicle);
        assertThat(vehicleExample.getPlate()).isEqualTo("AAAA11");
    }

    /*
    // TODO: Fix this test
    @Test
    void whenDeleteVehicle_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1L);
        vehicle.setPlate("AAAA11");
        vehicle.setBrand("Ford");
        vehicle.setMileage(8402);
        vehicle.setType("Pickup");
        vehicle.setYear(2014);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(6);

        //When
        when(vehicleRepository.deleteById(1L)).thenReturn(null);
        boolean vehicleExample = vehicleService.deleteVehicle(1L);

        //Then
        assertThat(vehicleExample).isTrue();
    }
     */

    @Test
    void whenDeleteVehicle_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1L);

        //When
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        boolean result = false;
        try {
            result = vehicleService.deleteVehicle(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertThat(result).isTrue();
    }
}
