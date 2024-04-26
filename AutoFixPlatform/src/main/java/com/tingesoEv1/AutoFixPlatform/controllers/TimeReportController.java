package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.TimeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timereports")
@CrossOrigin("*")
public class TimeReportController {
    @Autowired
    TimeReportService timeReportService;

    @GetMapping("/")
    public ResponseEntity<List<TimeReportEntity>> listTimeReports() {
        List<TimeReportEntity> timeReports = timeReportService.getTimeReports();
        return ResponseEntity.ok(timeReports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeReportEntity> getTimeReportById(@PathVariable Long id) {
        TimeReportEntity timeReport = timeReportService.getTimeReportById(id);
        return ResponseEntity.ok(timeReport);
    }

    @PutMapping("/")
    public ResponseEntity<TimeReportEntity> updateTimeReport(@RequestBody TimeReportEntity timeReport) {
        TimeReportEntity timeReportUpdated = timeReportService.updateTimeReport(timeReport);
        return ResponseEntity.ok(timeReportUpdated);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<TimeReportEntity>> bringTimeReports() {
        timeReportService.makeBlankReport();
        List<TimeReportEntity> reports = timeReportService.makeReport();
        return ResponseEntity.ok(reports);
    }
}
