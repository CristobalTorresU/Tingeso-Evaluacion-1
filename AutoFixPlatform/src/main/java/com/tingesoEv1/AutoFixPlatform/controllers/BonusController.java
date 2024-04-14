package com.tingesoEv1.AutoFixPlatform.controllers;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonuses")
@CrossOrigin("*")
public class BonusController {
    @Autowired
    BonusService bonusService;

    @GetMapping("/")
    public ResponseEntity<List<BonusEntity>> listBonus() {
        List<BonusEntity> bonuses = bonusService.getBonuses();
        return  ResponseEntity.ok(bonuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonusEntity> getBonusById(@PathVariable Long id) {
        BonusEntity bonus = bonusService.getBonusById(id);
        return ResponseEntity.ok(bonus);
    }

    // TODO: Ingresar los datos de los bonos, además de la cantidad que se harán.
    @PostMapping("/")
    public ResponseEntity<Void> saveMultipleBonuses(@RequestBody BonusEntity bonus, @RequestParam int quantity) {
        for (int i = 0 ; i < quantity ; i++) {
            BonusEntity bonusNew = new BonusEntity();

            bonusNew.setUsed(false);
            bonusNew.setBrand(bonus.getBrand());
            bonusNew.setAmount(bonus.getAmount());

            bonusService.saveBonus(bonusNew);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<BonusEntity> updateBonus(@RequestBody BonusEntity bonus) {
        BonusEntity bonusUpdated = bonusService.updateBonus(bonus);
        return ResponseEntity.ok(bonusUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BonusEntity> deleteBonus(@PathVariable Long id) throws Exception {
        var isDeleted = bonusService.deleteBonus(id);
        return ResponseEntity.noContent().build();
    }
}
