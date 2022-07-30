package com.PI.back.Service.Impl;

import com.PI.back.Mapper.InstitucionMapper;
import com.PI.back.Model.DTO.InstitucionDto.InstitucionDto;
import com.PI.back.Model.Entity.Institucion;
import com.PI.back.Repository.IInstitucionRepository;
import com.PI.back.Service.Interfaces.IInstitucionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitucionService implements IInstitucionService {
    protected final static Logger logger = Logger.getLogger(InstitucionService.class);

    @Autowired
    private IInstitucionRepository repository;
    @Autowired
    private InstitucionMapper mapper;

    @Override
    public InstitucionDto save(InstitucionDto institucionDto) {
        Institucion institucion = mapper.toEntity(institucionDto);
        repository.save(institucion);

        if (institucionDto.getId() == null){
            institucionDto.setId(institucion.getId());
            logger.info("Institución registrada correctamente: "+ institucionDto);
        }else{
            logger.info("Institución actualizada correctamente: "+ institucionDto);
        }
        return institucionDto;
    }

    @Override
    public List<InstitucionDto> findAll() {
        List<InstitucionDto> institucionesDto = new ArrayList<>();
        List<Institucion> instituciones = repository.findAll();
        for (Institucion institucion : instituciones) {
            institucionesDto.add(mapper.toDto(institucion));
        }
        logger.info("La búsqueda fue exitosa: "+ institucionesDto);
        return institucionesDto;
    }
}
