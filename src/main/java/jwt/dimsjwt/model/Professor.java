package jwt.dimsjwt.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "_professors")
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User{
    private String name;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Lecture> lectures;
}
