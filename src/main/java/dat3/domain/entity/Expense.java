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
    private int price;

    public Expense(Budget budget, String type, int price) {
        this.budget = budget;
        this.type = type;
        this.price = price;
    }


}
