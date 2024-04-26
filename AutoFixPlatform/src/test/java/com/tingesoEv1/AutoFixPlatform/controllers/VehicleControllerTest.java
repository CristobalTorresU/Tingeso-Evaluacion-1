package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.services.VehicleService;
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

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    public void listVehicles_ShouldReturnVehicles() throws Exception {
        VehicleEntity vehicle1 = new VehicleEntity(1L,
                "AAAA11",
                "Ford",
                8402,
                "America",
                "Pickup",
                2014,
                "Gasolina",
                6);

        VehicleEntity vehicle2 = new VehicleEntity(2L,
                "BBBB11",
                "Nissan",
                16888,
                "V16",
                "Sedán",
                2004,
                "Gasolina",
                4);

        List<VehicleEntity> vehicleList = new ArrayList<>(Arrays.asList(vehicle1, vehicle2));

        given(vehicleService.getVehicles()).willReturn((ArrayList<VehicleEntity>) vehicleList);

        mockMvc.perform(get("/vehicles/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("America")))
                .andExpect(jsonPath("$[1].model", is("V16")));
    }

    @Test
    public void getVehicleById_ShouldReturnVehicle() throws Exception {
        VehicleEntity vehicle = new VehicleEntity(1L,
                "CCCC24",
                "Toyota",
                42000,
                "Corola",
                "Pickup",
                1996,
                "Gasolina",
                6);

        given(vehicleService.getVehicleById(1L)).willReturn(vehicle);

        mockMvc.perform(get("/vehicles/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate", is("CCCC24")));
    }

    @Test
    public void saveVehicle_ShouldReturnSavedVehicle() throws Exception {
        //EmployeeEntity employeeToSave = new EmployeeEntity(null, "12345678-9", "New Employee", 40000, 0, "B");
        VehicleEntity savedVehicle = new VehicleEntity(1L,
                "DDDD44",
                "Kia",
                5800,
                "Motorsport",
                "SUV",
                2011,
                "Gasolina",
                4);

        given(vehicleService.saveVehicle(Mockito.any(VehicleEntity.class))).willReturn(savedVehicle);

        String vehicleJson = """
            {
                "plate": "DDDD44",
                "brand": "Kia",
                "mileage": 5800,
                "model": "Motorsport",
                "type": "SUV",
                "year": 2011,
                "motor": "Gasolina",
                "seats": 4
            }
            """;

        mockMvc.perform(post("/vehicles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Kia")));
    }

    @Test
    public void updateVehicle_ShouldReturnUpdatedVehicle() throws Exception {
        VehicleEntity updatedVehicle = new VehicleEntity(1L,
                "ABCD12",
                "Ferrari",
                400,
                "Caballo",
                "Hatchback",
                2016,
                "Gasolina",
                2);

        given(vehicleService.updateVehicle(Mockito.any(VehicleEntity.class))).willReturn(updatedVehicle);

        String vehicleJson = """
            {
                "id": 1,
                "plate": "ABCD12",
                "brand": "Ferrari",
                "mileage": 400,
                "model": "Caballo",
                "type": "Hatchback",
                "year": 2016,
                "motor": "Gasolina",
                "seats": 2
            }
            """;


        mockMvc.perform(put("/vehicles/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("Caballo")));
    }

    @Test
    public void deleteVehicleById_ShouldReturn204() throws Exception {
        when(vehicleService.deleteVehicle(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/vehicles/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
