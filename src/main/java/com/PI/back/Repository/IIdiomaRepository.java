package com.PI.back.Repository;

import com.PI.back.Model.Entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IIdiomaRepository extends JpaRepository<Idioma, Long> {

    @Query("SELECT i FROM Idioma i WHERE i.nombre= ?1")
    Optional<Idioma> getIdiomaByNombre(String nombre);
}
