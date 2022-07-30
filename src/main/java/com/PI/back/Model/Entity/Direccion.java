package com.PI.back.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "direccion")
public class Direccion {

    //* ///////// ATRIBUTOS ///////// *//

    @Id
    @SequenceGenerator(name = "direccionSequence",sequenceName = "direccionSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "direccionSequence")
    private Long id;
    private String direccion;
    private Integer codigoPostal;
    private String ciudad;
    private String pais;

}
