package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoDto;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoInicioDto;
import com.PI.back.Model.Entity.PersonaConocimiento;
import com.PI.back.Repository.IPersonaConocimientoRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PersonaConocimientoMapper implements IValidador<PersonaConocimiento> {

    @Autowired
    IPersonaConocimientoRepository repository;

    @BeforeMapping
    public PersonaConocimiento validador(Long id) throws ResourceNotFoundException {
        Optional<PersonaConocimiento> personaConocimiento = repository.findById(id);
        if (personaConocimiento.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return personaConocimiento.get();
    }

    public abstract PersonaConocimientoDto toDto(PersonaConocimiento personaConocimiento);
    public abstract PersonaConocimiento toEntity(PersonaConocimientoDto personaConocimientoDto);

    public PersonaConocimientoInicioDto toPersonaConocimientoInicioDto(PersonaConocimiento personaConocimiento) {
        return PersonaConocimientoInicioDto.builder()
                .id(personaConocimiento.getId())
                .nombreConocimiento(personaConocimiento.getConocimiento().getNombre())
                .tipoConocimiento(personaConocimiento.getTipo())
                .build();
    }
}