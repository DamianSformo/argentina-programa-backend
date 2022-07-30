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
public class PersonaIdioma {

    @Id
    @SequenceGenerator(name = "personaIdiomaSequence",sequenceName = "personaIdiomaSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaIdiomaSequence")
    private Long id;
    private String competencia;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idioma_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Idioma idioma;
}
