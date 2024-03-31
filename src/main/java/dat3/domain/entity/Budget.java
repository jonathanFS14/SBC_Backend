package dat3.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Student student;
    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Goal> goals;
    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Income> incomes;
    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Expense> expenses;
    private String month;
    private int saving;
    private int investment;


    public Budget(Student student ,String month, int saving, int investment) {
        this.student = student;
        this.month = month;
        this.saving = saving;
        this.investment = investment;
        this.goals = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addGoal(Goal goal){
        if (this.goals == null)
            goals = new ArrayList<>();
        goals.add(goal);
    }

    public void addIncome(Income income){
        if (this.incomes == null)
            incomes = new ArrayList<>();
        incomes.add(income);
    }

    public void addExpense(Expense expense){
        if (this.expenses == null)
            expenses = new ArrayList<>();
        expenses.add(expense);
    }

}
