package com.PI.back.Model.DTO.EducacionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

@ToString
@Getter
@Setter
@Builder
public class EducacionInicioDto{

    private Long id;
    private String titulo;
    private String institucionImg;
    private String institucionNombre;
    private String disciplina;
    private Year inicio;
    private Year finalizacion;
    private Boolean actualmenteCursando;

}
