package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.DetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DetailController.class)
public class DetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailService detailService;

    @Test
    public void listDetails_ShouldReturnDetails() throws Exception {
        DetailEntity detail1 = new DetailEntity(1L,
                180000,
                12000,
                1000,
                75000,
                40000,
                12000,
                13000,
                19000,
                1L);

        DetailEntity detail2 = new DetailEntity(2L,
                700000,
                50000,
                10000,
                50000,
                1000,
                1321,
                3654,
                198723,
                2L);

        List<DetailEntity> detailList = new ArrayList<>(Arrays.asList(detail1, detail2));

        given(detailService.getDetails()).willReturn((ArrayList<DetailEntity>) detailList);

        mockMvc.perform(get("/details/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].lateRecharge", is(13000)))
                .andExpect(jsonPath("$[1].lateRecharge", is(3654)));
    }

    @Test
    public void getDetailById_ShouldReturnDetail() throws Exception {

        DetailEntity detail = new DetailEntity(1L,
                180070,
                12600,
                1006,
                75700,
                48000,
                12080,
                13009,
                19004,
                1L);

        List<DetailEntity> detailList = new ArrayList<>(Arrays.asList(detail));

        given(detailService.getRepairById(1L)).willReturn((ArrayList<DetailEntity>) detailList);

        mockMvc.perform(get("/details/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lateRecharge", is(13009)));
    }
}
