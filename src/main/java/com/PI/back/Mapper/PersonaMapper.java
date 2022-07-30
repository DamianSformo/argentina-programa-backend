package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaDto.PersonaDto;
import com.PI.back.Model.DTO.PersonaDto.PersonaHeaderDto;
import com.PI.back.Model.DTO.UserDto.UserPersonaDto;
import com.PI.back.Model.Entity.Persona;
import com.PI.back.Repository.IPersonaRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PersonaMapper implements IValidador<Persona> {

    @Autowired
    IPersonaRepository repository;

    @BeforeMapping
    public Persona validador(Long id) throws ResourceNotFoundException {
        Optional<Persona> persona = repository.findById(id);
        if (persona.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return persona.get();
    }

    public abstract PersonaDto toDto(Persona persona);
    public abstract Persona toEntity(PersonaDto personaDto);

    public PersonaHeaderDto toPersonaHeaderDto(Persona persona){
        return PersonaHeaderDto.builder()
                .id(persona.getId())
                .nombreYApellido(persona.getNombre() + " " + persona.getApellido())
                .descripcion(persona.getDescripcion())
                .build();
    }

    public PersonaDto toPersonaDtoUser(UserPersonaDto userPersonaDto){
        return PersonaDto.builder()
                .nombre(userPersonaDto.getNombre())
                .apellido(userPersonaDto.getApellido())
                .tipoDocumento(userPersonaDto.getTipoDocumento())
                .numeroDocumento(userPersonaDto.getNumeroDocumento())
                .descripcion(userPersonaDto.getDescripcion())
                .direccion(userPersonaDto.getDireccion())
                .verNumeroWhatsapp(userPersonaDto.getVerNumeroWhatsapp())
                .verLinkedin(userPersonaDto.getVerLinkedin())
                .verEmail(userPersonaDto.getVerEmail())
                .verEmail(userPersonaDto.getVerEmail())
                .banner(userPersonaDto.getBanner())
                .build();
    }

}
