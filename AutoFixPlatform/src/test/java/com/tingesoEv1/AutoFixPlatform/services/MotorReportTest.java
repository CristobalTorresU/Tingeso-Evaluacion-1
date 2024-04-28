package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.MotorReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MotorReportTest {

    @Autowired
    private MotorReportService motorReportService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private MotorReportRepository motorReportRepository;

    @Test
    void whenGetMotorReports_thenCorrect() {
        //Given
        MotorReportEntity motorReport1 = new MotorReportEntity();
        MotorReportEntity motorReport2 = new MotorReportEntity();

        ArrayList<MotorReportEntity> motorReports = new ArrayList<>();
        motorReports.add(motorReport1);
        motorReports.add(motorReport2);

        //When
        when(motorReportRepository.findAll()).thenReturn(motorReports);
        ArrayList<MotorReportEntity> motorReportsExample = motorReportService.getMotorReports();

        //Then
        assertNotNull(motorReports);
        assertThat(motorReportsExample.size()).isEqualTo(2);
    }

    @Test
    void whenMakeBlankMotorReport_thenCorrect() {
        //Given
        // Simular el método deleteAll() del repositorio
        doNothing().when(motorReportRepository).deleteAll();

        // Llamar al método makeBlankReport()
        motorReportService.makeBlankReport();

        // Verificar que el método deleteAll() fue llamado una vez
        verify(motorReportRepository, times(1)).deleteAll();
    }

    @Test
    void whenMakeMotorReport_thenCorrect() {
        //Given
        RepairService repairService = mock(RepairService.class);

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA11");
        repair1.setReparationType(1);
        repair1.setTotalAmount(1000);
        ArrayList<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1));

        MotorReportEntity motorReport1 = new MotorReportEntity();
        motorReport1.setReparationType(1);
        motorReport1.setMotor("Gasolina");
        motorReport1.setTotalAmount(1000);
        motorReport1.setQuantity(1);

        //When
        // Simular el método getRepairs() del servicio
        when(repairService.getRepairs()).thenReturn(repairs);

        // Llamado de vehiculos

        // Llamar al método makeReport()
        when(motorReportRepository.findByReparationTypeAndMotor(anyInt(), anyString())).thenReturn(motorReport1);
        List<MotorReportEntity> motorReports = motorReportService.makeReport();

        when(motorReportRepository.orderByTotalAmount()).thenReturn(motorReports);

        //Then
        assertNotNull(motorReports);
        assertThat(motorReports.size()).isEqualTo(0);
    }

    @Test
    void whenGetMotorOrdered_thenCorrect() {
        //Given
        MotorReportEntity motorReport1 = new MotorReportEntity();
        MotorReportEntity motorReport2 = new MotorReportEntity();

        ArrayList<MotorReportEntity> motorReports = new ArrayList<>();
        motorReports.add(motorReport1);
        motorReports.add(motorReport2);

        //When
        when(motorReportRepository.orderByTotalAmount()).thenReturn(motorReports);
        List<MotorReportEntity> motorReportsExample = motorReportService.getMotorOrdered();

        //Then
        assertNotNull(motorReports);
        assertThat(motorReportsExample.size()).isEqualTo(2);
    }
}
