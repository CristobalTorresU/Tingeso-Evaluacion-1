package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.TimeReportEntity;
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

    @GetMapping("/generate")
    public ResponseEntity<List<TypeReportEntity>> bringTypeReports() {
        List<TypeReportEntity> reports = typeReportService.makeReport();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<TypeReportEntity>> orderedTypeReports() {
        List<TypeReportEntity> reports = typeReportService.getTypeOrdered();
        return ResponseEntity.ok(reports);
    }
}
