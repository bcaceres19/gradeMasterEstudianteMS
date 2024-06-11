package com.ms.grademaster.estudiante.repository;

import com.ms.grademaster.comons.entity.NotaMateriaEntity;
import com.ms.grademaster.comons.entity.NotaMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaMateriaRepository extends JpaRepository<NotaMateriaEntity, NotaMateriaId> {

    @Query(value = """
                select nm.valor_nota, n.porcentaje_nota  from dbo.nota_materia nm
                inner join dbo.nota n on nm.codigo_nota_fk = n.codigo_nota
                where nm.numero_corte = :numeroCorte 
                and nm.codigo_estudiante_fk = :codigoEstudiante
                and nm.codigo_materia_fk  = :codigoMateria   
            """, nativeQuery = true)
    List<Object[]> notasMateriaEstudianteCorte(@Param("numeroCorte") Long numeroCorte,
                                               @Param("codigoEstudiante") String codigoEstudiante,
                                               @Param("codigoMateria") String codigoMateria);

}
