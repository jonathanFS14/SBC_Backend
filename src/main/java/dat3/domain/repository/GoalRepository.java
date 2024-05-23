package dat3.domain.repository;

import dat3.domain.entity.Goal;
import dat3.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

    List<Goal> findByStudent(Student student);

}
