package com.ms.grademaster.estudiante.repository;

import com.ms.grademaster.comons.entity.CarreraMateriaEntity;
import com.ms.grademaster.comons.entity.CarreraMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraMateriaRepository extends JpaRepository<CarreraMateriaEntity, CarreraMateriaId> {

    @Query(value = """
        SELECT m2.codigo, m2.nombre FROM dbo.carrera_materia cm 
            inner join dbo.carrera m on cm.codigo_carrera_fk = m.codigo_carrera 
                 inner join dbo.materia m2 on cm.codigo_materia_fk = m2.codigo
                                  inner join dbo.estado e on m2.estado_fk = e.id_estado
                 WHERE codigo_carrera_fk = :codigoCarrera and e.nombre = 'ACTIVO'
    """, nativeQuery = true)
    List<Object[]> buscarMateriasCarrera(@Param("codigoCarrera") String codigoCarrera);

}
