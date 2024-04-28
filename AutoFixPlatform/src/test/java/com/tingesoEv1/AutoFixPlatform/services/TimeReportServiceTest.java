package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TimeReportRepository;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

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
}
