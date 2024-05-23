package dat3.domain.service;
import dat3.domain.entity.Goal;
import dat3.domain.entity.Student;
import dat3.domain.repository.GoalRepository;
import dat3.domain.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoalService {

    GoalRepository goalRepository;
    StudentRepository studentRepository;
    public GoalService(GoalRepository goalRepository, StudentRepository studentRepository) {
        this.goalRepository = goalRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createGoal(String studentId, Goal body) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Goal goal = new Goal(student, body.getType(), body.getAmountPercentage());
        goalRepository.save(goal);
        return ResponseEntity.ok().body("Goal created");
    }

    public void updateGoal() {
        // TODO implement here
    }

    public ResponseEntity<String> deleteGoal(int id) {
        Goal goal = goalRepository.findById(id).orElseThrow();
        goalRepository.delete(goal);
        return ResponseEntity.ok().body("Goal deleted");
    }

    public List<Goal> getAllGoalForOneStudent(String studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return goalRepository.findByStudent(student);
    }

}
