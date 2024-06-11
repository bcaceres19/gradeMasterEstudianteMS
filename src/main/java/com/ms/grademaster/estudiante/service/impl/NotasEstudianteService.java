package com.ms.grademaster.estudiante.service.impl;

import com.ms.grademaster.comons.dto.NotaDto;
import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.comons.mapper.NotaMateriaMapper;
import com.ms.grademaster.estudiante.dto.NotasEstudianteDto;
import com.ms.grademaster.estudiante.dto.NotasSemestreDto;
import com.ms.grademaster.estudiante.repository.NotaMateriaRepository;
import com.ms.grademaster.estudiante.service.INotasEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasEstudianteService implements INotasEstudianteService {

    private final NotaMateriaRepository notaMateriaRepository;

    private final NotaMateriaMapper notaMateriaMapper;

    @Override
    public NotasSemestreDto notasCortesEstudiante(String codigoEstudiante, String codigoMateria) {
        NotasSemestreDto notasSemestreDto  = new NotasSemestreDto();
        List<NotasEstudianteDto> notasCortes = new ArrayList<>();
        BigDecimal notaFinalSemestre = BigDecimal.ZERO;
        BigDecimal notaFinalSemestreImg = BigDecimal.ZERO;
        boolean noData = false;
        for(int i = 1; i<=3; i++){
            BigDecimal notaFinalCorte = BigDecimal.valueOf(0.0);
            NotasEstudianteDto notasEstudianteDto = new NotasEstudianteDto();
            notasEstudianteDto.setNumeroCorte(String.valueOf(i));
            notasEstudianteDto.setNotaImaginaria(0.0);
            if (i == 3) {
                notasEstudianteDto.setPorcentajeCorte(40.0);
            } else {
                notasEstudianteDto.setPorcentajeCorte(30.0);
            }
            List<NotaMateriaDto> notaMateriasDtos = notaMateriaMapper.listObjectsToListDtoNota(notaMateriaRepository.notasMateriaEstudianteCorte((long) i,codigoEstudiante, codigoMateria));
            if(!notaMateriasDtos.isEmpty()){
                BigDecimal totalNota = notaMateriasDtos.stream()
                        .map(notaMateriaDto -> notaMateriaDto.getValorNota()
                                .multiply(notaMateriaDto.getCodigoNotaEntityFk().getPorcentajeNota().divide(BigDecimal.valueOf(100))))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                notaFinalCorte = totalNota;
                notaFinalSemestre = notaFinalSemestre.add(notaFinalCorte);
            }
            if(notaFinalCorte.doubleValue() == 0.0){
                double notaMinimaImg = 0.01;
                double totalImg;
                double notaMinimaCort;
                if(!noData){
                    notaMinimaCort = 3.0  * (notasEstudianteDto.getPorcentajeCorte() / 100);
                    switch (notasEstudianteDto.getNumeroCorte()){
                        case "1": {
                            noData = true;
                            break;
                        }case "2","3":{
                            double notaAct = notaMinimaCort;
                            for(NotasEstudianteDto nt : notasCortes){
                                double subNotaEstimada  = 3.0 * (nt.getPorcentajeCorte()/100);
                                double subNota = nt.getNotaCorte()* (nt.getPorcentajeCorte()/100);
                                if(subNota<subNotaEstimada && nt.getNotaImaginaria() == 0.0){
                                    notaAct+=subNotaEstimada-subNota;
                                } else if(nt.getNotaImaginaria() != 0.0){
                                    notaAct =notaMinimaCort;
                                    double notaImg = nt.getNotaImaginaria()* (nt.getPorcentajeCorte()/100);
                                    for(NotasEstudianteDto t : notasCortes){
                                        double subNotEstimadaAnt  = 3.0 * (t.getPorcentajeCorte()/100);
                                        double subNotaAnt = t.getNotaCorte()* (t.getPorcentajeCorte()/100);
                                        if(subNotaAnt<subNotEstimadaAnt && t.getNotaImaginaria() == 0.0){
                                            subNotaEstimada+=subNotEstimadaAnt-subNotaAnt;
                                        }
                                    }
                                    notaAct += subNotaEstimada-notaImg;
                                }
                            }
                            notaMinimaCort = notaAct;
                            break;
                        }
                    }
                }else{
                    notaMinimaCort = 3.0 * (notasEstudianteDto.getPorcentajeCorte() / 100);
                }
                do{
                    totalImg = notaMinimaImg * (notasEstudianteDto.getPorcentajeCorte() / 100);
                    notaMinimaImg += 0.01;
                    if((notasEstudianteDto.getNumeroCorte().equals("1") || notasEstudianteDto.getNumeroCorte().equals("2")) && notaMinimaImg >= 5.0){
                        notaMinimaImg = 5.0;
                        break;
                    }
                }while (totalImg <notaMinimaCort);
                BigDecimal bd = new BigDecimal(notaMinimaImg);
                notasEstudianteDto.setNotaImaginaria(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());
                notaFinalSemestreImg = notaFinalSemestreImg.add(bd.setScale(2, RoundingMode.HALF_UP));
            }
            notasEstudianteDto.setNotaCorte(notaFinalCorte.doubleValue());
            notasCortes.add(notasEstudianteDto);
        }
        notasSemestreDto.setNotasEstudianteDtos(notasCortes);
        notasSemestreDto.setNotaFinalSemestreImaginaria(3.0);
        notasSemestreDto.setNotaFinalSemestre(notaFinalSemestre.doubleValue());
        return notasSemestreDto;
    }

    @Override
    public List<NotaMateriaDto> notasMateriaEstudianteCorte(Long numeroCorte, String codigoEstudiante, String codigoMateria) {
        return notaMateriaMapper.listObjectsToListDtoNota(notaMateriaRepository.notasMateriaEstudianteCorte(numeroCorte, codigoEstudiante, codigoMateria));
    }
}
