package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;

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
                price = pricesGasolina[reparation];
                break;
            case "Diésel":
                price = pricesDiesel[reparation];
                break;
            case "Híbrido":
                price = pricesHibrido[reparation];
                break;
            case "Eléctrico":
                price = pricesElectrico[reparation];
                break;
        }
        return price;
    }

    // TODO: Organizar ideas para los bonus.
    public double getBonusDiscount(VehicleEntity vehicle) {
        return 0.0;
    }

    // TODO: Contar la cantidad de reparaciones en los últimos 12 meses.
    public double getReparationsDiscount(VehicleEntity vehicle) {
        return 0.0;
    }

    public double getMileageRecharge(VehicleEntity vehicle) {
        double mileageRecharge = 1.0;
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

        /*
        if (brand.equals("Sedán")) {
            mileageRecharge = ;
        } else if (brand.equals("Hatchback")) {
            mileageRecharge = ;
        } else if (brand.equals("SUV")) {
            mileageRecharge = ;
        } else if (brand.equals("Pickup")) {
            mileageRecharge = ;
        } else if (brand.equals("Furgoneta")) {
            mileageRecharge = ;
        }
         */

        return mileageRecharge;
    }

    public double getYearRecharge(VehicleEntity vehicle) {
        double yearRecharge = 1.0;
        int actualYear = Year.now().getValue();
        int vehicleYear = vehicle.getYear();
        String vehicleType = vehicle.getType();

        int vehicleAge = actualYear - vehicleYear;

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
        double lateRecharge = 0;
        int daysLate = exitDate.until(collectDate).getDays();
        if (daysLate != 0) {
            lateRecharge = 0.05 * daysLate;
        }

        return lateRecharge;
    }
}