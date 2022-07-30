package com.PI.back.Controller;

import com.PI.back.Model.DTO.InstitucionDto.InstitucionDto;
import com.PI.back.Service.Interfaces.IInstitucionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/institucion")
public class InstitucionController {

    @Autowired
    private IInstitucionService service;

    //* ///////// GET ///////// *//

    @Operation(summary = "Traer todos las Instituciones")
    @GetMapping
    public ResponseEntity<List<InstitucionDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
