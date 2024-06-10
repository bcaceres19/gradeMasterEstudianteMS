package com.ms.grademaster.estudiante.repository;

import com.ms.grademaster.comons.entity.EstudianteMateriaEntity;
import com.ms.grademaster.comons.entity.EstudianteMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteMateriaRepository extends JpaRepository<EstudianteMateriaEntity, EstudianteMateriaId> {

    @Query(value = """
    select m.codigo, m.nombre  from dbo.estudiante_materia em inner join dbo.materia m on em.codigo_materia_fk = m.codigo
    inner join dbo.estado e on m.estado_fk = e.id_estado
    where em.codigo_estudiante_fk = :codigoEstudiante and e.nombre = 'ACTIVO'
    """, nativeQuery = true)
    List<Object[]> buscarMateriasEstudiante(@Param("codigoEstudiante") String codigoEstudiante);

    void deleteByCodigoMateriaEntityFk_Codigo(String codigoMateria);

}
