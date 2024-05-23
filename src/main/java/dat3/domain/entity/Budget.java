package dat3.domain.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Student student;
    private String monthOfYear;
    private int yearAsInt;
    private int saved;
    private int invested;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Income> incomes;
    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Expense> expenses;

    public Budget(Student student, String month, int year, int saved, int invested) {
        this.student = student;
        this.monthOfYear = month;
        this.yearAsInt = year;
        this.saved = saved;
        this.invested = invested;
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
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
