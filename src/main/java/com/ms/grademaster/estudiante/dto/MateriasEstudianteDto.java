package com.ms.grademaster.estudiante.dto;

import com.ms.grademaster.comons.dto.MateriaDto;
import lombok.Data;

import java.util.List;

@Data
public class MateriasEstudianteDto {

    private List<MateriaDto> materiasAsignadas;

    private List<MateriaDto> materiasNoAsignadas;


}
