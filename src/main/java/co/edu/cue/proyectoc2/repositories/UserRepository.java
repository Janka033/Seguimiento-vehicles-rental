package co.edu.cue.proyectoc2.repositories;

import co.edu.cue.proyectoc2.mapping.dto.UserDto;

import java.sql.SQLException;

public interface UserRepository extends Repository<UserDto>{
    UserDto porUsername(String username) throws SQLException;
}
