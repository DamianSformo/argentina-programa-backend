package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaDto;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaInicioDto;
import com.PI.back.Model.Entity.PersonaIdioma;
import com.PI.back.Repository.IPersonaIdiomaRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PersonaIdiomaMapper implements IValidador<PersonaIdioma>{

    @Autowired
    IPersonaIdiomaRepository repository;

    @BeforeMapping
    public PersonaIdioma validador(Long id) throws ResourceNotFoundException {
        Optional<PersonaIdioma> personaIdioma = repository.findById(id);
        if (personaIdioma.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return personaIdioma.get();
    }

    public abstract PersonaIdiomaDto toDto(PersonaIdioma personaIdioma);
    public abstract PersonaIdioma toEntity(PersonaIdiomaDto personaIdiomaDto);

    public PersonaIdiomaInicioDto toPersonaIdiomaInicioDto(PersonaIdioma personaIdioma) {
        return PersonaIdiomaInicioDto.builder()
                .id(personaIdioma.getId())
                .nombreIdioma(personaIdioma.getIdioma().getNombre())
                .competenciaIdioma(personaIdioma.getCompetencia())
                .build();
    }
}
