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

    /*
    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair) {
        RepairEntity repairNew = repairService.saveRepair(repair);
        return ResponseEntity.ok(repairNew);
    }
     */
    /*
    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repair) {
        RepairEntity repairUpdated = repairService.updateRepair(repair);
        return ResponseEntity.ok(repairUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepairById(@PathVariable Long id) throws Exception {
        var isDeleted = repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
    */

    // TODO: Que esta cosa funcione.
    /*
    @GetMapping("/calculate")
    public ResponseEntity<Void> calculatePrice(@RequestParam("plate") String plate,
                                                 @RequestParam("checkinDate") LocalDate checkinDate,
                                                 @RequestParam("checkinHour") LocalTime checkinHour,
                                                 @RequestParam("reparationType") int reparationType,
                                                 @RequestParam("exitDate") LocalDate exitDate,
                                                 @RequestParam("exitHour") LocalTime exitHour,
                                                 @RequestParam("collectDate") LocalDate collectDate,
                                                 @RequestParam("collectHour") LocalTime collectHour) {
        repairService.calculatePrice(plate, checkinDate, checkinHour, reparationType, exitDate,
                exitHour, collectDate, collectHour);
        return ResponseEntity.noContent().build();
    }
    */


    @GetMapping("/calculate")
    public ResponseEntity<Void> calculatePrice(@RequestBody RepairEntity repair) {
        repairService.calculatePrice(repair.getPlate(), repair.getCheckinDate(), repair.getCheckinHour(),
                repair.getReparationType(), repair.getExitDate(),
                repair.getExitHour(), repair.getCollectDate(), repair.getCollectHour());
        return ResponseEntity.noContent().build();
    }
}
