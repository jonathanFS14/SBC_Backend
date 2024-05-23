package dat3.domain.entity;
import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student extends UserWithRoles{

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Budget> budgets;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Goal> goals;

    public Student(String username, String password, String email, String firstName,
                  String lastName, String phoneNumber) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.budgets = new ArrayList<>();
        this.goals = new ArrayList<>();
    }

    public void addReservation(Budget budget){
        if (budgets == null)
            budgets = new ArrayList<>();
        budgets.add(budget);
    }

    public void addGoal(Goal goal){
        if (goals == null)
            goals = new ArrayList<>();
        goals.add(goal);
    }
}
