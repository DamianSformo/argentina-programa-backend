package com.PI.back.Model.DTO.PersonaDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class PersonaHeaderDto {

    private Long id;
    private String nombreYApellido;
    private String descripcion;

}
