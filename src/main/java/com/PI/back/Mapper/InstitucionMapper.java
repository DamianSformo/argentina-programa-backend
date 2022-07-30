package com.PI.back.Mapper;

import com.PI.back.Model.DTO.InstitucionDto.InstitucionDto;
import com.PI.back.Model.Entity.Institucion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InstitucionMapper {

    public abstract InstitucionDto toDto(Institucion institucion);
    public abstract Institucion toEntity(InstitucionDto institucionDto);
}
