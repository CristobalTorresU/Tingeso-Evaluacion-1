package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.BonusEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.BonusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(BonusService.class)
public class BonusServiceTest {

    @Autowired
    private BonusService bonusService;

    @MockBean
    private BonusRepository bonusRepository;

    @Test
    void whenGetBonuses_thenCorrect() {
        //Given
        BonusEntity bonus1 = new BonusEntity();
        BonusEntity bonus2 = new BonusEntity();

        ArrayList<BonusEntity> bonuses = new ArrayList<>();
        bonuses.add(bonus1);
        bonuses.add(bonus2);

        //When
        when(bonusRepository.findAll()).thenReturn(bonuses);
        ArrayList<BonusEntity> bonusesExample = bonusService.getBonuses();

        //Then
        assertNotNull(bonuses);
        assertThat(bonusesExample.size()).isEqualTo(2);
    }

    @Test
    void whenSaveBonus_thenCorrect() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.save(bonus)).thenReturn(bonus);
        BonusEntity bonusExample = bonusService.saveBonus(bonus);

        //Then
        assertNotNull(bonus);
        assertThat(bonusExample.getAmount()).isEqualTo(10000);
        assertThat(bonusExample.getBrand()).isEqualTo("Toyota");
    }

    @Test
    void whenSaveBonus_thenNull() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.findByBrand("Toyota")).thenReturn(bonus);
        BonusEntity bonusExample = bonusService.saveBonus(bonus);

        //Then
        assertThat(bonusExample).isNull();
    }

    @Test
    void whenGetBonusById_thenCorrect() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setId(1L);
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.findById(1L)).thenReturn(java.util.Optional.of(bonus));
        BonusEntity bonusExample = bonusService.getBonusById(1L);

        //Then
        assertNotNull(bonus);
        assertThat(bonusExample.getAmount()).isEqualTo(10000);
        assertThat(bonusExample.getBrand()).isEqualTo("Toyota");
    }

    @Test
    void whenGetBonusByBrand_thenCorrect() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setId(1L);
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.findByBrand("Toyota")).thenReturn(bonus);
        BonusEntity bonusExample = bonusService.getBonusByBrand("Toyota");

        //Then
        assertNotNull(bonus);
        assertThat(bonusExample.getAmount()).isEqualTo(10000);
        assertThat(bonusExample.getBrand()).isEqualTo("Toyota");
    }

    @Test
    void whenUpdateBonus_thenCorrect() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setId(1L);
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.save(bonus)).thenReturn(bonus);
        BonusEntity bonusExample = bonusService.updateBonus(bonus);

        //Then
        assertNotNull(bonus);
        assertThat(bonusExample.getAmount()).isEqualTo(10000);
        assertThat(bonusExample.getBrand()).isEqualTo("Toyota");
    }

    @Test
    void whenDeleteBonus_thenCorrect() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setId(1L);
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.save(bonus)).thenReturn(bonus);
        boolean result = false;
        try {
            result = bonusService.deleteBonus(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        assertThat(result).isTrue();
    }

    /*
    // TODO: Fix this test
    @Test
    void whenDeleteBonus_thenException() {
        //Given
        BonusEntity bonus = new BonusEntity();
        bonus.setId(1L);
        bonus.setBrand("Toyota");
        bonus.setAmount(10000);
        bonus.setQuantity(2);

        //When
        when(bonusRepository.save(bonus)).thenReturn(bonus);
        boolean result = false;
        try {
            result = bonusService.deleteBonus(2L);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("No value present");
        }

        //Then
        assertThat(result).isFalse();
    }
     */

}
