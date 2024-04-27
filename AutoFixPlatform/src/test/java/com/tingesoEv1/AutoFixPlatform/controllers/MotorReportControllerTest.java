package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.MotorReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@WebMvcTest(MotorReportController.class)
public class MotorReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MotorReportService motorReportService;

    @Test
    public void listMotorReports_ShouldReturnMotorReports() throws Exception {
        MotorReportEntity motorReport1 = new MotorReportEntity(1L,
                1,
                "Reparaciones del Sistema de Frenos",
                3,
                "Híbrido",
                485000);

        MotorReportEntity motorReport2 = new MotorReportEntity(2L,
                4,
                "Reparaciones de la Transmisión",
                2,
                "Gasolina",
                334000);

        List<MotorReportEntity> motorReportList = new ArrayList<>(Arrays.asList(motorReport1, motorReport2));

        given(motorReportService.getMotorReports()).willReturn((ArrayList<MotorReportEntity>) motorReportList);

        mockMvc.perform(get("/motorreports/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].motor", is("Híbrido")))
                .andExpect(jsonPath("$[1].motor", is("Gasolina")));
    }

    @Test
    public void getMotorReportById_ShouldReturnMotorReport() throws Exception {
        MotorReportEntity motorReport = new MotorReportEntity(1L,
                2,
                "Servicio del Sistema de Refrigeración",
                5,
                "Diésel",
                120000);

        given(motorReportService.getMotorReportById(1L)).willReturn(motorReport);

        mockMvc.perform(get("/motorreports/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.motor", is("Diésel")));
    }

    @Test
    public void bringMotorReports_ShouldReturnGeneratedMotorReports() throws Exception {

        /*
        List

        given(motorReportService.makeBlankReport()).willReturn((ArrayList<MotorReportEntity>) motorReportList);
         */
    }

    @Test
    public void orderedMotorReports_ShouldReturnOrderedMotorReports() throws Exception {
    }
}
