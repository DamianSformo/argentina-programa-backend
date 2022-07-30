package com.PI.back.Service.Interfaces;

import com.PI.back.Model.DTO.EducacionDto.EducacionDto;
import com.PI.back.Model.DTO.EducacionDto.EducacionInicioDto;
import com.PI.back.Service.IService;
import java.util.List;

public interface IEducacionService extends IService<EducacionDto> {

    List<EducacionInicioDto> findEducacionByPersonaInicio(Long id);
    List<EducacionDto> findEducacionByPersonaCompleto(Long id);
}
