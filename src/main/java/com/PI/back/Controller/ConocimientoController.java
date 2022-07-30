package com.PI.back.Controller;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.ConocimientoDto.ConocimientoDto;
import com.PI.back.Service.Interfaces.IConocimientoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/conocimiento")
public class ConocimientoController {

    @Autowired
    private IConocimientoService service;

    //* ///////// POST ///////// *//

    @Operation(summary = "Guardar o actualizar un Conocimiento")
    @PostMapping
    public ResponseEntity<ConocimientoDto> save(@RequestBody ConocimientoDto conocimientoDto){
        if(conocimientoDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conocimientoDto));
        else
            return ResponseEntity.ok(service.save(conocimientoDto));
    }

    //* ///////// DELETE ///////// *//

    @Operation(summary = "Eliminar Conocimiento por Id")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
