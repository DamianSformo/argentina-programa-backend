package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.IdiomaDto.IdiomaDto;
import com.PI.back.Model.Entity.Idioma;
import com.PI.back.Repository.IIdiomaRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class IdiomaMapper implements IValidador<Idioma>{

    @Autowired
    IIdiomaRepository repository;

    @BeforeMapping
    public Idioma validador(Long id) throws ResourceNotFoundException {
        Optional<Idioma> idioma = repository.findById(id);
        if (idioma.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return idioma.get();
    }

    public abstract IdiomaDto toDto(Idioma idioma);
    public abstract Idioma toEntity(IdiomaDto idiomaDto);
}
