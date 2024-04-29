package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TypeReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebMvcTest(TypeReportService.class)
public class TypeReportServiceTest {

    @Autowired
    private TypeReportService typeReportService;

    @MockBean
    private RepairService repairService;

    @MockBean
    private VehicleService vehicleService;

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

    @Test
    void whenMakeBlankTypeReport_thenCorrect() {
        //Given
        // Simular el método deleteAll() del repositorio
        doNothing().when(typeReportRepository).deleteAll();

        // Llamar al método makeBlankReport()
        typeReportService.makeBlankReport();

        // Verificar que el método deleteAll() fue llamado una vez
        verify(typeReportRepository, times(1)).deleteAll();
    }

    @Test
    void whenMakeTypeReport_thenCorrect() {
        //Given
        RepairService repairService = mock(RepairService.class);

        RepairEntity repair1 = new RepairEntity();
        RepairEntity repair2 = new RepairEntity();
        repair1.setPlate("AAAA11");
        repair1.setReparationType(1);
        repair1.setTotalAmount(1000);
        repair2.setPlate("BBBB22");
        repair2.setReparationType(2);
        repair2.setTotalAmount(2000);
        ArrayList<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));

        TypeReportEntity typeReport1 = new TypeReportEntity();
        TypeReportEntity typeReport2 = new TypeReportEntity();
        typeReport1.setReparationType(1);
        typeReport1.setType("SUV");
        typeReport1.setTotalAmount(1000);
        typeReport1.setQuantity(1);
        typeReport2.setReparationType(2);
        typeReport2.setType("Sedán");
        typeReport2.setTotalAmount(2000);
        typeReport2.setQuantity(1);

        //When
        // Simular el método getRepairs() del servicio
        when(repairService.getRepairs()).thenReturn(repairs);

        // Llamado de vehiculos

        // Llamar al método makeReport()
        when(typeReportRepository.findByReparationTypeAndType(anyInt(), anyString())).thenReturn(typeReport1);
        List<TypeReportEntity> motorReports = typeReportService.makeReport();

        when(typeReportRepository.orderByTotalAmount()).thenReturn(motorReports);

        //Then
        assertNotNull(motorReports);
        assertThat(motorReports.size()).isEqualTo(0);
    }

    @Test
    void whenGetTypeOrdered_thenCorrect() {
        //Given
        TypeReportEntity typeReport1 = new TypeReportEntity();
        TypeReportEntity typeReport2 = new TypeReportEntity();

        ArrayList<TypeReportEntity> typeReports = new ArrayList<>();
        typeReports.add(typeReport1);
        typeReports.add(typeReport2);

        //When
        when(typeReportRepository.orderByTotalAmount()).thenReturn(typeReports);
        List<TypeReportEntity> motorReportsExample = typeReportService.getTypeOrdered();

        //Then
        assertNotNull(typeReports);
        assertThat(motorReportsExample.size()).isEqualTo(2);
    }
}
