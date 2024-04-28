package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.TimeReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TimeReportController.class)
public class TimeReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeReportService timeReportService;

    @Test
    public void listTimeReports_ShouldReturnTimeReports() throws Exception {
        TimeReportEntity timeReport1 = new TimeReportEntity(1L,
                "Toyota",
                2,
                Duration.ofHours(2L),
                1L,
                48,
                34,
                12123L);

        TimeReportEntity timeReport2 = new TimeReportEntity(2L,
                "Nissan",
                2,
                Duration.ofHours(1L),
                1L,
                38,
                44,
                12111L);

        List<TimeReportEntity> timeReportList = new ArrayList<>(Arrays.asList(timeReport1, timeReport2));

        given(timeReportService.getTimeReports()).willReturn((ArrayList<TimeReportEntity>) timeReportList);

        mockMvc.perform(get("/timereports/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[1].brand", is("Nissan")));
    }

    @Test
    public void orderedTimeReports_ShouldReturnOrderedTimeReports() throws Exception {
        TimeReportEntity timeReport1 = new TimeReportEntity(1L,
                "Ford",
                2,
                Duration.ofHours(2L),
                1L,
                48,
                34,
                123453L);

        TimeReportEntity timeReport2 = new TimeReportEntity(2L,
                "Hyundai",
                3,
                Duration.ofHours(1L),
                1L,
                38,
                44,
                121231L);

        List<TimeReportEntity> timeReportList = new ArrayList<>(Arrays.asList(timeReport1, timeReport2));

        given(timeReportService.getTimeOrdered()).willReturn(timeReportList);

        mockMvc.perform(get("/timereports/ordered"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Ford")))
                .andExpect(jsonPath("$[1].brand", is("Hyundai")));
    }

    @Test
    public void bringTimeReports_ShouldReturnTimeReports() throws Exception {
        TimeReportEntity timeReport1 = new TimeReportEntity(1L,
                "Toyota",
                2,
                Duration.ofHours(2L),
                1L,
                48,
                34,
                12123L);

        TimeReportEntity timeReport2 = new TimeReportEntity(2L,
                "Nissan",
                2,
                Duration.ofHours(1L),
                1L,
                38,
                44,
                12111L);

        List<TimeReportEntity> timeReportList = new ArrayList<>(Arrays.asList(timeReport1, timeReport2));

        given(timeReportService.makeReport()).willReturn((ArrayList<TimeReportEntity>) timeReportList);

        mockMvc.perform(get("/timereports/generate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[1].brand", is("Nissan")));
    }
}
