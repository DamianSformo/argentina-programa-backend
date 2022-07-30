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
@JsonIgnoreProperties({"ratings","likes","bookings"})
public class User{

    @Id
    @SequenceGenerator(name = "userSequence",sequenceName = "userSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne()
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol" )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol rol;

}
