package pro.sky.skyprospringemployeebook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprospringemployeebook.exceptions.DepartmentNotFoundException;
import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private final Map<String, Employee> EMPLOYEES = Map.of(
            "John Adams", new Employee("John", "Adams", 4, 49.36),
            "Mark Evans", new Employee("Mark", "Evans", 3, 93.24),
            "Helene Stewart", new Employee("Helene", "Stewart", 2, 57.06),
            "Brad Pitt", new Employee("Brad", "Pitt", 2, 75.39),
            "Anthony Webber", new Employee("Anthony", "Webber", 4, 46.12));

    @Mock
    EmployeeServiceImpl employeeServiceMock;
    @InjectMocks
    DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(employeeServiceMock.getAllEmployees()).thenReturn(EMPLOYEES);
    }

    @Test
    void minSalaryInDepartment() {
        Double actual = out.minSalaryInDepartment(4);
        Double expected = 46.12;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void maxSalaryInDepartment() {
        Double actual = out.maxSalaryInDepartment(2);
        Double expected = 75.39;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printAllEmployeesByDepartment() {
        Map<String, Employee> actual = out.printAllEmployeesByDepartment(4);
        Map<String, Employee> expected = Map.of("John Adams", new Employee("John", "Adams", 4, 49.36),
                "Anthony Webber", new Employee("Anthony", "Webber", 4, 46.12));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void methodShouldThrowDepartmentNotFoundException() {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> out.minSalaryInDepartment(6));
    }

    @Test
    void totalSalaryForDepartment() {
        Double actual = out.totalSalaryForDepartment(2);
        Double expected = 132.45;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void printEmployeesInDepartments() {
        Map<Integer, List<Employee>> actual = out.printEmployeesInDepartments();
        Map<Integer, List<Employee>> expected = Map.of(
                2, List.of(new Employee("Brad", "Pitt", 2, 75.39),
                        new Employee("Helene", "Stewart", 2, 57.06)),
                3, List.of(new Employee("Mark", "Evans", 3, 93.24)),
                4, List.of(new Employee("John", "Adams", 4, 49.36),
                        new Employee("Anthony", "Webber", 4, 46.12)));
        Assertions.assertEquals(actual.size(), expected.size());
        Assertions.assertTrue(actual.get(2).containsAll(expected.get(2)));
        Assertions.assertTrue(actual.get(3).containsAll(expected.get(3)));
        Assertions.assertTrue(actual.get(4).containsAll(expected.get(4)));

    }
}