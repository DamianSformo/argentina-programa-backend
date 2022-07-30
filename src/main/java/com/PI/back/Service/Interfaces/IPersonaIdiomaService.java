package com.PI.back.Service.Interfaces;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaDto;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaInicioDto;
import com.PI.back.Service.IService;

import java.util.List;

public interface IPersonaIdiomaService extends IService<PersonaIdiomaDto> {

    List<PersonaIdiomaInicioDto> findIdiomaByPersonaForInicio(Long usuarioId) throws ResourceNotFoundException;
}
