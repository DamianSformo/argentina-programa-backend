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
@JsonIgnoreProperties({"experiencia", "educacion", "conocimiento", "idioma"})
public class Persona {

    @Id
    @SequenceGenerator(name = "personaSequence",sequenceName = "personaSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaSequence")
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    @Column(nullable = false)
    private String descripcion;
    private Boolean buscandoTrabajo;
    private String banner;

    private Boolean verEmail;
    private Long numeroWhatsapp;
    private Boolean verNumeroWhatsapp;
    private String linkedin;
    private Boolean verLinkedin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    private Direccion direccion;
    private Boolean verDireccion;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Experiencia> experiencia;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Educacion> educacion;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<PersonaConocimiento> conocimiento;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<PersonaIdioma> idioma;

}
