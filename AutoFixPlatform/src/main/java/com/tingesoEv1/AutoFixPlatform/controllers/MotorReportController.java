package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.MotorReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.MotorReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorreports")
@CrossOrigin("*")
public class MotorReportController {
    @Autowired
    MotorReportService motorReportService;

    @GetMapping("/")
    public ResponseEntity<List<MotorReportEntity>> listMotorReports() {
        List<MotorReportEntity> motorReports = motorReportService.getMotorReports();
        return ResponseEntity.ok(motorReports);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<MotorReportEntity>> bringMotorReports() {
        motorReportService.makeBlankReport();
        List<MotorReportEntity> reports = motorReportService.makeReport();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<MotorReportEntity>> orderedMotorReports() {
        List<MotorReportEntity> reports = motorReportService.getMotorOrdered();
        return ResponseEntity.ok(reports);
    }
}
