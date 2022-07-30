package com.PI.back.Repository;

import com.PI.back.Model.Entity.Conocimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConocimientoRepository extends JpaRepository<Conocimiento, Long> {

    @Query("SELECT c FROM Conocimiento c WHERE c.nombre= ?1")
    Optional<Conocimiento> getConocimientoByNombre(String nombre);
}
