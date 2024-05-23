package dat3.domain.service;
import dat3.domain.entity.Budget;
import dat3.domain.entity.Income;
import dat3.domain.entity.Student;
import dat3.domain.repository.BudgetRepository;
import dat3.domain.repository.IncomeRepository;
import dat3.domain.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class IncomeService {

    IncomeRepository incomeRepository;
    BudgetRepository budgetRepository;
    StudentRepository studentRepository;
    public IncomeService(IncomeRepository incomeRepository, BudgetRepository budgetRepository, StudentRepository studentRepository) {
        this.incomeRepository = incomeRepository;
        this.budgetRepository = budgetRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createIncome(Income body, String studentId) {
        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Student student = studentRepository.findById(studentId).orElseThrow();
        Budget budget = budgetRepository.findByStudentAndMonthOfYear(student, month);
        Income income = new Income(budget, body.getType(), body.getAmount());
        incomeRepository.save(income);
        return ResponseEntity.ok().body("Expense created");
    }

    public void updateIncome() {
        // TODO implement here
    }

    public ResponseEntity<String> deleteIncome(int id) {
        Income income = incomeRepository.findById(id).orElseThrow();
        incomeRepository.delete(income);
        return ResponseEntity.ok().body("Expense deleted");
    }

    public List<Income> getIncomesForOneBudget(String studentId) {
        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Student student = studentRepository.findById(studentId).orElseThrow();
        Budget budget = budgetRepository.findByStudentAndMonthOfYear(student, month);
        return incomeRepository.findByBudget(budget);
    }

}
