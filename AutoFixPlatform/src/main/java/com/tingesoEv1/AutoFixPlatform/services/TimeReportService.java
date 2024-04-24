package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TimeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeReportService {
    @Autowired
    TimeReportRepository timeReportRepository;
    @Autowired
    RepairService repairService;

    public ArrayList<TimeReportEntity> getTimeReports() {
        return (ArrayList<TimeReportEntity>) timeReportRepository.findAll();
    }

    public TimeReportEntity getTimeReportById(Long id) {
        return timeReportRepository.findById(id).get();
    }

    public TimeReportEntity updateTimeReport(TimeReportEntity timeReport) {
        return timeReportRepository.save(timeReport);
    }

    public Boolean makeBlankReport() {
        // Metodo que borra el reporte anterior

        // Genera cada combinacion
        for (int i = 1 ; i <= 11 ; i++) {
                TimeReportEntity report = new TimeReportEntity();
                report.setReparationType(i);
                report.setQuantity(0);
                // TODO: Entender como funciona con LocalTime

                timeReportRepository.save(report);
        }

        return true;
    }

    public List<TimeReportEntity> makeReport() {
        // Se traen todas las reparaciones
        List<RepairEntity> repairs = repairService.getRepairs();

        // Se revisan todas y se asignan a algun motorReport
        for (RepairEntity repair : repairs) {
            TimeReportEntity report = timeReportRepository.findByReparationType(repair.getReparationType());
            report.setQuantity(report.getQuantity() + 1);
            //report.setSumTime(report.getSumTime() + repair.getTotalAmount());
            timeReportRepository.save(report);
        }

        List<TimeReportEntity> timeReports = timeReportRepository.orderByTime();

        return timeReports;
    }
}
