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

        mockMvc.perform(get("/repairs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].plate", is("AAAA11")))
                .andExpect(jsonPath("$[1].plate", is("BBBB11")));
    }
}
