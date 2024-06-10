package com.ms.grademaster.estudiante.service.impl;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.mapper.EstudianteMapper;
import com.ms.grademaster.comons.mapper.EstudianteMateriaMapper;
import com.ms.grademaster.comons.mapper.MateriaMapper;
import com.ms.grademaster.estudiante.dto.AsignarMateriasDto;
import com.ms.grademaster.estudiante.repository.EstudianteMateriaRepository;
import com.ms.grademaster.estudiante.repository.EstudianteRepository;
import com.ms.grademaster.estudiante.service.ICrearEstudianteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.ms.grademaster.comons.utils.constants.Constantes.EXTENSION_CORREO;
import static com.ms.grademaster.comons.utils.constants.Constantes.INICIALES_ESTUDIANTE;

@Log4j2
@Service
@RequiredArgsConstructor
public class CrearEstudianteService implements ICrearEstudianteService {

    private final EstudianteRepository estudianteRepository;

    private final EstudianteMapper estudianteMapper;

    private final EstudianteMateriaRepository estudianteMateriaRepository;

    private final EstudianteMateriaMapper estudianteMateriaMapper;

    private final MateriaMapper materiaMapper;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void crearEstudiante(EstudianteDto estudiante) {
        estudianteRepository.save(estudianteMapper.dtoToEntity(estudiante));
    }

    @Override
    @Transactional
    public void asignarMateriasDocente(AsignarMateriasDto asignarMateriasDto) {
        log.error(asignarMateriasDto.toString());
        if(!asignarMateriasDto.getMateriasNoAsignadas().isEmpty()){
            asignarMateriasDto.getMateriasNoAsignadas().forEach(materia -> {
                if(materia.getCodigo() != null){
                    estudianteMateriaRepository.deleteByCodigoMateriaEntityFk_Codigo(materia.getCodigo());
                }
            });
        }
        estudianteMateriaRepository.saveAll(estudianteMateriaMapper.objectsToEntity(
                materiaMapper.listDtoToListEntity(asignarMateriasDto.getMateriasAsignar()),
                estudianteMapper.dtoToEntity(asignarMateriasDto.getEstudianteDto())
        ));
    }

}
