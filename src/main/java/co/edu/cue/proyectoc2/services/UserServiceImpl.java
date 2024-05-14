package co.edu.cue.proyectoc2.services;

import co.edu.cue.proyectoc2.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;

import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;
    @Override
    public Optional<UserDto> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.porUsername(username)).filter(u -> u.password().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
