package com.PI.back.Repository;

import com.PI.back.Model.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository  extends JpaRepository<Rol,Long> {

}
