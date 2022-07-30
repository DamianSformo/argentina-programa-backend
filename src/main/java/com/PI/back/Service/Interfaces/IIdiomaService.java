package com.PI.back.Service.Interfaces;

import com.PI.back.Model.DTO.IdiomaDto.IdiomaDto;
import com.PI.back.Service.IService;

import java.util.Set;

public interface IIdiomaService extends IService<IdiomaDto> {

    Set<IdiomaDto> findAll();
}
