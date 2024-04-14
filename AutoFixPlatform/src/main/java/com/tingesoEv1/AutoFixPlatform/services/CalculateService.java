package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalculateService {
    public double getReparationTypePrice(VehicleEntity vehicle, int reparation) {
        double price = 0.0;
        String vehicleMotor = vehicle.getMotor();
        double[] pricesGasolina = {120000.0, 130000.0, 350000.0,
                210000.0, 150000.0, 100000.0, 100000.0,
                180000.0, 150000.0, 130000.0, 80000.0
        };
        double[] pricesDiesel = {120000.0, 130000.0, 450000.0,
                210000.0, 150000.0, 120000.0, 100000.0,
                180000.0, 150000.0, 140000.0, 80000.0
        };
        double[] pricesHibrido = {180000.0, 190000.0, 700000.0,
                300000.0, 200000.0, 450000.0, 100000.0,
                210000.0, 180000.0, 220000.0, 80000.0
        };
        double[] pricesElectrico = {220000.0, 230000.0, 800000.0,
                300000.0, 250000.0, 0.0, 100000.0,
                250000.0, 180000.0, 0.0, 80000.0
        };

        switch (vehicleMotor) {
            case "Gasolina":
                price = pricesGasolina[reparation - 1];
                break;
            case "Diésel":
                price = pricesDiesel[reparation - 1];
                break;
            case "Híbrido":
                price = pricesHibrido[reparation - 1];
                break;
            case "Eléctrico":
                price = pricesElectrico[reparation - 1];
                break;
        }
        return price;
    }

    // TODO: Organizar ideas para los bonus.
    // TODO: Optimizar como se buscan los bonos.
    // TODO: Podría ser con las querys.
    public double getBonusDiscount(List<BonusEntity> bonuses) {
        double bonusDiscount = 0.0;
        if (!bonuses.isEmpty()) {
            // TODO: Verificar si en realidad se puede cambiar el bono desde aquí.
            bonusDiscount = bonuses.get(0).getAmount();
            bonuses.get(0).setUsed(true);
        }

        return bonusDiscount;
    }

    public double getReparationsDiscount(VehicleEntity vehicle, List<RepairEntity> repairs) {
        int totalReparations = 0;
        double reparationsDiscount = 0.0;

        // Conseguir la cantidad de reparaciones en el último año.
        for (int i = 0 ; i < repairs.size() ; i++) {
            LocalDate reparationDate = repairs.get(i).getCheckinDate();
            LocalDate interval = LocalDate.now().minusYears(1);
            if (!reparationDate.isBefore(interval)) {
                totalReparations += 1;
            }
        }

        if (totalReparations == 0) {
            return reparationsDiscount;
        }

        // Realizar los descuentos
        switch (vehicle.getMotor()) {
            case "Gasolina":
                if (totalReparations <= 2) {
                    reparationsDiscount = 0.05;
                } else if (totalReparations <= 5) {
                    reparationsDiscount = 0.10;
                } else if (totalReparations <= 9) {
                    reparationsDiscount = 0.15;
                } else {
                    reparationsDiscount = 0.20;
                }
                break;
            case "Diésel":
                if (totalReparations <= 2) {
                    reparationsDiscount = 0.07;
                } else if (totalReparations <= 5) {
                    reparationsDiscount = 0.12;
                } else if (totalReparations <= 9) {
                    reparationsDiscount = 0.17;
                } else {
                    reparationsDiscount = 0.22;
                }
                break;
            case "Híbrido":
                if (totalReparations <= 2) {
                    reparationsDiscount = 0.10;
                } else if (totalReparations <= 5) {
                    reparationsDiscount = 0.15;
                } else if (totalReparations <= 9) {
                    reparationsDiscount = 0.20;
                } else {
                    reparationsDiscount = 0.25;
                }
                break;
            case "Eléctrico":
                if (totalReparations <= 2) {
                    reparationsDiscount = 0.08;
                } else if (totalReparations <= 5) {
                    reparationsDiscount = 0.13;
                } else if (totalReparations <= 9) {
                    reparationsDiscount = 0.18;
                } else {
                    reparationsDiscount = 0.23;
                }
                break;
        }
        return reparationsDiscount;
    }

    public double getMileageRecharge(VehicleEntity vehicle) {
        double mileageRecharge = 0.0;
        int vehicleMileage = vehicle.getMileage();
        String vehicleType = vehicle.getType();

        switch (vehicleType) {
            case "Sedán":
            case "Hatchback":
                if (vehicleMileage <= 5000) {
                    return mileageRecharge;
                } else if (vehicleMileage <= 12000) {
                    mileageRecharge = 0.03;
                } else if (vehicleMileage <= 25000) {
                    mileageRecharge = 0.07;
                } else if (vehicleMileage <= 40000) {
                    mileageRecharge = 0.12;
                } else {
                    mileageRecharge = 0.20;
                }
                break;
            case "SUV":
            case "Pickup":
            case "Furgoneta":
                if (vehicleMileage <= 5000) {
                    return mileageRecharge;
                } else if (vehicleMileage <= 12000) {
                    mileageRecharge = 0.05;
                } else if (vehicleMileage <= 25000) {
                    mileageRecharge = 0.09;
                } else if (vehicleMileage <= 40000) {
                    mileageRecharge = 0.12;
                } else {
                    mileageRecharge = 0.20;
                }
                break;
        }

        return mileageRecharge;
    }

    public double getYearRecharge(VehicleEntity vehicle, LocalDate checkinDate) {
        double yearRecharge = 0.0;
        int checkinlYear = checkinDate.getYear();
        int vehicleYear = vehicle.getYear();
        String vehicleType = vehicle.getType();

        int vehicleAge = checkinlYear - vehicleYear;

        switch (vehicleType) {
            case "Sedán":
            case "Hatchback":
                if (vehicleAge <= 5) {
                    return yearRecharge;
                } else if (vehicleAge <= 10) {
                    yearRecharge = 0.05;
                } else if (vehicleAge <= 15) {
                    yearRecharge = 0.09;
                } else {
                    yearRecharge = 0.15;
                }
                break;
            case "SUV":
            case "Pickup":
            case "Furgoneta":
                if (vehicleAge <= 5) {
                    return yearRecharge;
                } else if (vehicleAge <= 10) {
                    yearRecharge = 0.07;
                } else if (vehicleAge <= 15) {
                    yearRecharge = 0.11;
                } else {
                    yearRecharge = 0.20;
                }
                break;
        }

        return yearRecharge;
    }

    public double getDayDiscount(LocalDate checkinDate, LocalTime checkinHour) {
        double dayDiscount = 0.0;
        DayOfWeek dayOfWeek = checkinDate.getDayOfWeek();

        if (checkinHour.isAfter(LocalTime.of(9,0)) &&
                checkinHour.isBefore(LocalTime.of(12,0))) {
            if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY ||
                    dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.THURSDAY) {
                dayDiscount = 0.10;
            }
        }

        return dayDiscount;
    }

    public double getLateRecharge(LocalDate exitDate, LocalDate collectDate) {
        double lateRecharge = 0.0;
        int daysLate = exitDate.until(collectDate).getDays();
        if (daysLate != 0) {
            lateRecharge = 0.05 * daysLate;
        }

        return lateRecharge;
    }
}