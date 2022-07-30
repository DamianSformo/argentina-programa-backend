package com.PI.back.Repository;

import com.PI.back.Model.Entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Long> {

    @Query("SELECT e FROM Experiencia e WHERE e.persona.id= ?1 ORDER BY e.anioDeInicio DESC")
    List<Experiencia> findExperienciaByPersona(Long id);
}