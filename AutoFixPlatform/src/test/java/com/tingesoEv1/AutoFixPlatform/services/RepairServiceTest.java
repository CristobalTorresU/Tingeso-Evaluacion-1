package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.RepairRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RepairServiceTest {

    @Autowired
    private RepairService repairService;

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private RepairRepository repairRepository;

    @Test
    void whenGetRepairs_thenCorrect() {
        //Given
        RepairEntity repair1 = new RepairEntity();
        RepairEntity repair2 = new RepairEntity();

        ArrayList<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairRepository.findAll()).thenReturn(repairs);
        ArrayList<RepairEntity> repairsExample = repairService.getRepairs();

        //Then
        assertNotNull(repairs);
        assertThat(repairsExample.size()).isEqualTo(2);
    }

    @Test
    void whenSaveRepair_thenCorrect() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setPlate("AAAA21");
        repair.setCheckinDate(LocalDate.parse("2020-06-01"));
        repair.setCheckinHour(LocalTime.parse("10:00"));
        repair.setReparationType(1);
        repair.setTotalAmount(10000);
        repair.setExitDate(LocalDate.parse("2020-06-02"));
        repair.setExitHour(LocalTime.parse("12:00"));
        repair.setCollectDate(LocalDate.parse("2020-06-03"));
        repair.setCollectHour(LocalTime.parse("14:00"));

        //When
        when(repairRepository.save(repair)).thenReturn(repair);
        RepairEntity repairExample = repairService.saveRepair(repair);

        //Then
        assertNotNull(repair);
        assertThat(repairExample.getPlate()).isEqualTo("AAAA21");
        assertThat(repairExample.getReparationType()).isEqualTo(1);
    }

    @Test
    void whenSaveRepair_thenNull() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setPlate("AAAA21");
        repair.setCheckinDate(LocalDate.parse("2020-06-01"));
        repair.setCheckinHour(LocalTime.parse("10:00"));
        repair.setReparationType(1);
        repair.setTotalAmount(10000);
        repair.setExitDate(LocalDate.parse("2020-06-02"));
        repair.setExitHour(LocalTime.parse("12:00"));
        repair.setCollectDate(LocalDate.parse("2020-06-03"));
        repair.setCollectHour(LocalTime.parse("14:00"));
    }

    @Test
    void whenDeleteRepair_thenCorrect() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setRepair_id(1L);
        repair.setPlate("AAAA21");
        repair.setCheckinDate(LocalDate.parse("2020-06-01"));
        repair.setCheckinHour(LocalTime.parse("10:00"));
        repair.setReparationType(1);
        repair.setTotalAmount(10000);
        repair.setExitDate(LocalDate.parse("2020-06-02"));
        repair.setExitHour(LocalTime.parse("12:00"));
        repair.setCollectDate(LocalDate.parse("2020-06-03"));
        repair.setCollectHour(LocalTime.parse("14:00"));

        //When
        when(repairRepository.save(repair)).thenReturn(repair);
        boolean result = false;
        try {
            result = repairService.deleteRepair(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteRepair_thenIncorrect() {
        doThrow(new RuntimeException("Error")).when(repairRepository).deleteById(anyLong());

        Exception exception = assertThrows(Exception.class, () -> repairService.deleteRepair(1L));

        assertEquals("Error", exception.getMessage());
    }

    @Test
    void whenGetRepairById_thenCorrect() {
        //Given
        RepairEntity repair = new RepairEntity();
        repair.setRepair_id(1L);
        repair.setPlate("AAAA21");
        repair.setCheckinDate(LocalDate.parse("2020-06-01"));
        repair.setCheckinHour(LocalTime.parse("10:00"));
        repair.setReparationType(1);
        repair.setTotalAmount(10000);
        repair.setExitDate(LocalDate.parse("2020-06-02"));
        repair.setExitHour(LocalTime.parse("12:00"));
        repair.setCollectDate(LocalDate.parse("2020-06-03"));
        repair.setCollectHour(LocalTime.parse("14:00"));

        //When
        when(repairRepository.findById(1L)).thenReturn(java.util.Optional.of(repair));
        RepairEntity repairExample = repairService.getRepairById(1L);

        //Then
        assertNotNull(repair);
        assertThat(repairExample.getPlate()).isEqualTo("AAAA21");
        assertThat(repairExample.getReparationType()).isEqualTo(1);
    }

    @Test
    void whenGetRepairsByPlate_thenCorrect() {
        //Given
        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("BBBB22");

        ArrayList<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);

        //When
        when(repairRepository.findByPlate("AAAA21")).thenReturn(repairs);
        ArrayList<RepairEntity> repairsExample = (ArrayList<RepairEntity>) repairService.getRepairsByPlate("AAAA21");

        //Then
        assertNotNull(repairs);
        assertThat(repairsExample.size()).isEqualTo(2);
    }

    @Test
    void whenCalculatePrice_thenCorrect() {
        //Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setBrand("Toyota");
        vehicle.setModel("Yaris");
        vehicle.setYear(2010);
        vehicle.setMileage(100000);
        vehicle.setMotor("Gasolina");
        vehicle.setSeats(5);
        vehicle.setType("Pickup");

        RepairEntity repair = new RepairEntity();
        repair.setRepair_id(1L);
        repair.setPlate("AAAA21");
        repair.setCheckinDate(LocalDate.parse("2020-06-01"));
        repair.setCheckinHour(LocalTime.parse("10:00"));
        repair.setReparationType(1);
        repair.setTotalAmount(10000);
        repair.setExitDate(LocalDate.parse("2020-06-02"));
        repair.setExitHour(LocalTime.parse("12:00"));
        repair.setCollectDate(LocalDate.parse("2020-06-03"));
        repair.setCollectHour(LocalTime.parse("14:00"));

        //When
        when(repairRepository.save(repair)).thenReturn(repair);
        boolean result = repairService.calculatePrice("AAAA21",
                "2020-06-01",
                "10:00",
                1,
                "2020-06-02",
                "12:00",
                "2020-06-03",
                "14:00");

        //Then
        assertThat(result).isTrue();
    }
}
