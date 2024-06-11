package com.ms.grademaster.estudiante.service.impl;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.comons.dto.MateriasHorasDto;
import com.ms.grademaster.comons.mapper.CarreraMateriaMapper;
import com.ms.grademaster.comons.mapper.EstudianteMapper;
import com.ms.grademaster.comons.mapper.MateriaMapper;
import com.ms.grademaster.estudiante.dto.MateriasEstudianteDto;
import com.ms.grademaster.estudiante.repository.CarreraMateriaRepository;
import com.ms.grademaster.estudiante.repository.EstudianteMateriaRepository;
import com.ms.grademaster.estudiante.repository.EstudianteRepository;
import com.ms.grademaster.estudiante.service.IConsultaEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaEstudianteService implements IConsultaEstudianteService {

    private final EstudianteRepository estudianteRepository;

    private final EstudianteMapper estudianteMapper;

    private final CarreraMateriaMapper carreraMateriaMapper;

    private final CarreraMateriaRepository carreraMateriaRepository;

    private final MateriaMapper materiaMapper;

    private final EstudianteMateriaRepository estudianteMateriaRepository;

    @Override
    public List<EstudianteDto> consultarAllEstudiantes() {
        return estudianteMapper.listEntityToListDto(estudianteRepository.findAllByEstadoEntityFk_Nombre("ACTIVO"));
    }

    @Override
    public List<EstudianteDto> consultarAllEstudiantesNombre(String nombre) {
        return estudianteMapper.listEntityToListDto(estudianteRepository.buscarEstudiantesPorNombre(nombre));
    }

    @Override
    public MateriasEstudianteDto buscarMateriasPorCarrera(String codigoCarrera, String codigoEstudiante) {
        List<MateriaDto> materiasCarrera = materiaMapper.lisObjectToListMateriaDocente(carreraMateriaRepository.buscarMateriasCarrera(codigoCarrera));
        List<MateriaDto> materiasEstudiante = materiaMapper.lisObjectToListMateriaDocente(estudianteMateriaRepository.buscarMateriasEstudiante(codigoEstudiante));
        MateriasEstudianteDto materiasEstudianteDto = new MateriasEstudianteDto();
        materiasEstudianteDto.setMateriasAsignadas(materiasEstudiante);
        materiasEstudianteDto.setMateriasNoAsignadas(materiasCarrera.stream().filter(materia -> materiasEstudiante.stream().noneMatch(materiaAsig -> materiaAsig.getCodigo().equals(materia.getCodigo()))).collect(Collectors.toList()));
        return materiasEstudianteDto;
    }

    @Override
    public List<MateriasHorasDto> materiasEstudiante(String codigoEstudiante) {
        return materiaMapper.listObjectToListMateriasHorasDto(estudianteMateriaRepository.buscarMateriasHorariosEstudiante(codigoEstudiante));
    }
}
