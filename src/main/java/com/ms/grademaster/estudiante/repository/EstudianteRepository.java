package com.ms.grademaster.estudiante.repository;

import com.ms.grademaster.comons.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {
}
