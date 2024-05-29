package dat3.domain.api;
import dat3.domain.dto.ExpenseDTO;
import dat3.domain.entity.Expense;
import dat3.domain.repository.StudentRepository;
import dat3.domain.service.ExpenseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/expense")
public class ExpenseController {

    ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addExpense(@RequestBody Expense body, Principal principal) {
        return expenseService.createExpense(body, principal.getName());
    }

    @GetMapping()
    List<ExpenseDTO> getExpense(Principal principal) {
        return expenseService.getExpensesForOneBudget(principal.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        return expenseService.deleteExpense(id);
    }

}
