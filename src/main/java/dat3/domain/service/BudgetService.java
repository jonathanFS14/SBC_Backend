package dat3.domain.service;
import dat3.domain.entity.Budget;
import dat3.domain.entity.Student;
import dat3.domain.repository.BudgetRepository;
import dat3.domain.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class BudgetService {

    BudgetRepository budgetRepository;
    StudentRepository studentRepository;
    public BudgetService(BudgetRepository budgetRepository, StudentRepository studentRepository) {
        this.budgetRepository = budgetRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createBudget(String studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        LocalDate currentDate = LocalDate.now();
        String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = currentDate.getYear();
        Budget budget = new Budget(student, month, year, 0, 0);
        budgetRepository.save(budget);
        return ResponseEntity.ok().body("Budget created");
    }

    public Budget getBudget(String studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return budgetRepository.findBudgetByStudent(student);
    }

    public void updateBudget() {
        // TODO implement here
    }

    public void deleteBudget() {
        // TODO implement here
    }

}
