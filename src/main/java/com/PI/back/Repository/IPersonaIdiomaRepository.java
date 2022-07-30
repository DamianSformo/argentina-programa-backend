package com.PI.back.Repository;

import com.PI.back.Model.Entity.PersonaIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaIdiomaRepository extends JpaRepository<PersonaIdioma, Long> {

    @Query("SELECT p FROM PersonaIdioma p WHERE p.persona.id= ?1 ORDER BY p.idioma.nombre")
    List<PersonaIdioma> findIdiomaByPersona(Long id);
}
