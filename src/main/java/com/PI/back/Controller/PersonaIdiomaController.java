package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaDto;
import com.PI.back.Model.DTO.PersonaIdiomaDto.PersonaIdiomaInicioDto;
import com.PI.back.Service.Interfaces.IPersonaIdiomaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("personaidioma/")
public class PersonaIdiomaController {

    @Autowired
    private IPersonaIdiomaService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar una PersonaIdioma")
    @PostMapping
    public ResponseEntity<PersonaIdiomaDto> save(@RequestBody PersonaIdiomaDto personaIdiomaDto){
        if(personaIdiomaDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(personaIdiomaDto));
        else
            return ResponseEntity.ok(service.save(personaIdiomaDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todas las PersonaIdioma de una Persona para el inicio")
    @GetMapping("inicio/{usuarioId}")
    public ResponseEntity<List<PersonaIdiomaInicioDto>> findConocimientoByPersonaForInicio(@PathVariable Long usuarioId) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findIdiomaByPersonaForInicio(usuarioId));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar PersonaIdioma por Id")
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
