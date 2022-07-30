package com.PI.back.Model.DTO.ExperienciaDto;

import com.PI.back.Model.Entity.Persona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

@ToString
@Getter
@Setter
public class ExperienciaDto {

    //* ///////// ATRIBUTOS ///////// *//

    private Long id;
    private String cargo;
    private String tipo;
    private String sector;
    private String nombreDeEmpresa;
    private String imgDeEmpresa;
    private String ubicacion;
    private String mesDeInicio;
    private Year anioDeInicio;
    private String mesDeFinalizacion;
    private Year anioDeFinalizacion;
    private Boolean actualmenteEnElCargo;
    private String descripcion;
    private Persona persona;
}
