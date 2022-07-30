package com.PI.back.Model.DTO.PersonaIdiomaDto;

import com.PI.back.Model.Entity.Idioma;
import com.PI.back.Model.Entity.Persona;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class PersonaIdiomaDto {

    private Long id;
    private Persona persona;
    private Idioma idioma;
    private String competencia;
}
