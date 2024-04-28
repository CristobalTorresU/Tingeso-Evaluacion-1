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

    public RepairEntity saveInitialRepair(RepairEntity repair, String plate, int reparationType) {
        repair.setCheckinDate(LocalDate.now());
        repair.setCheckinHour(LocalTime.now());
        repair.setPlate(plate);
        repair.setReparationType(reparationType);
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
        double mileageRecharges = reparations * calculateService.getMileageRecharge(vehicle);
        double yearRecharge = reparations * calculateService.getYearRecharge(vehicle, checkinDate);
        double lateRecharge = reparations * calculateService.getLateRecharge(exitDate, collectDate);
        double reparationDiscounts = reparations * calculateService.getReparationsDiscount(vehicle,
                getRepairsByPlate(vehicle.getPlate()));
        double dayDiscount = reparations * calculateService.getDayDiscount(checkinDate, checkinHour);
        double bonusDiscount = calculateService.getBonusDiscount(bonuses);

        int discounts = (int)reparationDiscounts + (int)dayDiscount + (int)bonusDiscount;
        int recharges = (int)mileageRecharges + (int)yearRecharge + (int)lateRecharge;
        int iva = (int)(reparations * 0.19);

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
        detail.setIva(iva);

        repairRepository.save(repair);

        detail.setRepair_id(repair.getRepair_id());
        detailService.saveDetail(detail);

        return true;
    }

    /*
    public Boolean calculateInPrice(Long id) {
        RepairEntity repair = repairRepository.findById(id).get();

        DetailEntity detail = new DetailEntity();

        VehicleEntity vehicle = vehicleService.getVehicleByPlate(repair.getPlate());
        BonusEntity bonuses = bonusService.getBonusByBrand(vehicle.getBrand());

        int totalPrice;
        double reparations = calculateService.getReparationTypePrice(vehicle, repair.getReparationType());
        double mileageRecharges = reparations * calculateService.getMileageRecharge(vehicle);
        double yearRecharge = reparations * calculateService.getYearRecharge(vehicle, repair.getCheckinDate());
        double lateRecharge = reparations * calculateService.getLateRecharge(repair.getExitDate(), repair.getCollectDate());
        double reparationDiscounts = reparations * calculateService.getReparationsDiscount(vehicle,
                getRepairsByPlate(vehicle.getPlate()));
        double dayDiscount = reparations * calculateService.getDayDiscount(repair.getCheckinDate(), repair.getCheckinHour());
        double bonusDiscount = calculateService.getBonusDiscount(bonuses);

        int discounts = (int)reparationDiscounts + (int)dayDiscount + (int)bonusDiscount;
        int recharges = (int)mileageRecharges + (int)yearRecharge + (int)lateRecharge;
        int iva = (int)(reparations * 0.19);

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

        repair.setTotalAmount(totalPrice);

        // Atributos para el detalle
        detail.setRepairAmount((int)reparations);
        detail.setRepairsDiscount((int)reparationDiscounts);
        detail.setDayDiscount((int)dayDiscount);
        detail.setBonusDiscount((int)bonusDiscount);
        detail.setMileageRecharge((int)mileageRecharges);
        detail.setYearRecharge((int)yearRecharge);
        detail.setLateRecharge((int)lateRecharge);
        detail.setIva(iva);

        repairRepository.save(repair);

        detail.setRepair_id(repair.getRepair_id());
        detailService.saveDetail(detail);

        return true;
    }
    */

    /*
    public RepairEntity updateExit(RepairEntity repair) {
        repair.setExitDate(LocalDate.now());
        repair.setExitHour(LocalTime.now());
        return repair;
    }

    public RepairEntity updateCollect(RepairEntity repair) {
        repair.setCollectDate(LocalDate.now());
        repair.setCollectHour(LocalTime.now());
        return repair;
    }
    */
}
