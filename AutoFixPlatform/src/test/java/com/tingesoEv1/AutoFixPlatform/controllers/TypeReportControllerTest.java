package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
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

    @Test
    public void listTypeReports_ShouldReturnTypeReports() throws Exception {
        TypeReportEntity typeReport1 = new TypeReportEntity(1L,
                1,
                "Reparaciones del Sistema de Frenos",
                3,
                "SUV",
                485000);

        TypeReportEntity typeReport2 = new TypeReportEntity(2L,
                4,
                "Reparaciones de la Transmisión",
                2,
                "Pickup",
                334000);

        List<TypeReportEntity> typeReportList = new ArrayList<>(Arrays.asList(typeReport1, typeReport2));

        given(typeReportService.getTypeReports()).willReturn((ArrayList<TypeReportEntity>) typeReportList);

        mockMvc.perform(get("/api/typereports/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].type", is("SUV")))
                .andExpect(jsonPath("$[1].type", is("Pickup")));
    }

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

        given(typeReportService.getTypeOrdered()).willReturn(typeReportList);

        mockMvc.perform(get("/api/typereports/ordered"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].repairName", is("Servicio del Sistema de Refrigeración")))
                .andExpect(jsonPath("$[1].repairName", is("Reparaciones de la Transmisión")))
                .andExpect(jsonPath("$[2].repairName", is("Reparaciones del Motor")));
    }

    @Test
    public void bringTypeReports_ShouldReturnTypeReports() throws Exception {
        TypeReportEntity typeReport1 = new TypeReportEntity(1L,
                1,
                "Reparaciones del Sistema de Frenos",
                3,
                "SUV",
                485000);

        TypeReportEntity typeReport2 = new TypeReportEntity(2L,
                4,
                "Reparaciones de la Transmisión",
                2,
                "Pickup",
                334000);

        List<TypeReportEntity> typeReportList = new ArrayList<>(Arrays.asList(typeReport1, typeReport2));

        given(typeReportService.makeReport()).willReturn(typeReportList);

        mockMvc.perform(get("/api/typereports/generate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].type", is("SUV")))
                .andExpect(jsonPath("$[1].type", is("Pickup")));
    }
}
