package dat3.domain.api;
import dat3.domain.entity.Student;
import dat3.domain.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Student addStudent(@RequestBody Student body){
        return studentService.createStudent(body);
    }

    @GetMapping
    Student getStudent(Principal principal) {
        return studentService.getStudent(principal.getName());
    }

    @PutMapping()
    ResponseEntity<String> editStudent(@RequestBody Student body, Principal principal) {
        return studentService.updateStudent(body, principal.getName());
    }

    @DeleteMapping()
    ResponseEntity<String> deleteStudent(Principal principal) {
        return studentService.deleteStudent(principal.getName());
    }

}
