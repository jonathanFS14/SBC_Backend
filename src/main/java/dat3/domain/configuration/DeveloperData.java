package dat3.domain.configuration;

import dat3.domain.entity.*;
import dat3.domain.repository.*;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class DeveloperData implements ApplicationRunner {

    PasswordEncoder passwordEncoder;
    BudgetRepository budgetRepository;
    ExpenseRepository expenseRepository;
    GoalRepository goalRepository;
    IncomeRepository incomeRepository;
    StudentRepository studentRepository;
    UserWithRolesRepository userWithRolesRepository;

    public DeveloperData(PasswordEncoder passwordEncoder, BudgetRepository budgetRepository, ExpenseRepository expenseRepository,
                         GoalRepository goalRepository, IncomeRepository incomeRepository,
                         StudentRepository studentRepository, UserWithRolesRepository userWithRolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.goalRepository = goalRepository;
        this.incomeRepository = incomeRepository;
        this.studentRepository = studentRepository;
        this.userWithRolesRepository = userWithRolesRepository;
    }

    Student student1 = new Student("student1", "123", "jdoe123@example.com", "John", "Doe", "555-123-4567");
    Student student2 = new Student("student2", "123", "asmith456@example.com", "Alice", "Smith", "555-234-5678");
    Budget budget1 = new Budget(student1, "may", 2024, 0, 0);
    Budget budget2 = new Budget(student2, "may", 2024, 0, 0);

    @Override
    public void run(ApplicationArguments args) {
        setupStudents();
        setupAdmin();
        setupBudgets();
        setupIncomes();
        setupExpenses();
        setupGoals();

    }

    public void setupAdmin() {
        UserWithRoles admin = new UserWithRoles("admin", passwordEncoder.encode("admin"), "adminMail");
        admin.addRole(Role.ADMIN);
        userWithRolesRepository.save(admin);
    }

    public void setupStudents() {
        student1.addRole(Role.USER);
        studentRepository.save(student1);
        student2.addRole(Role.USER);
        studentRepository.save(student2);
    }

    public void setupBudgets() {
        budgetRepository.save(budget1);
        budgetRepository.save(budget2);
    }

    public void setupIncomes() {
        Income income1 = new Income(budget1, "salary", 1000);
        Income income2 = new Income(budget1, "scholarship", 200);
        Income income3 = new Income(budget1, "part-time job", 200);
        Income income4 = new Income(budget2, "salary", 1000);
        Income income5 = new Income(budget2, "scholarship", 200);
        Income income6 = new Income(budget2, "part-time job", 200);
        incomeRepository.saveAll(List.of(income1, income2, income3, income4, income5, income6));
    }

    public void setupExpenses() {
        Expense expense1 = new Expense(budget1, "rent", 1000);
        Expense expense2 = new Expense(budget1, "food", 200);
        Expense expense3 = new Expense(budget1, "hobby", 200);
        Expense expense4 = new Expense(budget2, "rent", 1000);
        Expense expense5 = new Expense(budget2, "food", 200);
        Expense expense6 = new Expense(budget2, "hobby", 200);
        expenseRepository.saveAll(List.of(expense1, expense2, expense3, expense4, expense5, expense6));
    }

    public void setupGoals() {
        Goal goal1 = new Goal(student1, "invest", 10);
        Goal goal2 = new Goal(student1, "save", 20);
        Goal goal3 = new Goal(student2, "invest", 30);
        Goal goal4 = new Goal(student2, "save", 40);
        goalRepository.saveAll(List.of(goal1, goal2, goal3, goal4));
    }
}
