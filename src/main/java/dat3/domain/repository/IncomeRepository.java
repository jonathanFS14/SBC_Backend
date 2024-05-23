package dat3.domain.repository;

import dat3.domain.entity.Budget;
import dat3.domain.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findByBudget(Budget budget);

}
