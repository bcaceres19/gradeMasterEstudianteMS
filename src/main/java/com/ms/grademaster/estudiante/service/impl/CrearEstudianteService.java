package com.ms.grademaster.estudiante.service.impl;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.mapper.EstudianteMapper;
import com.ms.grademaster.estudiante.repository.EstudianteRepository;
import com.ms.grademaster.estudiante.service.ICrearEstudianteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.ms.grademaster.comons.utils.constants.Constantes.EXTENSION_CORREO;
import static com.ms.grademaster.comons.utils.constants.Constantes.INICIALES_ESTUDIANTE;

@Service
@RequiredArgsConstructor
public class CrearEstudianteService implements ICrearEstudianteService {

    private final EstudianteRepository estudianteRepository;

    private final EstudianteMapper estudianteMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void crearEstudiante(EstudianteDto estudiante) {
        estudianteRepository.save(estudianteMapper.dtoToEntity(estudiante));
    }

}
