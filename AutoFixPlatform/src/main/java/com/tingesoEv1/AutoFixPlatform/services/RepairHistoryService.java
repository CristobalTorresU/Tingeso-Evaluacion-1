package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairHistoryEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.RepairHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RepairHistoryService {
    @Autowired
    RepairHistoryRepository repairHistoryRepository;

    public ArrayList<RepairHistoryEntity> getRepairHistory() {
        return (ArrayList<RepairHistoryEntity>) repairHistoryRepository.findAll();
    }

    public RepairHistoryEntity saveRepairHistory(RepairHistoryEntity repairHistory) {
        return repairHistoryRepository.save(repairHistory);
    }

    // Calculo del historial

}
