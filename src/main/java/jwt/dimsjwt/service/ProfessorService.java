package jwt.dimsjwt.service;

import jwt.dimsjwt.DTO.ProfessorRegisterRequest;
import jwt.dimsjwt.model.Professor;
import jwt.dimsjwt.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int saveProfessorRegisterRequest(ProfessorRegisterRequest professorRegisterRequest) {

        System.out.println("PASSWORD DEBUG-------------------");
        String rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Raw password: " + rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
        try {
            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                System.out.println("Password match");
            } else {
                System.out.println("Password mismatch");
                System.out.println("Raw password: " + rawPassword);
                System.out.println("Encoded password to compare: " + encodedPassword);
            }
        } catch (Exception e) {
            System.out.println("Exception during password match check: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PASSWORD DEBUG-------------------");


        Professor professor = new Professor();
        professor.setName(professorRegisterRequest.getName());
        professor.setRole(professorRegisterRequest.getRole());
        professor.setEmail(professorRegisterRequest.getEmail());
        professor.setPassword(passwordEncoder.encode(professorRegisterRequest.getPassword()));
        try {
            professorRepository.save(professor);
            return 1;
        } catch (Exception e) {
            System.out.println("Exception during professor registration: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
