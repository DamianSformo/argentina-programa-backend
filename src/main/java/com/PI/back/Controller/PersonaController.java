package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaDto.PersonaDto;
import com.PI.back.Model.DTO.PersonaDto.PersonaHeaderDto;
import com.PI.back.Service.Interfaces.IPersonaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
@CrossOrigin
public class PersonaController {

    @Autowired
    private IPersonaService service;

    //* ///////// POST ///////// *//
    @Operation(summary = "Guardar o actualizar una Persona")
    @PostMapping
    public ResponseEntity<PersonaDto> save(@RequestBody PersonaDto personaDto) {
        if(personaDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personaDto));
        else
            return ResponseEntity.ok(service.save(personaDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer una Persona por Id para el Inicio")
    @GetMapping("/inicio/{id}")
    public ResponseEntity<PersonaDto> findByIdInicio(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findByIdInicio(id));
    }

    @Operation(summary = "Traer una Persona por Id para el Header")
    @GetMapping("/header/{id}")
    public ResponseEntity<PersonaHeaderDto> findByIdHeader(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findByIdHeader(id));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar Persona por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}