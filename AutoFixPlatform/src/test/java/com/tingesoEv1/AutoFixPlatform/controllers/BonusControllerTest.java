package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.services.BonusService;
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

@WebMvcTest(BonusController.class)
public class BonusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BonusService bonusService;

    @Test
    public void listBonus_ShouldReturnBonus() throws Exception {
        BonusEntity bonus1 = new BonusEntity(1L,
                "Toyota",
                20000,
                7);

        BonusEntity bonus2 = new BonusEntity(2L,
                "Hyundai",
                13000,
                2);

        List<BonusEntity> vehicleList = new ArrayList<>(Arrays.asList(bonus1, bonus2));

        given(bonusService.getBonuses()).willReturn((ArrayList<BonusEntity>) vehicleList);

        mockMvc.perform(get("/api/bonuses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[1].brand", is("Hyundai")));
    }

    @Test
    public void getBonusById_ShouldReturnBonus() throws Exception {
        BonusEntity bonus = new BonusEntity(1L,
                "Kia",
                12000,
                6);

        given(bonusService.getBonusById(1L)).willReturn(bonus);

        mockMvc.perform(get("/api/bonuses/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand", is("Kia")));
    }

    @Test
    public void saveBonus_ShouldReturnSavedBonus() throws Exception {
        BonusEntity savedBonus = new BonusEntity(1L,
                "Ford",
                60000,
                4);

        given(bonusService.saveBonus(Mockito.any(BonusEntity.class))).willReturn(savedBonus);

        String bonusJson = """
            {
                "brand": "Ford",
                "mileage": 60000,
                "seats": 4
            }
            """;

        mockMvc.perform(post("/api/bonuses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bonusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Ford")));
    }

    @Test
    public void updateBonus_ShouldReturnUpdatedBonus() throws Exception {
        BonusEntity updatedBonus = new BonusEntity(1L,
                "Ferrari",
                15000,
                2);

        given(bonusService.updateBonus(Mockito.any(BonusEntity.class))).willReturn(updatedBonus);

        String bonusJson = """
            {
                "id": 1,
                "brand": "Ferrari",
                "mileage": 15000,
                "seats": 2
            }
            """;


        mockMvc.perform(put("/api/bonuses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bonusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Ferrari")));
    }

    @Test
    public void deleteBonusById_ShouldReturn204() throws Exception {
        when(bonusService.deleteBonus(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/bonuses/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
