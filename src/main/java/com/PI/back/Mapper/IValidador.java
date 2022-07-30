package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;

public interface IValidador<T>{

    String msjError = "La búsqueda no arrojó resultados con id: ";

    T checkId(Long c) throws ResourceNotFoundException;


}
