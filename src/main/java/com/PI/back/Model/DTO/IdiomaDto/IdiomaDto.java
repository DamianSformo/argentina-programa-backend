package com.PI.back.Model.DTO.IdiomaDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class IdiomaDto {

    private Long id;
    private String nombre;
}
