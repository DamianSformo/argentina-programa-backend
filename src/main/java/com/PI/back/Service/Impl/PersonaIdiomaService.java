package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.PersonaIdiomaMapper;
import com.PI.back.Model.DTO.IdiomaDto.IdiomaDto;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaDto;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaInicioDto;
import com.PI.back.Model.Entity.Idioma;
import com.PI.back.Model.Entity.PersonaIdioma;
import com.PI.back.Repository.IIdiomaRepository;
import com.PI.back.Repository.IPersonaIdiomaRepository;
import com.PI.back.Service.Interfaces.IIdiomaService;
import com.PI.back.Service.Interfaces.IPersonaIdiomaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PersonaIdiomaService implements IPersonaIdiomaService {

    protected final static Logger logger = Logger.getLogger(PersonaService.class);

    @Autowired
    private IPersonaIdiomaRepository repository;

    @Autowired
    private IIdiomaRepository idiomaRepository;

    @Autowired
    private IIdiomaService idiomaService;

    @Autowired
    private PersonaIdiomaMapper mapper;

    @Override
    public PersonaIdiomaDto save(PersonaIdiomaDto personaIdiomaDto) {
        PersonaIdioma personaIdioma = mapper.toEntity(personaIdiomaDto);

        String nombre = (personaIdiomaDto.getIdioma().getNombre()).toLowerCase(Locale.ROOT);
        Optional<Idioma> idioma = idiomaRepository.getIdiomaByNombre(nombre);
        if(idioma.isPresent()){
            personaIdioma.getIdioma().setId(idioma.get().getId());
        } else {
            IdiomaDto idiomaDto = IdiomaDto.builder()
                    .nombre(nombre)
                    .build();
            idiomaService.save(idiomaDto);
            personaIdioma.getIdioma().setId(idiomaDto.getId());
        }

        repository.save(personaIdioma);

        if (personaIdiomaDto.getId() == null){
            personaIdiomaDto.setId(personaIdiomaDto.getId());
            logger.info("Idioma registrado correctamente: "+ personaIdiomaDto);
        }else{
            logger.info("Idioma actualizado correctamente: "+ personaIdiomaDto);
        }
        return personaIdiomaDto;
    }

    @Override
    public List<PersonaIdiomaInicioDto> findIdiomaByPersonaForInicio(Long usuarioId) throws ResourceNotFoundException {
        List<PersonaIdioma> personaIdiomas = repository.findIdiomaByPersona(usuarioId);
        List<PersonaIdiomaInicioDto> personaIdiomasInicioDto = new ArrayList<>();
        for (PersonaIdioma personaIdioma : personaIdiomas) {
            personaIdiomasInicioDto.add(mapper.toPersonaIdiomaInicioDto(personaIdioma));
        }
        logger.info("Búsqueda exitosa: " +  personaIdiomasInicioDto);
        return  personaIdiomasInicioDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó el Idioma correctamente: id("+id+")");
    }
}
