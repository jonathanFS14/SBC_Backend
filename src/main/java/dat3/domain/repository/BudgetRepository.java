package dat3.domain.repository;

import dat3.domain.entity.Budget;
import dat3.domain.entity.Expense;
import dat3.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Budget findBudgetByStudent(Student student);
    Budget findByStudentAndMonthOfYear(Student student, String month);
}
