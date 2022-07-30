package com.PI.back.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table
public class PersonaConocimiento {

    @Id
    @SequenceGenerator(name = "personaConocimientoSequence",sequenceName = "personaConocimientoSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaConocimientoSequence")
    private Long id;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "conocimiento_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Conocimiento conocimiento;
}
