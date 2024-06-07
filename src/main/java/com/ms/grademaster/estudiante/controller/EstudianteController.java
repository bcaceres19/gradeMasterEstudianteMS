package com.ms.grademaster.estudiante.controller;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.service.IGenerarRecursos;
import com.ms.grademaster.comons.utils.constants.Constantes;
import com.ms.grademaster.comons.utils.enums.EstadoRespuestaEnum;
import com.ms.grademaster.comons.utils.enums.TipoUsuarioEnum;
import com.ms.grademaster.estudiante.service.ICrearEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class EstudianteController {

    private final ICrearEstudianteService crearEstudianteService;

    private final IGenerarRecursos iGenerarRecursos;

    @PostMapping("crear")
    public ResponseEntity<RespuestaGeneralDto> crearEstudiante(@RequestBody EstudianteDto estudianteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        crearEstudianteService.crearEstudiante(estudianteDto);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("codigo")
    public ResponseEntity<RespuestaGeneralDto> generarCodigo(@RequestBody EstudianteDto estudianteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iGenerarRecursos.generarCodigo(estudianteDto, TipoUsuarioEnum.ESTUDIANTE, Constantes.INICIALES_ESTUDIANTE));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("correo")
    public ResponseEntity<RespuestaGeneralDto> generarCorreo(@RequestBody EstudianteDto estudianteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iGenerarRecursos.generarCorreo(estudianteDto, TipoUsuarioEnum.ESTUDIANTE));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

}
