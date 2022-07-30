package com.PI.back.Model.DTO.ConocimientoDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class ConocimientoDto {

    private Long id;
    private String nombre;
}
