package com.PI.back.Model.DTO.PersonaDto;

import com.PI.back.Model.Entity.Direccion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@Builder
public class PersonaDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String descripcion;
    private Boolean buscandoTrabajo;
    private Direccion direccion;

    private Boolean verEmail;
    private Long numeroWhatsapp;
    private Boolean verNumeroWhatsapp;
    private String linkedin;
    private Boolean verLinkedin;
    private Boolean verDireccion;
    private String banner;

}
