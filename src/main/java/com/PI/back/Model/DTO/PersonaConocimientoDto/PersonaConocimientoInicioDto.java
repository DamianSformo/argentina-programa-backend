package com.PI.back.Model.DTO.PersonaConocimientoDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class PersonaConocimientoInicioDto {

    private Long id;
    private String nombreConocimiento;
    private String tipoConocimiento;
}
