package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.PersonaConocimientoMapper;
import com.PI.back.Model.DTO.ConocimientoDto.ConocimientoDto;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoDto;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoInicioDto;
import com.PI.back.Model.Entity.Conocimiento;
import com.PI.back.Model.Entity.PersonaConocimiento;
import com.PI.back.Repository.IConocimientoRepository;
import com.PI.back.Repository.IPersonaConocimientoRepository;
import com.PI.back.Service.Interfaces.IConocimientoService;
import com.PI.back.Service.Interfaces.IPersonaConocimientoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaConocimientoService implements IPersonaConocimientoService {

    protected final static Logger logger = Logger.getLogger(PersonaService.class);

    @Autowired
    private IPersonaConocimientoRepository repository;

    @Autowired
    private IConocimientoRepository conocimientoRepository;

    @Autowired
    private IConocimientoService conocimientoService;

    @Autowired
    private PersonaConocimientoMapper mapper;

    @Override
    public PersonaConocimientoDto save(PersonaConocimientoDto personaConocimientoDto) {
        PersonaConocimiento personaConocimiento = mapper.toEntity(personaConocimientoDto);

        String nombre = (personaConocimientoDto.getConocimiento().getNombre());
        Optional<Conocimiento> conocimiento = conocimientoRepository.getConocimientoByNombre(nombre);
        if(conocimiento.isPresent()){
            personaConocimiento.getConocimiento().setId(conocimiento.get().getId());
        } else {
            ConocimientoDto conocimientoDto = ConocimientoDto.builder()
                    .nombre(nombre)
                    .build();
            conocimientoService.save(conocimientoDto);
            personaConocimiento.getConocimiento().setId(conocimientoDto.getId());
        }

        repository.save(personaConocimiento);

        if (personaConocimientoDto.getId() == null){
            personaConocimientoDto.setId(personaConocimiento.getId());
            logger.info("Conocimiento registrado correctamente: "+ personaConocimientoDto);
        }else{
            logger.info("Conocimiento actualizado correctamente: "+ personaConocimientoDto);
        }
        return personaConocimientoDto;
    }

    @Override
    public List<PersonaConocimientoInicioDto> findConocimientoByPersonaForInicio(Long usuarioId) throws ResourceNotFoundException {
        List<PersonaConocimiento> personaConocimientos = repository.findConocimientoByPersona(usuarioId);
        List<PersonaConocimientoInicioDto> personaConocimientosInicioDto = new ArrayList<>();
        for (PersonaConocimiento personaConocimiento : personaConocimientos) {
            personaConocimientosInicioDto.add(mapper.toPersonaConocimientoInicioDto(personaConocimiento));
        }
        logger.info("Búsqueda exitosa: " +  personaConocimientosInicioDto);
        return  personaConocimientosInicioDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó el Conocimiento correctamente: id("+id+")");
    }
}
