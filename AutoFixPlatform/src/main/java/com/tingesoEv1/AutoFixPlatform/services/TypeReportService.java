package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TypeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeReportService {
    @Autowired
    TypeReportRepository typeReportRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    RepairService repairService;

    public ArrayList<TypeReportEntity> getTypeReports() {
        return (ArrayList<TypeReportEntity>) typeReportRepository.findAll();
    }

    public TypeReportEntity getTypeReportById(Long id) {
        return typeReportRepository.findById(id).get();
    }

    public TypeReportEntity saveTypeReport(TypeReportEntity typeReport) {
        return typeReportRepository.save(typeReport);
    }

    public TypeReportEntity updateTypeReport(TypeReportEntity typeReport) {
        return typeReportRepository.save(typeReport);
    }

    public Boolean makeBlankReport() {
        // Metodo que borra el reporte anterior

        String[] types = {"Sedán","Hatchback","SUV","Pickup","Furgoneta"};

        // Genera cada combinacion
        for (int i = 1 ; i <= 11 ; i++) {
            for (int j = 0 ; j < 5 ; j++) {
                TypeReportEntity report = new TypeReportEntity();
                report.setReparationType(i);
                report.setType(types[j]);
                report.setQuantity(0);
                report.setTotalAmount(0);

                typeReportRepository.save(report);
            }
        }

        return true;
    }

    public List<TypeReportEntity> makeReport() {
        // Se traen todas las reparaciones
        List<RepairEntity> repairs = repairService.getRepairs();

        // Se revisan todas y se asignan a algun typeReport
        for (RepairEntity repair : repairs) {
            TypeReportEntity report = typeReportRepository.findByReparationTypeAndType(repair.getReparationType(),vehicleService.getVehicleByPlate(repair.getPlate()).getType());
            report.setQuantity(report.getQuantity() + 1);
            report.setTotalAmount(report.getTotalAmount() + repair.getTotalAmount());
            typeReportRepository.save(report);
        }

        List<TypeReportEntity> typeReports = typeReportRepository.orderByTotalAmount();

        return typeReports;
    }
}
