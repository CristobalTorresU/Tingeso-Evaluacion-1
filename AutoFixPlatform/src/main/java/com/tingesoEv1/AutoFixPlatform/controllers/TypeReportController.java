package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.services.TypeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typereports")
@CrossOrigin("*")
public class TypeReportController {
    @Autowired
    TypeReportService typeReportService;

    @GetMapping("/")
    public ResponseEntity<List<TypeReportEntity>> listTypeReports() {
        List<TypeReportEntity> typeReports = typeReportService.getTypeReports();
        return ResponseEntity.ok(typeReports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReportEntity> getTypeReportById(@PathVariable Long id) {
        TypeReportEntity typeReport = typeReportService.getTypeReportById(id);
        return ResponseEntity.ok(typeReport);
    }

    @PutMapping("/")
    public ResponseEntity<TypeReportEntity> updateTypeReport(@RequestBody TypeReportEntity typeReport) {
        TypeReportEntity typeReportUpdated = typeReportService.updateTypeReport(typeReport);
        return ResponseEntity.ok(typeReportUpdated);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<TypeReportEntity>> bringTypeReports() {
        typeReportService.makeBlankReport();
        List<TypeReportEntity> reports = typeReportService.makeReport();
        return ResponseEntity.ok(reports);
    }
}
