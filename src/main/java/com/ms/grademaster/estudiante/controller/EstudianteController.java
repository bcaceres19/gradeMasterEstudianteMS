package com.ms.grademaster.estudiante.controller;

import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.service.IGenerarRecursos;
import com.ms.grademaster.comons.utils.constants.Constantes;
import com.ms.grademaster.comons.utils.enums.EstadoRespuestaEnum;
import com.ms.grademaster.comons.utils.enums.TipoUsuarioEnum;
import com.ms.grademaster.estudiante.dto.AsignarMateriasDto;
import com.ms.grademaster.estudiante.service.IConsultaEstudianteService;
import com.ms.grademaster.estudiante.service.ICrearEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class EstudianteController {

    private final ICrearEstudianteService crearEstudianteService;

    private final IConsultaEstudianteService iConsultaEstudianteService;

    private final IGenerarRecursos iGenerarRecursos;

    @GetMapping("all-estudiantes-activos")
    public ResponseEntity<RespuestaGeneralDto> getAllEstudiantes(){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultaEstudianteService.consultarAllEstudiantes());
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("all-materias-estudiante-carrera")
    public ResponseEntity<RespuestaGeneralDto> getAllMateriasEstudianteCarrera(@RequestParam String codigoCarrera, @RequestParam String codigoEstudiante){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultaEstudianteService.buscarMateriasPorCarrera(codigoCarrera, codigoEstudiante));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("all-estudiantes-activos-nombre")
    public ResponseEntity<RespuestaGeneralDto> getAllEstudiantesActivos(@RequestParam String nombre){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultaEstudianteService.consultarAllEstudiantesNombre(nombre));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

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

    @PostMapping("asignar-materias")
    public ResponseEntity<RespuestaGeneralDto> asignarMateriasDocente(@RequestBody AsignarMateriasDto asignarMaterias){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        crearEstudianteService.asignarMateriasDocente(asignarMaterias);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("materias-estudiante")
    public ResponseEntity<RespuestaGeneralDto> getMateriasEstudiatne(@RequestParam String codigoEstudiante){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultaEstudianteService.materiasEstudiante(codigoEstudiante));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }
}
