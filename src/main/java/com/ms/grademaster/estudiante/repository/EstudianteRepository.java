package com.ms.grademaster.estudiante.repository;

import com.ms.grademaster.comons.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {

    List<EstudianteEntity> findAllByEstadoEntityFk_Nombre(String nombreEstado);


    @Query(value = """
    SELECT e FROM EstudianteEntity e where e.codigoEstudiante LIKE %:nombre% AND e.estadoEntityFk.nombre = 'ACTIVO'
    """)
    List<EstudianteEntity> buscarEstudiantesPorNombre(String nombre);

}

