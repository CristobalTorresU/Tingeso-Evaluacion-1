package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.DetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DetailServiceTest {

    @Autowired
    private DetailService detailService;

    @MockBean
    private DetailRepository detailRepository;

    @Test
    void whenGetDetails_thenCorrect() {
        //Given
        DetailEntity detail1 = new DetailEntity();
        DetailEntity detail2 = new DetailEntity();

        ArrayList<DetailEntity> details = new ArrayList<>();
        details.add(detail1);
        details.add(detail2);

        //When
        when(detailRepository.findAll()).thenReturn(details);
        ArrayList<DetailEntity> detailsExample = detailService.getDetails();

        //Then
        assertNotNull(details);
        assertThat(detailsExample.size()).isEqualTo(2);
    }

    @Test
    void whenSaveDetail_thenCorrect() {
        //Given
        DetailEntity detail = new DetailEntity();
        detail.setRepairAmount(800000);
        detail.setRepairsDiscount(10000);
        detail.setDayDiscount(2);
        detail.setBonusDiscount(10000);
        detail.setMileageRecharge(10000);
        detail.setYearRecharge(10000);
        detail.setLateRecharge(10000);
        detail.setIva(10000);
        detail.setRepair_id(1L);

        //When
        when(detailRepository.save(detail)).thenReturn(detail);
        DetailEntity detailExample = detailService.saveDetail(detail);

        //Then
        assertNotNull(detail);
        assertThat(detailExample.getRepairsDiscount()).isEqualTo(10000);
        assertThat(detailExample.getIva()).isEqualTo(10000);
    }

    @Test
    void whenGetRepairById_thenCorrect() {
        //Given
        DetailEntity detail = new DetailEntity();
        detail.setRepairAmount(800000);
        detail.setRepairsDiscount(10000);
        detail.setDayDiscount(2);
        detail.setBonusDiscount(10000);
        detail.setMileageRecharge(10000);
        detail.setYearRecharge(10000);
        detail.setLateRecharge(10000);
        detail.setIva(10000);
        detail.setRepair_id(1L);

        //When
        when(detailRepository.findById(1L)).thenReturn(java.util.Optional.of(detail));
        ArrayList<DetailEntity> detailsExample = (ArrayList<DetailEntity>) detailService.getRepairById(1L);

        //Then
        assertNotNull(detailsExample);
        assertThat(detailsExample.size()).isEqualTo(1);
    }
}
