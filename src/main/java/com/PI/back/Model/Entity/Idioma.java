package com.PI.back.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table
@JsonIgnoreProperties({"idioma"})
public class Idioma {

    @Id
    @SequenceGenerator(name = "idiomaSequence",sequenceName = "idiomaSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idiomaSequence")
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "idioma")
    @ToString.Exclude
    private Set<PersonaIdioma> idioma;

}
