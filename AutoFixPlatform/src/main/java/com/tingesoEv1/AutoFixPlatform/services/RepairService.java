package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    BonusService bonusService;
    @Autowired
    DetailService detailService;
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

    // TODO: Hacer que funcione solo si el vehiculo esta registrado.
    public Boolean calculatePrice(String plate,
                                  int mileage,
                                  String checkinDateString,
                                  String checkinHourString,
                                  int reparationType,
                                  String exitDateString,
                                  String exitHourString,
                                  String collectDateString,
                                  String collectHourString)
    {
        // Formatear Fechas
        LocalDate checkinDate = LocalDate.parse(checkinDateString);
        LocalDate exitDate = LocalDate.parse(exitDateString);
        LocalDate collectDate = LocalDate.parse(collectDateString);

        // Formatear Horas
        LocalTime checkinHour = LocalTime.parse(checkinHourString);
        LocalTime exitHour = LocalTime.parse(exitHourString);
        LocalTime collectHour = LocalTime.parse(collectHourString);

        RepairEntity repair = new RepairEntity();
        DetailEntity detail = new DetailEntity();

        VehicleEntity vehicle = vehicleService.getVehicleByPlate(plate);
        BonusEntity bonuses = bonusService.getBonusByBrand(vehicle.getBrand());

        int totalPrice;
        double reparations = calculateService.getReparationTypePrice(vehicle, reparationType);
        double mileageRecharges = reparations * calculateService.getMileageRecharge(vehicle, mileage);
        double yearRecharge = reparations * calculateService.getYearRecharge(vehicle, checkinDate);
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

        // Atributos para la reparacion
        repair.setCheckinDate(checkinDate);
        repair.setCheckinHour(checkinHour);
        repair.setPlate(vehicle.getPlate());
        repair.setReparationType(reparationType);
        repair.setExitDate(exitDate);
        repair.setExitHour(exitHour);
        repair.setCollectDate(collectDate);
        repair.setCollectHour(collectHour);
        repair.setTotalAmount(totalPrice);

        // Atributos para el detalle
        detail.setRepairAmount((int)reparations);
        detail.setRepairsDiscount((int)reparationDiscounts);
        detail.setDayDiscount((int)dayDiscount);
        detail.setBonusDiscount((int)bonusDiscount);
        detail.setMileageRecharge((int)mileageRecharges);
        detail.setYearRecharge((int)yearRecharge);
        detail.setLateRecharge((int)lateRecharge);
        detail.setIVA(iva);

        repairRepository.save(repair);

        detail.setRepair(repair);
        detailService.saveDetail(detail);

        return true;
    }
}
