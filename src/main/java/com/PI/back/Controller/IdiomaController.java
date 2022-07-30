package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.IdiomaDto.IdiomaDto;
import com.PI.back.Service.Interfaces.IIdiomaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/idioma")
public class IdiomaController {

    @Autowired
    private IIdiomaService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar un Idioma")
    @PostMapping
    public ResponseEntity<IdiomaDto> save(@RequestBody IdiomaDto idiomaDto){
        if(idiomaDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(idiomaDto));
        else
            return ResponseEntity.ok(service.save(idiomaDto));
    }

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todos los Idiomas")
    @GetMapping
    public ResponseEntity<Set<IdiomaDto>> findAll() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Prueba")
    @GetMapping("/hola")
    public String hola() throws ResourceNotFoundException {
        return "hola";
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar Idioma por Id")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
