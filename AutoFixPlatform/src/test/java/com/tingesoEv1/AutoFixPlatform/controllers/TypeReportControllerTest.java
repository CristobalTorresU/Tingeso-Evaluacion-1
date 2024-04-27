package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.TypeReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TypeReportController.class)
public class TypeReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeReportService typeReportService;

    // TODO: Verificar si en realidad esta ocupando todas las lineas.
    // TODO: Verificar si esta funcionando correctamente.
    @Test
    public void orderedTypeReports_ShouldReturnOrderedTypeReports() throws Exception {
        TypeReportEntity typeReport1 = new TypeReportEntity(1L,
                4,
                "Reparaciones de la Transmisión",
                1,
                "SUV",
                300000);

        TypeReportEntity typeReport2 = new TypeReportEntity(2L,
                3,
                "Reparaciones del Motor",
                2,
                "Hatchback",
                230000);

        TypeReportEntity typeReport3 = new TypeReportEntity(3L,
                2,
                "Servicio del Sistema de Refrigeración",
                1,
                "SUV",
                450000);

        List<TypeReportEntity> typeReportList = new ArrayList<>(Arrays.asList(typeReport3, typeReport1, typeReport2));

        given(typeReportService.getTypeOrdered()).willReturn((ArrayList<TypeReportEntity>) typeReportList);

        mockMvc.perform(get("/typereports/ordered"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].repairName", is("Servicio del Sistema de Refrigeración")))
                .andExpect(jsonPath("$[1].repairName", is("Reparaciones de la Transmisión")))
                .andExpect(jsonPath("$[2].repairName", is("Reparaciones del Motor")));
    }

    @Test
    public void bringTypeReports_ShouldReturnReport() throws Exception {
        /*
        RepairEntity repair1 = new RepairEntity();

        RepairEntity repair2 = new RepairEntity();

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        mockMvc.perform()

         */
    }
}
