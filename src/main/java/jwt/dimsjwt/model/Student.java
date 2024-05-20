package jwt.dimsjwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "_students")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private String name;
    private String surname;
    @Column(columnDefinition = "varchar(20)")
    private String studentId;
    @ManyToMany(mappedBy = "students")
    private List<Lecture> enrolledLectures;
}
