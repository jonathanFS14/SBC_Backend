package dat3.domain.api;
import dat3.domain.entity.Budget;
import dat3.domain.entity.Expense;
import dat3.domain.service.BudgetService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/budget")
public class BudgetController {

    BudgetService budgetService;
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addBudget(@RequestBody Expense body, Principal principal) {
        return budgetService.createBudget(principal.getName());
    }

    @GetMapping()
    Budget getBudget(Principal principal) {
        return budgetService.getBudget(principal.getName());
    }

}
