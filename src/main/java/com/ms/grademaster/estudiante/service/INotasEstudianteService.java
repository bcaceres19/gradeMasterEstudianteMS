package com.ms.grademaster.estudiante.service;

import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.estudiante.dto.NotasEstudianteDto;
import com.ms.grademaster.estudiante.dto.NotasSemestreDto;

import java.util.List;

public interface INotasEstudianteService {

    NotasSemestreDto notasCortesEstudiante(String codigoEstudiante, String codigoMateria);

    List<NotaMateriaDto> notasMateriaEstudianteCorte(Long numeroCorte, String codigoEstudiante, String codigoMateria);

}
