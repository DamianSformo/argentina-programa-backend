package com.PI.back.Service.Interfaces;

import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaDto;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaInicioDto;
import com.PI.back.Service.IService;

import java.util.List;

public interface IExperienciaService extends IService<ExperienciaDto> {

    List<ExperienciaInicioDto> findExperienciaByPersonaInicio(Long id);
    List<ExperienciaDto> findExperienciaByPersonaCompleto(Long id);
}
