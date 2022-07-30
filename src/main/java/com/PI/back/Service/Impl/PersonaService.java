package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.PersonaMapper;
import com.PI.back.Model.DTO.PersonaDto.PersonaDto;
import com.PI.back.Model.DTO.PersonaDto.PersonaHeaderDto;
import com.PI.back.Model.Entity.Persona;
import com.PI.back.Repository.IPersonaRepository;
import com.PI.back.Service.Interfaces.IPersonaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {
    protected final static Logger logger = Logger.getLogger(PersonaService.class);

    @Autowired
    private IPersonaRepository repository;
    @Autowired
    private PersonaMapper mapper;

    @Override
    public PersonaDto save(PersonaDto personaDto) {
        Persona persona = mapper.toEntity(personaDto);
        repository.save(persona);
        if (personaDto.getId() == null){
            personaDto.setId(persona.getId());
            logger.info("Persona registrada correctamente: "+ personaDto);
        }else{
            logger.info("Persona actualizada correctamente: "+ personaDto);
        }
        return personaDto;
    }

    @Override
    public PersonaDto findByIdInicio(Long id) throws ResourceNotFoundException {
        Persona persona = mapper.validador(id);
        PersonaDto personaDto = mapper.toDto(persona);

        logger.info("Búsqueda exitosa: " + personaDto);
        return personaDto;
    }

    @Override
    public PersonaHeaderDto findByIdHeader(Long id) throws ResourceNotFoundException {
        Persona persona = mapper.validador(id);
        PersonaHeaderDto personaHeaderDto = mapper.toPersonaHeaderDto(persona);

        logger.info("Búsqueda exitosa: " + personaHeaderDto);
        return personaHeaderDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó la Persona correctamente: id("+id+")");
    }

}
