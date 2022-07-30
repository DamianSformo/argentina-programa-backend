package com.PI.back.Repository;

import com.PI.back.Model.Entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInstitucionRepository extends JpaRepository<Institucion, Long> {

    @Query("SELECT i FROM Institucion i WHERE i.nombre= ?1")
    Optional<Institucion> getInstitucionByNombre(String nombre);
}
