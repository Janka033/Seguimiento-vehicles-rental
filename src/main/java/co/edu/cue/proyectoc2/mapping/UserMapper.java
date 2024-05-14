package co.edu.cue.proyectoc2.mapping;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.models.User;

public class UserMapper {
    public static UserDto mapFrom(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }
    public static User mapFromDto(UserDto userDto){
        return User.builder()
                .id(userDto.id())
                .username(userDto.username())
                .password(userDto.password())
                .email(userDto.email())
                .build();
    }
}
