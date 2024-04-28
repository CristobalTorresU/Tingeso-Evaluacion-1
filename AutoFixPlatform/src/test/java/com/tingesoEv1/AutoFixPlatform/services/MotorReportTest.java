package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.MotorReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MotorReportTest {

    @Autowired
    private MotorReportService motorReportService;

    @Autowired
    private RepairService repairService;

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

    /*
    // TODO: Implementar el siguiente test
    @Test
    void whenMakeMotorReport_thenCorrect() {
        //Given
        // Simular el método getRepairs() del servicio
        when(repairService.getRepairs()).thenReturn(null);

        // Llamar al método makeReport()
        motorReportService.makeReport();

        // Verificar que el método getRepairs() fue llamado una vez
        verify(repairService, times(1)).getRepairs();
    }
     */
}
