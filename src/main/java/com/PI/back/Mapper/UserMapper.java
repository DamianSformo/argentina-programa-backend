package com.PI.back.Mapper;

import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.Entity.User;
import com.PI.back.Repository.IUserRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements IValidador<User>{

    @Autowired
    IUserRepository repository;

    @BeforeMapping
    public User validador(Long id) throws ResourceNotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return user.get();
    }

}