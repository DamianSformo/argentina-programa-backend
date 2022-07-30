package com.PI.back.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.Year;

@ToString
@Getter
@Setter
@Entity
@Table
public class Experiencia {

    @Id
    @SequenceGenerator(name = "experienciaSequence",sequenceName = "experienciaSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experienciaSequence")
    private Long id;
    @Column(nullable = false)
    private String cargo;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String nombreDeEmpresa;
    private String imgDeEmpresa;
    private String ubicacion;
    @Column(nullable = false)
    private String mesDeInicio;
    @Column(nullable = false)
    private Year anioDeInicio;
    private String mesDeFinalizacion;
    private Year anioDeFinalizacion;
    private Boolean actualmenteEnElCargo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;
}
