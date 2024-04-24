package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.MotorReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorReportService {
    @Autowired
    MotorReportRepository motorReportRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    RepairService repairService;

    public ArrayList<MotorReportEntity> getMotorReports() {
        return (ArrayList<MotorReportEntity>) motorReportRepository.findAll();
    }

    public MotorReportEntity getMotorReportById(Long id) {
        return motorReportRepository.findById(id).get();
    }

    public MotorReportEntity updateMotorReport(MotorReportEntity motorReport) {
        return motorReportRepository.save(motorReport);
    }

    public Boolean makeBlankReport() {
        // Metodo que borra el reporte anterior

        String[] types = {"Gasolina","Diésel","Híbrido","Eléctrico"};

        // Genera cada combinacion
        for (int i = 1 ; i <= 11 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                MotorReportEntity report = new MotorReportEntity();
                report.setReparationType(i);
                report.setMotor(types[j]);
                report.setQuantity(0);
                report.setTotalAmount(0);

                motorReportRepository.save(report);
            }
        }

        return true;
    }

    public List<MotorReportEntity> makeReport() {
        // Se traen todas las reparaciones
        List<RepairEntity> repairs = repairService.getRepairs();

        // Se revisan todas y se asignan a algun motorReport
        for (RepairEntity repair : repairs) {
            MotorReportEntity report = motorReportRepository.findByReparationTypeAndMotor(repair.getReparationType(),vehicleService.getVehicleByPlate(repair.getPlate()).getMotor());
            report.setQuantity(report.getQuantity() + 1);
            report.setTotalAmount(report.getTotalAmount() + repair.getTotalAmount());
            motorReportRepository.save(report);
        }

        List<MotorReportEntity> motorReports = motorReportRepository.orderByTotalAmount();

        return motorReports;
    }
}
