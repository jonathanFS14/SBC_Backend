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
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Budget budget;
    private String type;
    private int saving;
    private int investment;

    public Goal(Budget budget, String type, int saving, int investment) {
        this.budget = budget;
        this.type = type;
        this.saving = saving;
        this.investment = investment;
    }

}
