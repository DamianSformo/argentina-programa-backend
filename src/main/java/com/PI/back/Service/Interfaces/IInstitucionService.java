package com.PI.back.Service.Interfaces;

import com.PI.back.Model.DTO.InstitucionDto.InstitucionDto;

import java.util.List;

public interface IInstitucionService{

    InstitucionDto save(InstitucionDto institucionDto);
    List<InstitucionDto> findAll();
}
