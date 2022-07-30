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
public class Educacion {

    @Id
    @SequenceGenerator(name = "educacionSequence",sequenceName = "educacionSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "educacionSequence")
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String disciplina;
    private Double nota;
    @Column(nullable = false)
    private Year inicio;
    private Year finalizacion;
    private Boolean actualmenteCursando;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "institucion_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Institucion institucion;
}