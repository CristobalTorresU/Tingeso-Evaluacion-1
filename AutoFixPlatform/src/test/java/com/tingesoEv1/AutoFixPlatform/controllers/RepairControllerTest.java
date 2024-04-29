package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.services.RepairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RepairController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairService repairService;

    @Test
    public void listRepairs_ShouldReturnRepairs() throws Exception {
        RepairEntity repair1 = new RepairEntity(1L,
                "AAAA11",
                LocalDate.parse("2024-04-12"),
                LocalTime.parse("11:00:00"),
                4,
                400000,
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("11:00:00"),
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("13:00:00"));

        RepairEntity repair2 = new RepairEntity(2L,
                "BBBB11",
                LocalDate.parse("2024-04-12"),
                LocalTime.parse("10:00:00"),
                5,
                320000,
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("11:00:00"),
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("12:00:00"));

        List<RepairEntity> repairList = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getRepairs()).willReturn((ArrayList<RepairEntity>) repairList);

        mockMvc.perform(get("/api/repairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("AAAA11")))
                .andExpect(jsonPath("$[1].plate", is("BBBB11")));
    }

    @Test
    public void getRepairById_ShouldReturnRepair() throws Exception {
        RepairEntity repair = new RepairEntity(1L,
                "AAAA11",
                LocalDate.parse("2024-04-12"),
                LocalTime.parse("11:00:00"),
                4,
                400000,
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("11:00:00"),
                LocalDate.parse("2024-04-13"),
                LocalTime.parse("13:00:00"));

        given(repairService.getRepairById(1L)).willReturn(repair);

        mockMvc.perform(get("/api/repairs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate", is("AAAA11")));
    }

    @Test
    public void deleteRepair_ShouldReturnNoContent() throws Exception {
        given(repairService.deleteRepair(1L)).willReturn(true);

        mockMvc.perform(delete("/api/repairs/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void calculatePrice_ShouldReturnNoContent() throws Exception {
        given(repairService.calculatePrice("AAAA11",
                "2024-04-12",
                "11:00:00",
                4,
                "2024-04-13",
                "11:00:00",
                "2024-04-13",
                "13:00:00")).willReturn(true);

        mockMvc.perform(get("/api/repairs/calculate?plate=AAAA11&checkinDate=2024-04-12&checkinHour=11:00:00&reparationType=4&exitDate=2024-04-13&exitHour=11:00:00&collectDate=2024-04-13&collectHour=13:00:00"))
                .andExpect(status().isNoContent());
    }
}
