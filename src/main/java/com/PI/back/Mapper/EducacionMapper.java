package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.EducacionDto.EducacionDto;
import com.PI.back.Model.DTO.EducacionDto.EducacionInicioDto;
import com.PI.back.Model.Entity.Educacion;
import com.PI.back.Repository.IEducacionRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class EducacionMapper implements IValidador<Educacion> {

    @Autowired
    IEducacionRepository repository;

    @BeforeMapping
    public Educacion validador(Long id) throws ResourceNotFoundException {
        Optional<Educacion> educacion = repository.findById(id);
        if (educacion.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return educacion.get();
    }

    public abstract EducacionDto toDto(Educacion educacion);
    public abstract Educacion toEntity(EducacionDto educacionDto);

    public EducacionInicioDto toEducacionPresentacionDto(Educacion educacion){
        return EducacionInicioDto.builder()
                .id(educacion.getId())
                .titulo(educacion.getTitulo())
                .institucionImg(educacion.getInstitucion().getImg())
                .institucionNombre(educacion.getInstitucion().getNombre())
                .disciplina(educacion.getDisciplina())
                .inicio(educacion.getInicio())
                .finalizacion(educacion.getFinalizacion())
                .actualmenteCursando(educacion.getActualmenteCursando())
                .build();
    }
}
