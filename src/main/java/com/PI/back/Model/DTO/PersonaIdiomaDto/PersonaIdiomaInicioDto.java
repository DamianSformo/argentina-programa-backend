package com.PI.back.Model.DTO.PersonaIdiomaDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class PersonaIdiomaInicioDto {

    private Long id;
    private String nombreIdioma;
    private String competenciaIdioma;
}
