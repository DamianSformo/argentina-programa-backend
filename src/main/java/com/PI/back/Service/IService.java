package com.PI.back.Service;

import com.PI.back.Exceptions.ResourceNotFoundException;

public interface IService<T>{

    T save(T t);
    void delete(Long id) throws ResourceNotFoundException;
}
