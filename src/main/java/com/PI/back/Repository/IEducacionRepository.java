package com.PI.back.Repository;

import com.PI.back.Model.Entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Long> {

    @Query("SELECT e FROM Educacion e where e.persona.id=?1 ORDER BY e.inicio DESC")
    List<Educacion> getEducacionByPersona(Long id);
}
