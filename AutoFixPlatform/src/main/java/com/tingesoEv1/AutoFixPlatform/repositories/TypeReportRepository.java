package com.tingesoEv1.AutoFixPlatform.repositories;

import com.tingesoEv1.AutoFixPlatform.entities.TypeReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeReportRepository extends JpaRepository<TypeReportEntity, Long> {
    List<TypeReportEntity> findByReparationType(int reparationType);
    List<TypeReportEntity> findByType(String type);
}
