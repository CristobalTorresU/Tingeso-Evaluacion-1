package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TypeReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TypeReportServiceTest {

    @Autowired
    private TypeReportService typeReportService;

    @MockBean
    private TypeReportRepository typeReportRepository;

    @Test
    void whenGetTypes_thenCorrect() {
        //Given
        TypeReportEntity motorReport1 = new TypeReportEntity();
        TypeReportEntity motorReport2 = new TypeReportEntity();

        ArrayList<TypeReportEntity> motorReports = new ArrayList<>();
        motorReports.add(motorReport1);
        motorReports.add(motorReport2);

        //When
        when(typeReportRepository.findAll()).thenReturn(motorReports);
        ArrayList<TypeReportEntity> motorReportsExample = typeReportService.getTypeReports();

        //Then
        assertNotNull(motorReports);
        assertThat(motorReportsExample.size()).isEqualTo(2);
    }

    /*
    @Test
    void whenMakeReport_thenCorrect() {
        //Given
        // Simular el método deleteAll() del repositorio
        doNothing().when(typeReportRepository).deleteAll();

        // Llamar al método makeBlankReport()
        typeReportService.makeReport();

        // Verificar que el método deleteAll() fue llamado una vez
        verify(typeReportRepository, times(1)).deleteAll();
    }
     */

    @Test
    void whenGetTypeOrdered_thenCorrect() {
        //Given
        TypeReportEntity typeReport1 = new TypeReportEntity();
        TypeReportEntity typeReport2 = new TypeReportEntity();
        TypeReportEntity typeReport3 = new TypeReportEntity();
        TypeReportEntity typeReport4 = new TypeReportEntity();
        TypeReportEntity typeReport5 = new TypeReportEntity();

        typeReport1.setTotalAmount(1000);
        typeReport2.setTotalAmount(2000);
        typeReport3.setTotalAmount(3000);
        typeReport4.setTotalAmount(4000);
        typeReport5.setTotalAmount(5000);

        ArrayList<TypeReportEntity> typeReports = new ArrayList<>();
        typeReports.add(typeReport1);
        typeReports.add(typeReport2);
        typeReports.add(typeReport3);
        typeReports.add(typeReport4);
        typeReports.add(typeReport5);

        //When
        when(typeReportRepository.orderByTotalAmount()).thenReturn(typeReports);
        ArrayList<TypeReportEntity> typeReportsExample = typeReportService.getTypeReports();

        //Then
        assertNotNull(typeReports);
        assertThat(typeReportsExample.size()).isEqualTo(5);
    }
}
