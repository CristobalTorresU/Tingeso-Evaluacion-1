package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/details")
@CrossOrigin("*")
public class DetailController {
    @Autowired
    DetailService detailService;

    @GetMapping("/")
    public ResponseEntity<List<DetailEntity>> listDetails() {
        List<DetailEntity> details = detailService.getDetails();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DetailEntity>> getDetailById(@PathVariable Long id) {
        List<DetailEntity> detail = detailService.getRepairById(id);
        return ResponseEntity.ok(detail);
    }
}
