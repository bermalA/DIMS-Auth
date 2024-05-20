package jwt.dimsjwt.DTO;

import jwt.dimsjwt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisterRequest {
    private String studentId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role = Role.STUDENT;
}
