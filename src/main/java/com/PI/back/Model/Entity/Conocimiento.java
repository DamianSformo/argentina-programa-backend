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
@JsonIgnoreProperties({"conocimiento"})
public class Conocimiento {

    @Id
    @SequenceGenerator(name = "conocimientoSequence",sequenceName = "conocimientoSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conocimientoSequence")
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "conocimiento")
    @ToString.Exclude
    private Set<PersonaConocimiento> conocimiento;
}
