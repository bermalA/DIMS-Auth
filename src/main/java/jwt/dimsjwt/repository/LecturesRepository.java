package jwt.dimsjwt.repository;

import jwt.dimsjwt.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturesRepository extends JpaRepository<Lecture, Long> {

}
