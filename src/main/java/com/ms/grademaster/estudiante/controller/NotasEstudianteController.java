package com.ms.grademaster.estudiante.controller;

import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.utils.enums.EstadoRespuestaEnum;
import com.ms.grademaster.estudiante.service.INotasEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotasEstudianteController {


    private final INotasEstudianteService notasEstudianteService;

    @GetMapping("cortes-estudiante")
    public ResponseEntity<RespuestaGeneralDto> cortesEstudiante(@RequestParam("codigoEstudiante") String codigoEstudiante, @Param("codigoMateria") String codigoMateria){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(notasEstudianteService.notasCortesEstudiante(codigoEstudiante, codigoMateria));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("notas-corte")
    public ResponseEntity<RespuestaGeneralDto> notasCorte(@RequestParam("numeroCorte") Long numeroCorte, @RequestParam("codigoEstudiante") String codigoEstudiante, @RequestParam("codigoMateria") String codigoMateria){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(notasEstudianteService.notasMateriaEstudianteCorte(numeroCorte, codigoEstudiante, codigoMateria));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

}
