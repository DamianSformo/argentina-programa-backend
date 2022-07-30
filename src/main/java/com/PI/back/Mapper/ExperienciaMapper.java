package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaDto;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaInicioDto;
import com.PI.back.Model.Entity.Experiencia;
import com.PI.back.Repository.IExperienciaRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class ExperienciaMapper implements IValidador<Experiencia>{

    @Autowired
    IExperienciaRepository repository;

    @BeforeMapping
    public Experiencia validador(Long id) throws ResourceNotFoundException {
        Optional<Experiencia> experiencia = repository.findById(id);
        if (experiencia.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return experiencia.get();
    }

    public abstract ExperienciaDto toDto(Experiencia experiencia);
    public abstract Experiencia toEntity(ExperienciaDto experienciaDto);

    public ExperienciaInicioDto toExperienciaPresentacionDto(Experiencia experiencia){
        return ExperienciaInicioDto.builder()
                .id(experiencia.getId())
                .cargo(experiencia.getCargo())
                .nombreDeEmpresa(experiencia.getNombreDeEmpresa())
                .imgDeEmpresa(experiencia.getImgDeEmpresa())
                .tipo(experiencia.getTipo())
                .mesDeInicio(experiencia.getMesDeInicio())
                .anioDeInicio(experiencia.getAnioDeInicio())
                .mesDeFinalizacion(experiencia.getMesDeFinalizacion())
                .anioDeFinalizacion(experiencia.getAnioDeFinalizacion())
                .actualmenteEnElCargo(experiencia.getActualmenteEnElCargo())
                .ubicacionDeEmpresa(experiencia.getUbicacion())
                .build();
    }
}
