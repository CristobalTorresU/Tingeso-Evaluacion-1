package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TimeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

@Service
public class TimeReportService {
    @Autowired
    TimeReportRepository timeReportRepository;
    @Autowired
    RepairService repairService;
    @Autowired
    VehicleService vehicleService;

    public ArrayList<TimeReportEntity> getTimeReports() {
        return (ArrayList<TimeReportEntity>) timeReportRepository.findAll();
    }

    public TimeReportEntity getTimeReportById(Long id) {
        return timeReportRepository.findById(id).get();
    }

    public TimeReportEntity updateTimeReport(TimeReportEntity timeReport) {
        return timeReportRepository.save(timeReport);
    }

    public void makeBlankReport() {
        // Metodo que borra el reporte anterior
        timeReportRepository.deleteAll();

        // Se buscan todas las marcas en la base de datos.
        List<String> brands = vehicleService.vehicleRepository.findBrands();

        // Genera cada combinacion
        for (int i = 0 ; i < brands.size() ; i++) {
                TimeReportEntity report = new TimeReportEntity();
                report.setQuantity(0);
                report.setBrand(brands.get(i));
                // TODO: Setear el tiempo promedio a 0.
                report.setAverageTime(Duration.ofDays(0).plusHours(0).plusMinutes(0).plusSeconds(0));
                // TODO: Setear la suma del tiempo a 0.
                report.setSumTime(Duration.ofDays(0).plusHours(0).plusMinutes(0).plusSeconds(0));

                timeReportRepository.save(report);
        }
    }

    public List<TimeReportEntity> makeReport() {
        // Se traen todas las reparaciones
        List<RepairEntity> repairs = repairService.getRepairs();

        // Se revisan todas y se asignan a algun motorReport
        for (RepairEntity repair : repairs) {
            TimeReportEntity report = timeReportRepository.findByBrand(vehicleService.getVehicleByPlate(repair.getPlate()).getBrand());
            report.setQuantity(report.getQuantity() + 1);

            // Obtiene el tiempo que demoro la reparacion.
            Duration duration = Duration.between(repair.getCheckinDate().atTime(repair.getCheckinHour()),
                    repair.getExitDate().atTime(repair.getExitHour()));
            report.setSumTime(report.getSumTime().plus(duration));

            // TODO: Calcular el promedio.
            report.setAverageTime(report.getSumTime().dividedBy(report.getQuantity()));

            timeReportRepository.save(report);
        }

        List<TimeReportEntity> timeReports = timeReportRepository.orderByTime();

        return timeReports;
    }
}
