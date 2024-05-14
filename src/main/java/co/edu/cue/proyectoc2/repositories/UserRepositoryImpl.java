package co.edu.cue.proyectoc2.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import co.edu.cue.proyectoc2.mapping.UserMapper;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de usuarios.
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Inject
    @Named("conn")
    private Connection conn;

    /**
     * Obtiene un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario a buscar
     * @return un objeto UserDto representando el usuario encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public UserDto porUsername(String username) throws SQLException {
        User user;
        UserDto u = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                    u = UserMapper.mapFrom(user);
                }
            }
        }
        return u;
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return una lista de objetos UserDto representando los usuarios
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public List<UserDto> listar() throws SQLException {
        List<UserDto> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                User u = getUser(rs);
                UserDto userDto = UserMapper.mapFrom(u);
                users.add(userDto);
            }
        }
        return users;
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id el ID del usuario a buscar
     * @return un objeto UserDto representando el usuario encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public UserDto porId(Long id) throws SQLException {
        User user = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users as u WHERE u.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                }

            }
            return UserMapper.mapFrom(user);
        }
    }

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param userDto el objeto UserDto que representa el usuario a guardar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void save(UserDto userDto) throws SQLException {
        String sql;
        if (userDto.id() != null && userDto.id() > 0) {
            sql = "UPDATE users set username=?, password=?,email=? where id=?";
        } else {
            sql = "INSERT INTO users (username, password,email) VALUES(?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userDto.username());
            stmt.setString(2, userDto.password());
            stmt.setString(3, userDto.email());

            if (userDto.id() != null && userDto.id() > 0) {
                stmt.setLong(4, userDto.id());
            }
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param id el ID del usuario a eliminar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void delet(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
