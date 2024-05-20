package jwt.dimsjwt.repository;

import jwt.dimsjwt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
