package com.PI.back.Model.DTO.PersonaConocimientoDto;

import com.PI.back.Model.Entity.Conocimiento;
import com.PI.back.Model.Entity.Persona;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class PersonaConocimientoDto {

    private Long id;
    private Persona persona;
    private Conocimiento conocimiento;
    private String tipo;
}
