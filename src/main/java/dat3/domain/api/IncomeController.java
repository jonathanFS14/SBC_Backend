package dat3.domain.api;
import dat3.domain.entity.Income;
import dat3.domain.service.IncomeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/income")
public class IncomeController {

    private final IncomeService incomeService;
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addIncome(@RequestBody Income body, Principal principal) {
      return incomeService.createIncome(body, principal.getName());
    }

    @GetMapping()
    List<Income> getIncome(Principal principal) {
        return incomeService.getIncomesForOneBudget(principal.getName());
    }

    @DeleteMapping()
    ResponseEntity<String> deleteIncome(int id) {
        return incomeService.deleteIncome(id);
    }

}
