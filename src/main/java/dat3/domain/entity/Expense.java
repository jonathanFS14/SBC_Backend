package dat3.domain.entity;
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
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Budget budget;
    private String type;
    private int amount;

    public Expense(Budget budget, String type, int amount) {
        this.budget = budget;
        this.type = type;
        this.amount = amount;
    }

    public Expense(int id, Budget budget, String type, int amount) {
        this.id = id;
        this.budget = budget;
        this.type = type;
        this.amount = amount;
    }
}
