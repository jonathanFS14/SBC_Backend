package dat3.domain.api;

import dat3.domain.dto.GoalDTO;
import dat3.domain.entity.Goal;
import dat3.domain.service.GoalService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/goal")
public class GoalController {

    GoalService goalsService;
    public GoalController(GoalService goalsService) {
        this.goalsService = goalsService;
    }

    @GetMapping()
    List<GoalDTO> getGoals(Principal principal) {
        return goalsService.getGoalsForStudent(principal.getName());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addGoal(@RequestBody Goal body, Principal principal) {
        return goalsService.createGoal(principal.getName(), body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoal(@PathVariable int id) {
        return goalsService.deleteGoal(id);
    }

}
