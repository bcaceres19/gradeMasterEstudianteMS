package com.ms.grademaster.estudiante.dto;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.comons.dto.SemestreDto;
import lombok.Data;

import java.util.List;

@Data
public class AsignarMateriasDto {

    private EstudianteDto estudianteDto;

    private List<MateriaDto> materiasAsignar;

    private List<MateriaDto> materiasNoAsignadas;
}
