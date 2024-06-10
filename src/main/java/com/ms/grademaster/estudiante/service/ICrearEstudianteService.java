package com.ms.grademaster.estudiante.service;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.estudiante.dto.AsignarMateriasDto;

public interface ICrearEstudianteService {

    void crearEstudiante(EstudianteDto estudiante);

    void asignarMateriasDocente(AsignarMateriasDto asignarMateriasDto);

}
