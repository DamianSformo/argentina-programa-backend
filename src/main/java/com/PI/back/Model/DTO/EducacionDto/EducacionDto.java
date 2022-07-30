package com.PI.back.Model.DTO.EducacionDto;

import com.PI.back.Model.Entity.Institucion;
import com.PI.back.Model.Entity.Persona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.Year;

@ToString
@Getter
@Setter
public class EducacionDto {

    private Long id;
    private String titulo;
    private String disciplina;
    private Double nota;
    private Year inicio;
    private Year finalizacion;
    private Boolean actualmenteCursando;
    private String descripcion;
    private Institucion institucion;
    private Persona persona;
}
