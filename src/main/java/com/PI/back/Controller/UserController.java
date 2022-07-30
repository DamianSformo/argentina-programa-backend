package com.PI.back.Controller;

import com.PI.back.Exceptions.BadRequestException;
import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.Post.AuthenticationRequest;
import com.PI.back.Model.DTO.UserDto.UserDto;
import com.PI.back.Model.DTO.UserCardDto;
import com.PI.back.Model.DTO.UserDto.UserPersonaDto;
import com.PI.back.Service.Interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;


    //* ///////// NEW ///////// *//
    @Operation(summary = "Guardar o actualizar un Usuario")
    @PostMapping("/registro")
    public ResponseEntity<UserCardDto> saveconpersona(@RequestBody UserPersonaDto userPersonaDto) throws BadRequestException, MessagingException {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(userPersonaDto.getEmail());
        authenticationRequest.setPassword(userPersonaDto.getPassword());

        userService.savepersona(userPersonaDto);
        userService.authenticate(authenticationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.authenticate(authenticationRequest));
        /*
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(UserPersonaDto.getEmail());
        authenticationRequest.setPassword(UserPersonaDto.getPassword());

        if(UserPersonaDto.getId() == null){
            userService.save(userPersonaDto);
            UserCardDto user_cardDto =userService.authenticate(authenticationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(user_cardDto);
        } else{
            userService.save(UserPersonaDto);
            return ResponseEntity.ok(userService.authenticate(authenticationRequest));
        }
        */
    }

    @GetMapping("/emails")
    public ResponseEntity<List<String>> findEmails(){
        return ResponseEntity.ok(userService.findEmails());
    }

    //* ///////// POST ///////// *//
    @Operation(summary = "Guardar o actualizar un Usuario")
    @PostMapping
    public ResponseEntity<UserCardDto> save(@RequestBody UserDto userDto) throws BadRequestException, MessagingException {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(userDto.getEmail());
        authenticationRequest.setPassword(userDto.getPassword());

        if(userDto.getId() == null){
            userService.save(userDto);
            UserCardDto user_cardDto =userService.authenticate(authenticationRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(user_cardDto);
        } else{
            userService.save(userDto);
            return ResponseEntity.ok(userService.authenticate(authenticationRequest));
        }
    }

//    @Operation(summary = "Actualizar City de un Usuario")
//    @PostMapping("/city")
//    public ResponseEntity<UserDto> updateCity(@RequestBody UserDto userDto) throws BadRequestException, ResourceNotFoundException {
//        userService.updateCity(userDto);
//        return ResponseEntity.ok(userService.updateCity(userDto));
//    }

    @Operation(summary = "Log in de Usuario")
    @PostMapping("/authenticate")
    public ResponseEntity<UserCardDto> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadRequestException {
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }

    @Operation(summary = "Pasar rol de Pending a USER")
    @PostMapping("/validate")
    public ResponseEntity<UserCardDto> validateUser(@RequestBody AuthenticationRequest authenticationRequest) throws BadRequestException {
        UserDto userDto = userService.findByEmail(authenticationRequest.getEmail());
        return ResponseEntity.ok(userService.validate( authenticationRequest, userDto));
    }

    //* ///////// DELETE ///////// *//
    @Secured({"ADMIN"})
    @Operation(summary = "Eliminar un Usuario por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
