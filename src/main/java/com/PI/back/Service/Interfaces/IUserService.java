package com.PI.back.Service.Interfaces;

import com.PI.back.Exceptions.BadRequestException;
import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Model.DTO.Post.AuthenticationRequest;
import com.PI.back.Model.DTO.UserDto.UserDto;
import com.PI.back.Model.DTO.UserBookingDto;
import com.PI.back.Model.DTO.UserCardDto;
import com.PI.back.Model.Entity.User;
import com.PI.back.Mapper.IValidador;
import com.PI.back.Model.DTO.UserDto.UserPersonaDto;
import com.PI.back.Service.IService;

import java.util.List;

public interface IUserService extends IService<UserDto>, IValidador<User> {
    UserDto findByEmail(String email);
    UserBookingDto findById(Long id) throws ResourceNotFoundException;
    UserCardDto validate(AuthenticationRequest authenticationRequest, UserDto userdto) throws BadRequestException;
    UserCardDto authenticate(AuthenticationRequest authenticationRequest) throws BadRequestException;

    UserPersonaDto savepersona(UserPersonaDto userPersonaDto);
    List<String> findEmails();
}
