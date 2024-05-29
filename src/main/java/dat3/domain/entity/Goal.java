package dat3.domain.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JsonIgnore
    private Student student;
    private String type;
    private int amountPercentage;

    public Goal(Student student, String type, int amountPercentage) {
        this.student = student;
        this.type = type;
        this.amountPercentage = amountPercentage;
    }

    public Goal(int id, Student student, String type, int amountPercentage) {
        this.id = id;
        this.student = student;
        this.type = type;
        this.amountPercentage = amountPercentage;
    }
}
