package jwt.dimsjwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "_lectures")
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    private String date;
    private int duration;
    @ManyToMany
    @JoinTable(
            name = "enrolled_lectures",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

}
