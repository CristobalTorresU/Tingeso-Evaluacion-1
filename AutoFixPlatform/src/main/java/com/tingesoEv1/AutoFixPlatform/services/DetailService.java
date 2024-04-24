package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public ArrayList<DetailEntity> getDetails() {
        return (ArrayList<DetailEntity>) detailRepository.findAll();
    }

    public DetailEntity getRepairById(Long id) {
        return detailRepository.findById(id).get();
    }

    public DetailEntity saveDetail(DetailEntity detail) {
        return detailRepository.save(detail);
    }

}
