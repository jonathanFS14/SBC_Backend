package dat3.domain.repository;

import dat3.domain.entity.Budget;
import dat3.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findByBudget(Budget budget);

}
