package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.BonusRepository;
import com.tingesoEv1.AutoFixPlatform.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    BonusService bonusService;
    @Autowired
    CalculateService calculateService;

    public ArrayList<RepairEntity> getRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id).get();
    }

    public List<RepairEntity> getRepairByPlate(String plate) {
        return repairRepository.findByPlate(plate);
    }

    public RepairEntity saveRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }

    public List<RepairEntity> getRepairByType(int reparationType) {
        return repairRepository.findByReparationType(reparationType);
    }

    public List<RepairEntity> getRepairByCheckinDate(LocalDate checkinDate) {
        return repairRepository.findByCheckinDateBefore(checkinDate);
    }

    public List<RepairEntity> getRepairByExitDate(LocalDate exitDate) {
        return repairRepository.findByExitDateBefore(exitDate);
    }

    public List<RepairEntity> getRepairByTotalAmount(Double startTotalAmount, Double endTotalAmount) {
        return repairRepository.findByTotalAmountBetween(startTotalAmount, endTotalAmount);
    }

    public List<RepairEntity> getRepairByCollectDate(LocalDate collectDate) {
        return repairRepository.findByCollectDateBefore(collectDate);
    }

    public RepairEntity updateRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }

    public boolean deleteRepair(Long id) throws Exception {
        try{
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // TODO: Por ahora solo funciona cuando van de salida,
    //  hacer que funcione antes de sacar el vehíclo.
    public Boolean calculatePrice(VehicleEntity vehicle,
                                 LocalDate checkinDate,
                                 LocalTime checkinHour,
                                 int reparationType,
                                 LocalDate exitDate,
                                 LocalTime exitHour,
                                 LocalDate collectDate,
                                 LocalTime collectHour) {
        RepairEntity repair = new RepairEntity();

        double totalPrice = 0.0;
        double iva = 1.19;
        // TODO: Hacer que funcione para más de una reparación a la vez.
        double reparations = calculateService.getReparationTypePrice(vehicle, reparationType);
        double mileageRecharges = 1.0 + calculateService.getMileageRecharge(vehicle);
        double yearRecharge = 1.0 + calculateService.getYearRecharge(vehicle);
        // TODO: Preguntar si se multiplica o se suman los porcentajes.
        double lateRecharge = 1.0 + calculateService.getLateRecharge(exitDate, collectDate);
        double reparationDiscounts = 1.0 - calculateService.getReparationsDiscount(vehicle);
        double dayDiscount = 1.0 - calculateService.getDayDiscount(checkinDate, checkinHour);
        double bonusDiscount = calculateService.getBonusDiscount(vehicle);

        // TODO: Verificar si la fórmula está correcta.
        totalPrice = ((reparations - bonusDiscount) *
                mileageRecharges *
                yearRecharge *
                lateRecharge *
                reparationDiscounts *
                dayDiscount) * iva;

        repair.setCheckinDate(checkinDate);
        repair.setCheckinHour(checkinHour);
        repair.setPlate(vehicle.getPlate());
        repair.setReparationType(reparationType);
        repair.setExitDate(exitDate);
        repair.setExitHour(exitHour);
        repair.setCollectDate(collectDate);
        repair.setCollectHour(collectHour);
        repair.setTotalAmount(totalPrice);

        return true;
    }
}
