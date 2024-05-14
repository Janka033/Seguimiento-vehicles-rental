package co.edu.cue.proyectoc2.services;

import co.edu.cue.proyectoc2.mapping.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(String username, String password);
}
