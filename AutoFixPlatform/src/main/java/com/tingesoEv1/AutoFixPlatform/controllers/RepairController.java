package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/repairs")
@CrossOrigin("*")
public class RepairController {
    @Autowired
    RepairService repairService;

    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> listRepairs() {
        List<RepairEntity> repairs = repairService.getRepairs();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        RepairEntity repair = repairService.getRepairById(id);
        return ResponseEntity.ok(repair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepairEntity> deleteRepair(@PathVariable Long id) throws Exception {
        var isDeleted = repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/calculate")
    public ResponseEntity<Void> calculatePrice(@RequestBody RepairEntity repair) {
        repairService.calculatePrice(repair.getPlate(), repair.getCheckinDate(), repair.getCheckinHour(),
                repair.getReparationType(), repair.getExitDate(),
                repair.getExitHour(), repair.getCollectDate(), repair.getCollectHour());
        return ResponseEntity.noContent().build();
    }
    */

    @GetMapping("/calculate")
    public ResponseEntity<Void> calculatePrice(@RequestParam("plate") String plate,
                                               @RequestParam("checkinDate") String checkinDate,
                                               @RequestParam("checkinHour") String checkinHour,
                                               @RequestParam("reparationType") int reparationType,
                                               @RequestParam("exitDate") String exitDate,
                                               @RequestParam("exitHour") String exitHour,
                                               @RequestParam("collectDate") String collectDate,
                                               @RequestParam("collectHour") String collectHour) {
        repairService.calculatePrice(plate, checkinDate, checkinHour, reparationType,
                exitDate, exitHour, collectDate, collectHour);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<Void> generateRepairReport(@PathVariable Long id) {
        repairService.generateRepairReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/typereport")
    public ResponseEntity<Void> generateTypeReport() {
        repairService.generateTypeReport();
        return ResponseEntity.noContent().build();
    }

    // TODO: Realizar reporte R3.

    @GetMapping("/motorreport")
    public ResponseEntity<Void> generateMotorReport() {
        repairService.generateMotorReport();
        return ResponseEntity.noContent().build();
    }
}
