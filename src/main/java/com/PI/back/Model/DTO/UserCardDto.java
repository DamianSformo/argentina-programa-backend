package com.PI.back.Model.DTO;

import com.PI.back.Model.Entity.Persona;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserCardDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String rolName;
    private String jwt;
    private Persona persona;
    private Long idPersona;

    //Default
    public UserCardDto() {}
}
