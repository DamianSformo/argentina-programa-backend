package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.Post.RolDto;
import com.PI.back.Model.Entity.Rol;
import com.PI.back.Repository.IRolRepository;
import com.PI.back.Service.Interfaces.IRolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService implements IRolService {
    protected final static Logger logger = Logger.getLogger(RolService.class);

    @Autowired
    private IRolRepository rolRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public RolDto save(RolDto rolDto) {
        Rol rol = mapper.convertValue(rolDto, Rol.class);
        rolRepository.save(rol);
        if (rolDto.getId() == null){
            rolDto.setId(rol.getId());
            logger.info("Rol registrado correctamente: "+ rolDto);
        }else{
            logger.info("Rol actualizado correctamente: "+ rolDto);
        }
        return rolDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        checkId(id);
        rolRepository.deleteById(id);
        logger.info("Se elimino el Rol correctamente: id("+id+")");
    }

    @Override
    public Rol checkId(Long id) throws ResourceNotFoundException {
        Optional<Rol> rol = rolRepository.findById(id);
        if (rol.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return rol.get();
    }
}
