package com.PI.back.Model.DTO.DireccionDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DireccionDto {

    private Long id;
    private String direccion;
    private Integer codigoPostal;
    private String ciudad;
    private String pais;

}
