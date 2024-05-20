package jwt.dimsjwt.repository;

import jwt.dimsjwt.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
