package com.PI.back.Service.Impl;

import com.PI.back.Exceptions.BadRequestException;
import com.PI.back.Exceptions.ResourceNotFoundException;
import com.PI.back.Mapper.PersonaMapper;
import com.PI.back.Mapper.UserMapper;
import com.PI.back.Model.DTO.PersonaDto.PersonaDto;
import com.PI.back.Model.DTO.Post.AuthenticationRequest;
import com.PI.back.Model.DTO.UserDto.UserDto;
import com.PI.back.Model.DTO.UserBookingDto;
import com.PI.back.Model.DTO.UserCardDto;
import com.PI.back.Model.Entity.Rol;
import com.PI.back.Model.Entity.User;
import com.PI.back.Model.DTO.UserDto.UserPersonaDto;
import com.PI.back.Model.DTO.UserDto.UserRoles;
import com.PI.back.Repository.IUserRepository;
import com.PI.back.Service.Interfaces.IUserService;
import com.PI.back.Service.Security.AuthenticationService;
import com.PI.back.Service.Security.MyPasswordEncoder;
import com.PI.back.Service.Security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    protected final static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        UserDto userDto = mapper.convertValue(user, UserDto.class);
        //userDto.setIdPersona(user.getPersona().getId());
        logger.info("La busqueda fue exitosa: "+ userDto);
        return userDto;
    }

    @Override
    public UserBookingDto findById(Long id) throws ResourceNotFoundException {
        User user = checkId(id);
        UserBookingDto userBookingDto = mapper.convertValue(user, UserBookingDto.class);
        userBookingDto.setEmail(user.getEmail());

        return userBookingDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        String hashedPassword = passwordEncoder.encodePassword(userDto.getPassword());
        userDto.setPassword(hashedPassword);
        User user = mapper.convertValue(userDto, User.class);
        if (userDto.getId() == null){
            Rol rol = new Rol();
            rol.setId(1L);
            rol.setName(UserRoles.USER);
            user.setRol(rol);
            userRepository.save(user);
            userDto.setId(user.getId());
            logger.info("User registrado correctamente: "+ userDto);
        }else{
            userRepository.save(user);
            logger.info("User actualizado correctamente: "+ userDto);
        }
        return userDto;
    }

    @Override
    public UserCardDto validate(AuthenticationRequest authenticationRequest, UserDto userDto) throws BadRequestException{
        userDto.getRol().setId(2L);
        userDto.getRol().setName(UserRoles.USER);
        User user = mapper.convertValue(userDto, User.class);
        userRepository.save(user);
        logger.info("User actualizado correctamente: "+ userDto);
        return authenticate(authenticationRequest);
    }

    @Override
    public UserCardDto authenticate(AuthenticationRequest authenticationRequest) throws BadRequestException {
        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matchesPassword(authenticationRequest.getPassword(), user.get().getPassword())) {
            final UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            UserCardDto userCardDto = mapper.convertValue(user, UserCardDto.class);
            userCardDto.setRolName(user.get().getRol().getName().name());
            userCardDto.setJwt(jwt);
            userCardDto.setIdPersona(user.get().getPersona().getId());
            return userCardDto;
        } else {
            throw new BadRequestException("Los datos ingresados no son correctos");
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        checkId(id);
        userRepository.deleteById(id);
        logger.info("Se elimino el User correctamente: id("+id+")");
    }

    @Override
    public User checkId(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(msjError + id);
        }
        return user.get();
    }

    @Override
    public UserPersonaDto savepersona(UserPersonaDto userPersonaDto){
        String hashedPassword = passwordEncoder.encodePassword(userPersonaDto.getPassword());
        userPersonaDto.setPassword(hashedPassword);

        PersonaDto personaDto = personaMapper.toPersonaDtoUser(userPersonaDto);

        PersonaDto personaUserDto = personaService.save(personaDto);

        User user = new User();
        user.setEmail(userPersonaDto.getEmail());
        user.setPassword(userPersonaDto.getPassword());
        user.setPersona(personaMapper.toEntity(personaUserDto));

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName(UserRoles.USER);
        user.setRol(rol);

        userRepository.save(user);

        return userPersonaDto;
    }

    public List<String> findEmails(){
        List<String> emails = userRepository.findEmails();
        return emails;
    }
}
