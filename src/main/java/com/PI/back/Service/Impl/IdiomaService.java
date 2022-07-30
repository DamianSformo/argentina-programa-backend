package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.IdiomaMapper;
import com.PI.back.Model.DTO.IdiomaDto.IdiomaDto;
import com.PI.back.Model.Entity.Idioma;
import com.PI.back.Repository.IIdiomaRepository;
import com.PI.back.Service.Interfaces.IIdiomaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IdiomaService implements IIdiomaService {

    protected final static Logger logger = Logger.getLogger(IdiomaService.class);

    @Autowired
    private IIdiomaRepository repository;
    @Autowired
    private IdiomaMapper mapper;

    @Override
    public IdiomaDto save(IdiomaDto idiomaDto) {
        Idioma idioma = mapper.toEntity(idiomaDto);
        repository.save(idioma);

        if (idiomaDto.getId() == null){
            idiomaDto.setId(idioma.getId());
            logger.info("Idioma registrado correctamente: "+ idiomaDto);
        }else{
            logger.info("Idioma actualizado correctamente: "+ idiomaDto);
        }
        return idiomaDto;
    }

    @Override
    public Set<IdiomaDto> findAll() {
        Set<IdiomaDto> idiomasDto = new HashSet<>();
        List<Idioma> idiomas = repository.findAll();
        for (Idioma idioma : idiomas) {
            idiomasDto.add(mapper.toDto(idioma));
        }
        logger.info("La búsqueda fue exitosa: "+ idiomasDto);
        return idiomasDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó el Idioma correctamente: id("+id+")");
    }

}
