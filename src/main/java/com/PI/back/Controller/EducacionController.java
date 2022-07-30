package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.EducacionDto.EducacionDto;
import com.PI.back.Model.DTO.EducacionDto.EducacionInicioDto;
import com.PI.back.Service.Interfaces.IEducacionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("educacion/")
public class EducacionController {

    @Autowired
    private IEducacionService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar una Educación")
    @PostMapping
    public ResponseEntity<EducacionDto> save(@RequestBody EducacionDto educacionDto) {
        if(educacionDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(educacionDto));
        else
            return ResponseEntity.ok(service.save(educacionDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todas las Educaciones de una Persona para el inicio")
    @GetMapping("inicio/{personaId}")
    public ResponseEntity<List<EducacionInicioDto>> findEducacionByPersonaInicio(@PathVariable Long personaId){
        return ResponseEntity.ok(service.findEducacionByPersonaInicio(personaId));
    }

    @Operation(summary = "Traer todas las Educaciones completas de una Persona")
    @GetMapping("completo/{personaId}")
    public ResponseEntity<List<EducacionDto>> findEducacionByPersonaCompleto(@PathVariable Long personaId){
        return ResponseEntity.ok(service.findEducacionByPersonaCompleto(personaId));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar Educación por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
