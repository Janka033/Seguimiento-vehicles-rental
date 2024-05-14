package co.edu.cue.proyectoc2.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
}
