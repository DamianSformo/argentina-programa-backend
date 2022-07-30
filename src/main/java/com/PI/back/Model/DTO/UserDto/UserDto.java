package com.PI.back.Model.DTO.UserDto;

import com.PI.back.Model.Entity.Persona;
import com.PI.back.Model.Entity.Rol;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Rol rol;
    private Persona persona;

    //Default
    public UserDto() {}
}