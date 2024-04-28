package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TimeReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TimeReportServiceTest {

    @Autowired
    private TimeReportService timeReportService;

    @MockBean
    private TimeReportRepository timeReportRepository;

    @Test
    void whenGetTimeReports_thenCorrect() {
        //Given
        TimeReportEntity timeReport1 = new TimeReportEntity();
        TimeReportEntity timeReport2 = new TimeReportEntity();

        ArrayList<TimeReportEntity> timeReports = new ArrayList<>();
        timeReports.add(timeReport1);
        timeReports.add(timeReport2);

        //When
        when(timeReportRepository.findAll()).thenReturn(timeReports);
        ArrayList<TimeReportEntity> timeReportsExample = timeReportService.getTimeReports();

        //Then
        assertNotNull(timeReports);
        assertThat(timeReportsExample.size()).isEqualTo(2);
    }

    @Test
    void whenMakeBlankTimeReport_thenCorrect() {
        //Given
        // Simular el método deleteAll() del repositorio
        doNothing().when(timeReportRepository).deleteAll();

        // Llamar al método makeBlankReport()
        timeReportService.makeBlankReport();

        // Verificar que el método deleteAll() fue llamado una vez
        verify(timeReportRepository, times(1)).deleteAll();
    }

    @Test
    void whenMakeTimeReport_thenCorrect() {
        //Given
        TimeReportEntity timeReport1 = new TimeReportEntity();
        timeReport1.setBrand("Toyota");
        timeReport1.setQuantity(1);
        timeReport1.setTotalSeconds(3600L);
        timeReport1.setHours(1L);
        timeReport1.setMinutes(0);
        timeReport1.setSeconds(0);
        timeReport1.setSumTime(java.time.Duration.ofDays(0).plusHours(1).plusMinutes(0).plusSeconds(0));
        TimeReportEntity timeReport2 = new TimeReportEntity();

        ArrayList<TimeReportEntity> timeReports = new ArrayList<>();
        timeReports.add(timeReport1);
        timeReports.add(timeReport2);

        //When
        when(timeReportRepository.findByBrand(anyString())).thenReturn(timeReport1);
        List<TimeReportEntity> timeReportsExample = timeReportService.makeReport();

        //Then
        assertNotNull(timeReports);
        assertThat(timeReportsExample.size()).isEqualTo(0);
    }

    @Test
    void whenGetTimeOrdered_thenCorrect() {
        //Given
        TimeReportEntity timeReport1 = new TimeReportEntity();
        TimeReportEntity timeReport2 = new TimeReportEntity();

        ArrayList<TimeReportEntity> timeReports = new ArrayList<>();
        timeReports.add(timeReport1);
        timeReports.add(timeReport2);

        //When
        when(timeReportRepository.orderByTime()).thenReturn(timeReports);
        List<TimeReportEntity> motorReportsExample = timeReportService.getTimeOrdered();

        //Then
        assertNotNull(timeReports);
        assertThat(motorReportsExample.size()).isEqualTo(2);
    }
}
