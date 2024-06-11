package com.ms.grademaster.estudiante.service;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.MateriasHorasDto;
import com.ms.grademaster.estudiante.dto.MateriasEstudianteDto;

import java.util.List;

public interface IConsultaEstudianteService {

    List<EstudianteDto> consultarAllEstudiantes();

    List<EstudianteDto> consultarAllEstudiantesNombre(String nombre);

    MateriasEstudianteDto buscarMateriasPorCarrera(String codigoCarrera, String codigoEstudiante);

    List<MateriasHorasDto> materiasEstudiante(String codigoEstudiante);

}
