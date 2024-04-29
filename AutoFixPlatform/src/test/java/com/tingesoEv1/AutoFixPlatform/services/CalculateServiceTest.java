package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.entities.RepairEntity;
import com.tingesoEv1.AutoFixPlatform.entities.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@WebMvcTest(CalculateService.class)
public class CalculateServiceTest {

    @Autowired
    private CalculateService calculateService;

    @Test
    void whenGetReparationTypePriceGasolina_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setMotor("Gasolina");
        double price = calculateService.getReparationTypePrice(vehicle, 1);
        assertEquals(120000.0, price);
    }

    @Test
    void whenGetReparationTypePriceDiesel_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setMotor("Diésel");
        double price = calculateService.getReparationTypePrice(vehicle, 1);
        assertEquals(120000.0, price);
    }

    @Test
    void whenGetReparationTypePriceHibrido_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setMotor("Híbrido");
        double price = calculateService.getReparationTypePrice(vehicle, 1);
        assertEquals(180000.0, price);
    }

    @Test
    void whenGetReparationTypePriceElectrico_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setMotor("Eléctrico");
        double price = calculateService.getReparationTypePrice(vehicle, 1);
        assertEquals(220000.0, price);
    }

    @Test
    void whenGetBonusDiscountNull_thenCorrect() {
        double price = calculateService.getBonusDiscount(null);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetBonusDiscount_thenCorrect() {
        BonusEntity bonus = new BonusEntity();
        bonus.setAmount(1000);
        bonus.setQuantity(1);
        double price = calculateService.getBonusDiscount(bonus);
        assertEquals(1000.0, price);
    }

    @Test
    void whenGetReparationsDiscountNull_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Diésel");

        List<RepairEntity> repairs = new ArrayList<>();
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Diésel");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.07, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Diésel");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.12, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect3() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Diésel");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.17, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Diésel");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));
        RepairEntity repair7 = new RepairEntity();
        repair7.setPlate("AAAA21");
        repair7.setCheckinDate(LocalDate.parse("2024-02-07"));
        RepairEntity repair8 = new RepairEntity();
        repair8.setPlate("AAAA21");
        repair8.setCheckinDate(LocalDate.parse("2024-02-08"));
        RepairEntity repair9 = new RepairEntity();
        repair9.setPlate("AAAA21");
        repair9.setCheckinDate(LocalDate.parse("2024-02-09"));
        RepairEntity repair10 = new RepairEntity();
        repair10.setPlate("AAAA21");
        repair10.setCheckinDate(LocalDate.parse("2024-02-10"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6, repair7, repair8, repair9, repair10));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.22, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect5() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Gasolina");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));
        RepairEntity repair7 = new RepairEntity();
        repair7.setPlate("AAAA21");
        repair7.setCheckinDate(LocalDate.parse("2024-02-07"));
        RepairEntity repair8 = new RepairEntity();
        repair8.setPlate("AAAA21");
        repair8.setCheckinDate(LocalDate.parse("2024-02-08"));
        RepairEntity repair9 = new RepairEntity();
        repair9.setPlate("AAAA21");
        repair9.setCheckinDate(LocalDate.parse("2024-02-09"));
        RepairEntity repair10 = new RepairEntity();
        repair10.setPlate("AAAA21");
        repair10.setCheckinDate(LocalDate.parse("2024-02-10"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6, repair7, repair8, repair9, repair10));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.20, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect6() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Gasolina");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.10, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect7() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Gasolina");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.05, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect8() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Gasolina");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));
        RepairEntity repair7 = new RepairEntity();
        repair7.setPlate("AAAA21");
        repair7.setCheckinDate(LocalDate.parse("2024-02-07"));
        RepairEntity repair8 = new RepairEntity();
        repair8.setPlate("AAAA21");
        repair8.setCheckinDate(LocalDate.parse("2024-02-08"));
        RepairEntity repair9 = new RepairEntity();
        repair9.setPlate("AAAA21");
        repair9.setCheckinDate(LocalDate.parse("2024-02-09"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6, repair7, repair8, repair9));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.15, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect10() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Híbrido");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.10, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect11() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Híbrido");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.15, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect12() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Híbrido");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.20, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect13() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Híbrido");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));
        RepairEntity repair7 = new RepairEntity();
        repair7.setPlate("AAAA21");
        repair7.setCheckinDate(LocalDate.parse("2024-02-07"));
        RepairEntity repair8 = new RepairEntity();
        repair8.setPlate("AAAA21");
        repair8.setCheckinDate(LocalDate.parse("2024-02-08"));
        RepairEntity repair9 = new RepairEntity();
        repair9.setPlate("AAAA21");
        repair9.setCheckinDate(LocalDate.parse("2024-02-09"));
        RepairEntity repair10 = new RepairEntity();
        repair10.setPlate("AAAA21");
        repair10.setCheckinDate(LocalDate.parse("2024-02-10"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6, repair7, repair8, repair9, repair10));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.25, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect9() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Eléctrico");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.08, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect14() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Eléctrico");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.13, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect15() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Eléctrico");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.18, price);
    }

    @Test
    void whenGetReparationsDiscount_thenCorrect16() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setPlate("AAAA21");
        vehicle.setMotor("Eléctrico");

        RepairEntity repair1 = new RepairEntity();
        repair1.setPlate("AAAA21");
        repair1.setCheckinDate(LocalDate.parse("2024-02-03"));
        RepairEntity repair2 = new RepairEntity();
        repair2.setPlate("AAAA21");
        repair2.setCheckinDate(LocalDate.parse("2024-02-01"));
        RepairEntity repair3 = new RepairEntity();
        repair3.setPlate("AAAA21");
        repair3.setCheckinDate(LocalDate.parse("2024-02-02"));
        RepairEntity repair4 = new RepairEntity();
        repair4.setPlate("AAAA21");
        repair4.setCheckinDate(LocalDate.parse("2024-02-04"));
        RepairEntity repair5 = new RepairEntity();
        repair5.setPlate("AAAA21");
        repair5.setCheckinDate(LocalDate.parse("2024-02-05"));
        RepairEntity repair6 = new RepairEntity();
        repair6.setPlate("AAAA21");
        repair6.setCheckinDate(LocalDate.parse("2024-02-06"));
        RepairEntity repair7 = new RepairEntity();
        repair7.setPlate("AAAA21");
        repair7.setCheckinDate(LocalDate.parse("2024-02-07"));
        RepairEntity repair8 = new RepairEntity();
        repair8.setPlate("AAAA21");
        repair8.setCheckinDate(LocalDate.parse("2024-02-08"));
        RepairEntity repair9 = new RepairEntity();
        repair9.setPlate("AAAA21");
        repair9.setCheckinDate(LocalDate.parse("2024-02-09"));
        RepairEntity repair10 = new RepairEntity();
        repair10.setPlate("AAAA21");
        repair10.setCheckinDate(LocalDate.parse("2024-02-10"));

        List<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2, repair3, repair4, repair5, repair6, repair7, repair8, repair9, repair10));
        double price = calculateService.getReparationsDiscount(vehicle, repairs);
        assertEquals(0.23, price);
    }

    @Test
    void whenGetMileageRechargeSedan_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setMileage(4000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetMileageRechargeSedan_thenCorrect2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setMileage(10000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.03, price);
    }

    @Test
    void whenGetMileageRechargeSedan_thenCorrect3() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setMileage(20000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.07, price);
    }

    @Test
    void whenGetMileageRechargeSedan_thenCorrect4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setMileage(35000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.12, price);
    }

    @Test
    void whenGetMileageRechargeSedan_thenCorrect5() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setMileage(50000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.20, price);
    }

    @Test
    void whenGetMileageRechargeSUV_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("SUV");
        vehicle.setMileage(4000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetMileageRechargeSUV_thenCorrect2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("SUV");
        vehicle.setMileage(10000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.05, price);
    }

    @Test
    void whenGetMileageRechargeSUV_thenCorrect3() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("SUV");
        vehicle.setMileage(20000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.09, price);
    }

    @Test
    void whenGetMileageRechargeSUV_thenCorrect4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("SUV");
        vehicle.setMileage(35000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.12, price);
    }

    @Test
    void whenGetMileageRechargeSUV_thenCorrect5() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("SUV");
        vehicle.setMileage(50000);
        double price = calculateService.getMileageRecharge(vehicle);
        assertEquals(0.20, price);
    }

    @Test
    void whenGetYearRechargeSedan_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setYear(2020);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetYearRechargeSedan_thenCorrect2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setYear(2018);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.05, price);
    }

    @Test
    void whenGetYearRechargeSedan_thenCorrect4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setYear(2009);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.09, price);
    }

    @Test
    void whenGetYearRechargeSedan_thenCorrect5() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Sedán");
        vehicle.setYear(2000);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.15, price);
    }

    @Test
    void whenGetYearRechargePickup_thenCorrect() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Pickup");
        vehicle.setYear(2020);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetYearRechargePickup_thenCorrect2() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Pickup");
        vehicle.setYear(2018);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.07, price);
    }

    @Test
    void whenGetYearRechargePickup_thenCorrect4() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Pickup");
        vehicle.setYear(2009);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.11, price);
    }

    @Test
    void whenGetYearRechargePickup_thenCorrect5() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setType("Pickup");
        vehicle.setYear(2000);
        LocalDate checkinDate = LocalDate.parse("2024-02-03");
        double price = calculateService.getYearRecharge(vehicle, checkinDate);
        assertEquals(0.20, price);
    }

    @Test
    void whenGetDayDiscount_thenCorrect() {
        LocalDate checkinDate = LocalDate.parse("2024-02-06");
        LocalTime checkinHour = LocalTime.parse("10:00");
        double price = calculateService.getDayDiscount(checkinDate,checkinHour);
        assertEquals(0.10, price);
    }

    @Test
    void whenGetLateRecharge_thenCorrect() {
        LocalDate exitDate = LocalDate.parse("2024-02-06");
        LocalDate collectDate = LocalDate.parse("2024-02-06");
        double price = calculateService.getLateRecharge(exitDate, collectDate);
        assertEquals(0.0, price);
    }

    @Test
    void whenGetLateRecharge_thenCorrect2() {
        LocalDate exitDate = LocalDate.parse("2024-02-06");
        LocalDate collectDate = LocalDate.parse("2024-02-07");
        double price = calculateService.getLateRecharge(exitDate, collectDate);
        assertEquals(0.05, price);
    }
}
