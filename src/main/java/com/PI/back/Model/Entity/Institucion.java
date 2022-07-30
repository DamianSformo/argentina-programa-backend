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
@JsonIgnoreProperties({"educacion"})
public class Institucion {

    @Id
    @SequenceGenerator(name = "institucionSequence",sequenceName = "institucionSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institucionSequence")
    private Long id;
    private String nombre;
    private String pais;
    private String ciudad;
    private String img;

    @OneToMany(mappedBy = "institucion")
    @ToString.Exclude
    private Set<Educacion> educacion;
}
