package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaDto;
import com.PI.back.Model.DTO.ExperienciaDto.ExperienciaInicioDto;
import com.PI.back.Service.Interfaces.IExperienciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("experiencia/")
public class ExperienciaController {

    @Autowired
    private IExperienciaService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar una Experiencia")
    @PostMapping
    public ResponseEntity<ExperienciaDto> save(@RequestBody ExperienciaDto experienciaDto){
        if(experienciaDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(experienciaDto));
        else
            return ResponseEntity.ok(service.save(experienciaDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todas las Experiencias de una Persona para el inicio")
    @GetMapping("inicio/{personaId}")
    public ResponseEntity<List<ExperienciaInicioDto>> findExperienciaByPersonaInicio(@PathVariable Long personaId){
        return ResponseEntity.ok(service.findExperienciaByPersonaInicio(personaId));
    }

    @Operation(summary = "Traer todas las Experiencias completas de una Persona")
    @GetMapping("completo/{personaId}")
    public ResponseEntity<List<ExperienciaDto>> findExperienciaByPersonaCompleto(@PathVariable Long personaId){
        return ResponseEntity.ok(service.findExperienciaByPersonaCompleto(personaId));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar Experiencia por Id")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
