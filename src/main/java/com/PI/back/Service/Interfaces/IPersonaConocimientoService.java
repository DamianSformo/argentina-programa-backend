package com.PI.back.Service.Interfaces;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoDto;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoInicioDto;
import com.PI.back.Service.IService;

import java.util.List;

public interface IPersonaConocimientoService extends IService<PersonaConocimientoDto> {

    //ExperienciaDto findById(Long id) throws ResourceNotFoundException;
    List<PersonaConocimientoInicioDto> findConocimientoByPersonaForInicio(Long usuarioId) throws ResourceNotFoundException;
    //List<ExperienciaDto> findExperienciaByPersona(Long id) throws ResourceNotFoundException;
}
