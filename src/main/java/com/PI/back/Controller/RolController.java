package com.PI.back.Controller;

import com.PI.back.Model.DTO.Post.RolDto;
import com.PI.back.Service.Interfaces.IRolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolController {
    @Autowired
    private IRolService rolService;

    //* ///////// POST ///////// *//
    @Secured({"ADMIN"})
    @Operation(summary = "Guardar o actualizar un Rol")
    @PostMapping
    public ResponseEntity<RolDto> save(@RequestBody RolDto rolDto) {
        if(rolDto.getId() == null)
            return ResponseEntity.status(HttpStatus.CREATED).body(rolService.save(rolDto));
        else
            return ResponseEntity.ok(rolService.save(rolDto));
    }
}
