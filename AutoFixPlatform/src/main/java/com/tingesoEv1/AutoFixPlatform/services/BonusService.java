package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BonusService {
    @Autowired
    BonusRepository bonusRepository;

    public ArrayList<BonusEntity> getBonuses() {
        return (ArrayList<BonusEntity>) bonusRepository.findAll();
    }

    public BonusEntity saveBonus(BonusEntity bonus) {
        if (bonusRepository.findByBrand(bonus.getBrand()) != null) {
            return null;
        }
        return bonusRepository.save(bonus);
    }

    public BonusEntity getBonusById(Long id) {
        return bonusRepository.findById(id).get();
    }

    public BonusEntity getBonusByBrand(String brand) {
        return bonusRepository.findByBrand(brand);
    }

    public BonusEntity updateBonus(BonusEntity bonus) {
        return bonusRepository.save(bonus);
    }

    public boolean deleteBonus(Long id) throws Exception {
        try{
            bonusRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
