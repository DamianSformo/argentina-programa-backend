package com.PI.back.Model.DTO.ExperienciaDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

@ToString
@Getter
@Setter
@Builder
public class ExperienciaInicioDto {

    private Long id;
    private String cargo;
    private String nombreDeEmpresa;
    private String imgDeEmpresa;
    private String tipo;
    private String mesDeInicio;
    private Year anioDeInicio;
    private String mesDeFinalizacion;
    private Year anioDeFinalizacion;
    private Boolean actualmenteEnElCargo;
    private String ubicacionDeEmpresa;

}
