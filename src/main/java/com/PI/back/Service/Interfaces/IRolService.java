package com.PI.back.Service.Interfaces;

import com.PI.back.Model.DTO.Post.RolDto;
import com.PI.back.Model.Entity.Rol;
import com.PI.back.Mapper.IValidador;
import com.PI.back.Service.IService;

public interface IRolService extends IService<RolDto>, IValidador<Rol> {
}
