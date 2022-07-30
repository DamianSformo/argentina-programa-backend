package com.PI.back.Service.Interfaces;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaDto.PersonaDto;
import com.PI.back.Model.DTO.PersonaDto.PersonaHeaderDto;
import com.PI.back.Service.IService;

public interface IPersonaService extends IService<PersonaDto>{

    PersonaDto findByIdInicio(Long id) throws ResourceNotFoundException;
    PersonaHeaderDto findByIdHeader(Long id) throws ResourceNotFoundException;
}
