package com.tingesoEv1.AutoFixPlatform.services;

import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import com.tingesoEv1.AutoFixPlatform.repositories.TypeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TypeReportService {
    @Autowired
    TypeReportRepository typeReportRepository;

    public ArrayList<TypeReportEntity> getTypeReports() {
        return (ArrayList<TypeReportEntity>) typeReportRepository.findAll();
    }

    public TypeReportEntity getTypeReportById(Long id) {
        return typeReportRepository.findById(id).get();
    }

    public TypeReportEntity saveTypeReport(TypeReportEntity typeReport) {
        return typeReportRepository.save(typeReport);
    }

    public TypeReportEntity updateTypeReport(TypeReportEntity typeReport) {
        return typeReportRepository.save(typeReport);
    }
}
