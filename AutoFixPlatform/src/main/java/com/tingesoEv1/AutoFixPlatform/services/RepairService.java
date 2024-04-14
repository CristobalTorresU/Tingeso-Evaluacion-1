package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
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

    public List<RepairEntity> getRepairsByPlate(String plate) {
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

    public List<RepairEntity> getRepairByTotalAmount(Integer startTotalAmount, Integer endTotalAmount) {
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

    // TODO: Por ahora sólo funciona cuando van de salida,
    //  hacer que funcione antes de sacar el vehículo.
    public Boolean calculatePrice(String plate,
                                  LocalDate checkinDate,
                                  LocalTime checkinHour,
                                  int reparationType,
                                  LocalDate exitDate,
                                  LocalTime exitHour,
                                  LocalDate collectDate,
                                  LocalTime collectHour)
    {
        RepairEntity repair = new RepairEntity();

       VehicleEntity vehicle = vehicleService.getVehicleByPlate(plate);
       List<BonusEntity> bonuses = bonusService.getBonusByBrand(vehicle.getBrand(), false);

        int totalPrice;
        // TODO: Hacer que funcione para más de una reparación a la vez.
        double reparations = calculateService.getReparationTypePrice(vehicle, reparationType);
        double mileageRecharges = reparations * calculateService.getMileageRecharge(vehicle);
        double yearRecharge = reparations * calculateService.getYearRecharge(vehicle, checkinDate);
        // TODO: Preguntar si se multiplica o se suman los porcentajes.
        double lateRecharge = reparations * calculateService.getLateRecharge(exitDate, collectDate);
        double reparationDiscounts = reparations * calculateService.getReparationsDiscount(vehicle,
                getRepairsByPlate(vehicle.getPlate()));
        double dayDiscount = reparations * calculateService.getDayDiscount(checkinDate, checkinHour);
        double bonusDiscount = calculateService.getBonusDiscount(bonuses);

        int discounts = (int)reparationDiscounts + (int)dayDiscount + (int)bonusDiscount;
        int recharges = (int)mileageRecharges + (int)yearRecharge + (int)lateRecharge;
        int iva = (int)(reparations * 0.19);

        // DEBUG
        /*
        System.out.print("\nReparations Discounts: ");
        System.out.print(reparationDiscounts);
        System.out.print("\nDay Discount: ");
        System.out.print(dayDiscount);
        System.out.print("\nBonus Discount: ");
        System.out.print(bonusDiscount);
        System.out.print("\nMileage Recharge: ");
        System.out.print(mileageRecharges);
        System.out.print("\nYear Recharge: ");
        System.out.print(yearRecharge);
        System.out.print("\nLate Recharge: ");
        System.out.print(lateRecharge);
        System.out.print("\nIVA: ");
        System.out.print(iva);
         */

        totalPrice = ((int)reparations + recharges - discounts) + iva;

        repair.setCheckinDate(checkinDate);
        repair.setCheckinHour(checkinHour);
        repair.setPlate(vehicle.getPlate());
        repair.setReparationType(reparationType);
        repair.setExitDate(exitDate);
        repair.setExitHour(exitHour);
        repair.setCollectDate(collectDate);
        repair.setCollectHour(collectHour);
        repair.setTotalAmount(totalPrice);
        repair.setRepairAmount((int)reparations);
        repair.setRepairsDiscount((int)reparationDiscounts);
        repair.setDayDiscount((int)dayDiscount);
        repair.setBonusDiscount((int)bonusDiscount);
        repair.setMileageRecharge((int)mileageRecharges);
        repair.setYearRecharge((int)yearRecharge);
        repair.setLateRecharge((int)lateRecharge);
        repair.setIVA(iva);

        repairRepository.save(repair);

        return true;
    }

    // TODO: ¿Genera el reporte con los datos que ya tiene la entidad?
    public boolean generateRepairReport(Long id) {
        return true;
    }

    // TODO: Una vez se tiene el reporte, hacer que se muestre por pantalla.
    public boolean generateTypeReport() {
        List<Object[]> report = repairRepository.getTypeReport();
        return true;
    }

    // TODO: Una vez se tiene el reporte, hacer que se muestre por pantalla.
    public boolean generateMotorReport() {
        List<Object[]> report = repairRepository.getMotorReport();
        return true;
    }
}
