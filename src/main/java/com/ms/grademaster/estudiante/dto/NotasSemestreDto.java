package com.ms.grademaster.estudiante.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotasSemestreDto {

    private List<NotasEstudianteDto> notasEstudianteDtos;

    private Double notaFinalSemestre;

    private Double notaFinalSemestreImaginaria;

}
