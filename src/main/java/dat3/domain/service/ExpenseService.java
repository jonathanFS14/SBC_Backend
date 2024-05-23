package dat3.domain.service;
import dat3.domain.entity.Budget;
import dat3.domain.entity.Expense;
import dat3.domain.entity.Student;
import dat3.domain.repository.BudgetRepository;
import dat3.domain.repository.ExpenseRepository;
import dat3.domain.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class ExpenseService {

    ExpenseRepository expenseRepository;
    BudgetRepository budgetRepository;
    StudentRepository studentRepository;
    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository, StudentRepository studentRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createExpense(Expense body, String studentId) {
        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Student student = studentRepository.findById(studentId).orElseThrow();
        Budget budget = budgetRepository.findByStudentAndMonthOfYear(student, month);
        Expense expense = new Expense(budget, body.getType(), body.getAmount());
        expenseRepository.save(expense);
        return ResponseEntity.ok().body("Expense created");
    }

    public void updateExpense() {
        // TODO implement here
    }

    public ResponseEntity<String> deleteExpense(int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        expenseRepository.delete(expense);
        return ResponseEntity.ok().body("Expense deleted");
    }

    public List<Expense> getExpensesForOneBudget(String studentId) {
        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        Student student = studentRepository.findById(studentId).orElseThrow();
        Budget budget = budgetRepository.findByStudentAndMonthOfYear(student, month);
        return expenseRepository.findByBudget(budget);
    }

}
