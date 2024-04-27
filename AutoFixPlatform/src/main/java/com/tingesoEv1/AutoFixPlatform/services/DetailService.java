package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.DetailEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public ArrayList<DetailEntity> getDetails() {
        return (ArrayList<DetailEntity>) detailRepository.findAll();
    }

    public List<DetailEntity> getRepairById(Long id) {
        List<DetailEntity> detailAsList = new ArrayList<>();
        DetailEntity detail = detailRepository.findById(id).get();
        detailAsList.add(detail);
        return detailAsList;
    }

    public DetailEntity saveDetail(DetailEntity detail) {
        return detailRepository.save(detail);
    }

}
