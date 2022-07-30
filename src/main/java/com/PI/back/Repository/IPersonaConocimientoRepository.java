package com.PI.back.Repository;

import com.PI.back.Model.Entity.PersonaConocimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaConocimientoRepository extends JpaRepository<PersonaConocimiento, Long> {

    @Query("SELECT p FROM PersonaConocimiento p WHERE p.persona.id= ?1 ORDER BY p.conocimiento.nombre")
    List<PersonaConocimiento> findConocimientoByPersona(Long id);
}
