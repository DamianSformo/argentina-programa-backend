package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.EducacionMapper;
import com.PI.back.Model.DTO.EducacionDto.EducacionDto;
import com.PI.back.Model.DTO.EducacionDto.EducacionInicioDto;
import com.PI.back.Model.DTO.InstitucionDto.InstitucionDto;
import com.PI.back.Model.Entity.Educacion;
import com.PI.back.Model.Entity.Institucion;
import com.PI.back.Repository.IEducacionRepository;
import com.PI.back.Repository.IInstitucionRepository;
import com.PI.back.Service.Interfaces.IEducacionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EducacionService implements IEducacionService {

    protected final static Logger logger = Logger.getLogger(PersonaService.class);

    @Autowired
    private IEducacionRepository repository;

    @Autowired
    private IInstitucionRepository institucionRepository;

    @Autowired
    private InstitucionService institucionService;

    @Autowired
    private EducacionMapper mapper;

    @Override
    public EducacionDto save(EducacionDto educacionDto) {
        Educacion educacion = mapper.toEntity(educacionDto);

        String nombre = (educacionDto.getInstitucion().getNombre()).toLowerCase(Locale.ROOT);
        Optional<Institucion> institucion = institucionRepository.getInstitucionByNombre(nombre);
        if(institucion.isPresent()){
            educacion.getInstitucion().setId(institucion.get().getId());
        } else {
            InstitucionDto institucionDto = InstitucionDto.builder()
                    .nombre(educacionDto.getInstitucion().getNombre())
                    .pais(educacionDto.getInstitucion().getPais())
                    .ciudad(educacionDto.getInstitucion().getCiudad())
                    .img(educacionDto.getInstitucion().getImg())
                    .build();
            institucionService.save(institucionDto);
            educacion.getInstitucion().setId(institucionDto.getId());
        }

        repository.save(educacion);

        if (educacionDto.getId() == null){
            educacionDto.setId(educacion.getId());
            logger.info("Educación registrada correctamente: "+ educacionDto);
        }else{
            logger.info("Educación actualizada correctamente: "+ educacionDto);
        }
        return educacionDto;
    }

    @Override
    public List<EducacionInicioDto> findEducacionByPersonaInicio(Long id){
        List<Educacion> educaciones = repository.getEducacionByPersona(id);
        List<EducacionInicioDto> educacionesInicioDto = new ArrayList<>();

        for (Educacion educacion : educaciones) {
            educacionesInicioDto.add(mapper.toEducacionPresentacionDto(educacion));
        }

        logger.info("Búsqueda exitosa: " + educacionesInicioDto);
        return educacionesInicioDto;
    }

    @Override
    public List<EducacionDto> findEducacionByPersonaCompleto(Long usuarioId){
        List<Educacion> educaciones = repository.getEducacionByPersona(usuarioId);
        List<EducacionDto> educacionesDto = new ArrayList<>();

        for (Educacion educacion : educaciones) {
            educacionesDto.add(mapper.toDto(educacion));
        }

        logger.info("Búsqueda exitosa: " + educacionesDto);
        return educacionesDto;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        mapper.validador(id);
        repository.deleteById(id);
        logger.info("Se eliminó la Educación correctamente: id("+id+")");
    }
}