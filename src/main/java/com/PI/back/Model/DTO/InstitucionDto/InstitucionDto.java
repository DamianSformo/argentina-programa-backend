package com.PI.back.Model.DTO.InstitucionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class InstitucionDto {

    private Long id;
    private String nombre;
    private String pais;
    private String ciudad;
    private String img;
}
