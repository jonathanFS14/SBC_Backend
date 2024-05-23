package dat3.domain.service;
import dat3.domain.entity.Student;
import dat3.domain.repository.StudentRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createStudent(Student body) {
        if (studentRepository.existsById(body.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        Student student = new Student(body.getUsername(), body.getPassword(), body.getFirstName(), body.getLastName(), body.getEmail(), body.getPhoneNumber());
        student.addRole(Role.USER);
        studentRepository.save(student);
        return ResponseEntity.ok().body("Student created");
    }

    public ResponseEntity<String> updateStudent(Student body, String id) {
        Student student = getStudent(id);
        student.setUsername(body.getUsername());
        student.setPassword(body.getPassword());
        student.setFirstName(body.getFirstName());
        student.setLastName(body.getLastName());
        student.setEmail(body.getEmail());
        student.setPhoneNumber(body.getPhoneNumber());
        studentRepository.save(student);
        return ResponseEntity.ok().body("Student updated");
    }

    public ResponseEntity<String> deleteStudent(String id) {
        Student user = getStudent(id);
        studentRepository.delete(user);
        return ResponseEntity.ok().body("Student deleted");
    }

    public Student getStudent(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

}
