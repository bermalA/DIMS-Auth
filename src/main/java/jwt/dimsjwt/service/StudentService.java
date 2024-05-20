package jwt.dimsjwt.service;

import jwt.dimsjwt.DTO.StudentRegisterRequest;
import jwt.dimsjwt.model.Student;
import jwt.dimsjwt.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public int saveStudentRegisterRequest(StudentRegisterRequest studentRegisterRequest) {
        Student student = new Student();
        student.setName(studentRegisterRequest.getName());
        student.setEmail(studentRegisterRequest.getEmail());
        student.setRole(studentRegisterRequest.getRole());
        student.setPassword(String.valueOf(studentRegisterRequest.getPassword().hashCode()));
        student.setSurname(studentRegisterRequest.getSurname());
        student.setStudentId(studentRegisterRequest.getStudentId());
        try {
            System.out.println(student.getPassword().hashCode());
            studentRepository.save(student);
            return 1;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
