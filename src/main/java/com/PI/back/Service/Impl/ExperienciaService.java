package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.ExperienciaMapper;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaDto;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaInicioDto;
import com.PI.back.Model.Entity.Experiencia;
import com.PI.back.Repository.IExperienciaRepository;
import com.PI.back.Service.Interfaces.IExperienciaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienciaService implements IExperienciaService {

    protected final static Logger logger = Logger.getLogger(PersonaService.class);

    @Autowired
    private IExperienciaRepository repository;

    @Autowired
    private ExperienciaMapper mapper;

    @Override
    public ExperienciaDto save(ExperienciaDto experienciaDto) {
        Experiencia experiencia = mapper.toEntity(experienciaDto);
        repository.save(experiencia);

        if (experienciaDto.getId() == null){
            experienciaDto.setId(experiencia.getId());
            logger.info("Experiencia registrado correctamente: "+ experienciaDto);
        }else{
            logger.info("Experiencia actualizado correctamente: "+ experienciaDto);
        }
        return experienciaDto;
    }

    @Override
    public List<ExperienciaInicioDto> findExperienciaByPersonaInicio(Long id){
        List<Experiencia> experiencias = repository.findExperienciaByPersona(id);
        List<ExperienciaInicioDto> experienciasDtos = new ArrayList<>();

        for (Experiencia experiencia : experiencias) {
            experienciasDtos.add(mapper.toExperienciaPresentacionDto(experiencia));
        }

        logger.info("Búsqueda exitosa: " + experienciasDtos);
        return experienciasDtos;
    }

    @Override
    public List<ExperienciaDto> findExperienciaByPersonaCompleto(Long id){
        List<Experiencia> experiencias = repository.findExperienciaByPersona(id);
        List<ExperienciaDto> experienciasDtos = new ArrayList<>();

        for (Experiencia experiencia : experiencias) {
            experienciasDtos.add(mapper.toDto(experiencia));
        }

        logger.info("Búsqueda exitosa: " + experienciasDtos);
        return experienciasDtos;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó la Experiencia correctamente: id("+id+")");
    }
}
