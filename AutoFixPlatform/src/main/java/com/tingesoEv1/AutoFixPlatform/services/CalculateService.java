package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class CalculateService {
    // calcula el precio de la reparacion
    //public int getTotalPrice() {
    //    return ;
    //}

    public double getReparationTypePrice(VehicleEntity vehicle, int reparation) {
        double price = 0.0;
        String vehicleMotor = vehicle.getMotor();
        double[] pricesGasolina = {};
        double[] pricesDiesel = {};
        double[] pricesHibrido = {};
        double[] pricesElectrico = {};

        switch (vehicleMotor) {
            case "Gasolina":
                price = pricesGasolina[reparation];
            case "Diésel":
                price = pricesDiesel[reparation];
            case "Híbrido":
                price = pricesHibrido[reparation];
            case "Eléctrico":
                price = pricesElectrico[reparation];
        }
        return price;
    }

    //public int getDiscounts() {}

    //public int getRecharges() {}

    //public int getBonus() {}

    //public int getReparationsDiscount() {}

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
        double yearRecharge = 0.0;
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
        }

        return yearRecharge;
    }

    //public int getLateRecharge() {}
}
