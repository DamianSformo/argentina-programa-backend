package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoDto;
import com.PI.back.Model.DTO.PersonaConocimientoDto.PersonaConocimientoInicioDto;
import com.PI.back.Service.Interfaces.IPersonaConocimientoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("personaconocimiento/")
public class PersonaConocimientoController {

    @Autowired
    private IPersonaConocimientoService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar una PersonaConocimiento")
    @PostMapping
    public ResponseEntity<PersonaConocimientoDto> save(@RequestBody PersonaConocimientoDto personaConocimientoDto){
        if(personaConocimientoDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personaConocimientoDto));
        else
            return ResponseEntity.ok(service.save(personaConocimientoDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todas las PersonaConocimiento de una Persona para el inicio")
    @GetMapping("inicio/{usuarioId}")
    public ResponseEntity<List<PersonaConocimientoInicioDto>> findConocimientoByPersonaForInicio(@PathVariable Long usuarioId) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findConocimientoByPersonaForInicio(usuarioId));
    }

    @Operation(summary = "Traer todas las PersonaConocimiento completas de una Persona")
    @GetMapping("completo/{usuarioId}")
    public ResponseEntity<List<PersonaConocimientoInicioDto>> findExperienciaByPersona(@PathVariable Long usuarioId) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findConocimientoByPersonaForInicio(usuarioId));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar una PersonaConocimiento por Id")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
