package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.ConocimientoMapper;
import com.PI.back.Model.DTO.ConocimientoDto.ConocimientoDto;
import com.PI.back.Model.Entity.Conocimiento;
import com.PI.back.Repository.IConocimientoRepository;
import com.PI.back.Service.Interfaces.IConocimientoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConocimientoService implements IConocimientoService {

    protected final static Logger logger = Logger.getLogger(ConocimientoService.class);

    @Autowired
    private IConocimientoRepository repository;
    @Autowired
    private ConocimientoMapper mapper;

    @Override
    public ConocimientoDto save(ConocimientoDto conocimientoDto) {
        Conocimiento conocimiento = mapper.toEntity(conocimientoDto);
        repository.save(conocimiento);

        if (conocimientoDto.getId() == null){
            conocimientoDto.setId(conocimiento.getId());
            logger.info("Conocimiento registrado correctamente: "+ conocimientoDto);
        }else{
            logger.info("Conocimiento actualizado correctamente: "+ conocimientoDto);
        }
        return conocimientoDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó el Conocimiento correctamente: id("+id+")");
    }
}
