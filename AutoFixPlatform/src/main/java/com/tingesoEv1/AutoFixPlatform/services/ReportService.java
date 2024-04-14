package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.RepairRepository;
import com.tingesoEv1.AutoFixPlatform.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ReportService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    /*
    public List<int[][]> getRepairTypeReport() {
        // Obtiene los datos por tipo de reparación.
        // TODO: Podría hacerse mediante guardar los valores directamente en repairs.
        int[][] count = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};
        int[][] prices = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        for (int i = 1 ; i <= 11 ; i++) {
            List<RepairEntity> repairs = repairRepository.findByReparationType(i);
            for (int j = 0 ; j < repairs.size() ; j++) {
                String vehicleType = vehicleRepository.findByPlate(repairs.get(j).getPlate()).getType();
                switch (vehicleType) {
                    case "Sedán":
                        count[0][i - 1] += 1;
                        prices[0][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Hatchback":
                        count[1][i - 1] += 1;
                        prices[1][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "SUV":
                        count[2][i - 1] += 1;
                        prices[2][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Pickup":
                        count[3][i - 1] += 1;
                        prices[3][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Furgoneta":
                        count[4][i - 1] += 1;
                        prices[4][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                }
            }
        }

        List<int[][]> data = new ArrayList<>();
        data.add(count);
        data.add(prices);

        return data;
    }

    // TODO: Encontrar cada una de las marcas. (ayuda)
    public List<int[][]> getBrandTimeReport() {
        int[][] count = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};
        int[][] prices = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};
    }

    public List<int[][]> get() {
        int[][] count = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};
        int[][] prices = {{0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        for (int i = 1 ; i <= 11 ; i++) {
            List<RepairEntity> repairs = repairRepository.findByReparationType(i);
            for (int j = 0 ; j < repairs.size() ; j++) {
                String vehicleMotor = vehicleRepository.findByPlate(repairs.get(j).getPlate()).getMotor();
                switch (vehicleMotor) {
                    case "Gasolina":
                        count[0][i - 1] += 1;
                        prices[0][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Diésel":
                        count[1][i - 1] += 1;
                        prices[1][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Híbrido":
                        count[2][i - 1] += 1;
                        prices[2][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                    case "Eléctrico":
                        count[3][i - 1] += 1;
                        prices[3][i - 1] += repairs.get(j).getTotalAmount();
                        break;
                }
            }
        }
    }
     */
}
