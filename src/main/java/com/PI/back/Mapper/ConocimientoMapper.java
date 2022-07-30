package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.ConocimientoDto.ConocimientoDto;
import com.PI.back.Model.Entity.Conocimiento;
import com.PI.back.Repository.IConocimientoRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class ConocimientoMapper implements IValidador<Conocimiento>{

    @Autowired
    IConocimientoRepository repository;

    @BeforeMapping
    public Conocimiento validador(Long id) throws ResourceNotFoundException {
        Optional<Conocimiento> conocimiento = repository.findById(id);
        if (conocimiento.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return conocimiento.get();
    }

    public abstract ConocimientoDto toDto(Conocimiento conocimiento);
    public abstract Conocimiento toEntity(ConocimientoDto conocimientoDto);
}
