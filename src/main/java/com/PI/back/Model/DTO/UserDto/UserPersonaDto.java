package com.PI.back.Model.DTO.UserDto;

import com.PI.back.Model.Entity.Direccion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class UserPersonaDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String descripcion;
    private Boolean buscandoTrabajo;
    private Direccion direccion;

    private Boolean verLinkedin;
    private Boolean verDireccion;
    private Boolean verEmail;
    private Boolean verNumeroWhatsapp;

    private String calle;
    private Integer codigoPostal;
    private String ciudad;
    private String pais;
    private String banner;

    private String email;
    private String password;
}
