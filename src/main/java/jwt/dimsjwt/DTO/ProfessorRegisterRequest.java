package jwt.dimsjwt.DTO;

import jwt.dimsjwt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role = Role.PROFESSOR;
}