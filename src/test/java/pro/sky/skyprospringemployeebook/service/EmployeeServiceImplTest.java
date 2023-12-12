package pro.sky.skyprospringemployeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringemployeebook.exceptions.InvalidInputException;
import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeServiceImpl out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
        Employee emp1 = new Employee("John", "Adams", 4, 49.36);
        Employee emp2 = new Employee("Mark", "Evans", 3, 93.24);
        Employee emp3 = new Employee("Helene", "Stewart", 2, 57.06);
        Employee emp4 = new Employee("Ana", "De Armas", 4, 31.22);
        Employee emp5 = new Employee("Brad", "Pitt", 2, 75.39);
        Employee emp6 = new Employee("Maria", "Williams", 3, 63.03);
        Employee emp7 = new Employee("Anthony", "Webber", 4, 46.12);
        out.employees.put(emp1.getFullName(), emp1);
        out.employees.put(emp2.getFullName(), emp2);
        out.employees.put(emp3.getFullName(), emp3);
        out.employees.put(emp4.getFullName(), emp4);
        out.employees.put(emp5.getFullName(), emp5);
        out.employees.put(emp6.getFullName(), emp6);
        out.employees.put(emp7.getFullName(), emp7);
    }

    public static Stream<Arguments> argumentsForSuccessfullyAddTest() {
        return Stream.of(Arguments.of("Johny", "Depp", 2, 86.15));
    }

    public static Stream<Arguments> argumentsForInvalidInputTest() {
        return Stream.of(Arguments.of("1", "56", 2, 86.15));
    }

    public static Stream<Arguments> argumentsForEmployeeAlreadyAddedTest() {
        return Stream.of(Arguments.of("Brad", "Pitt", 2, 75.39));
    }

    public static Stream<Arguments> argumentsForSuccessfullyRemoveTest() {
        return Stream.of(Arguments.of("Mark", "Evans", 3, 93.24));
    }

    public static Stream<Arguments> argumentsForEmployeeNotExistTest() {
        return Stream.of(Arguments.of("Maria", "Gomez", 1, 54.69));
    }

    public static Stream<Arguments> argumentsForSuccessfullyFindTest() {
        return Stream.of(Arguments.of("Anthony", "Webber", 4, 46.12));
    }


    @ParameterizedTest
    @MethodSource("argumentsForSuccessfullyAddTest")
    void methodShouldAddEmployeeTest(String name, String lastName, int department, double salary) {
        Employee newEmployee = out.add(name, lastName, department, salary);
        assertTrue(out.getAllEmployees().containsKey(newEmployee.getFullName()));
    }

    @ParameterizedTest
    @MethodSource("argumentsForInvalidInputTest")
    void methodShouldThrowInvalidInputException(String name, String lastName, int department, double salary) {
        assertThrows(InvalidInputException.class, () -> out.add(name, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("argumentsForEmployeeAlreadyAddedTest")
    void methodShouldThrowEmployeeAlreadyAddedException(String name, String lastName, int department, double salary) {
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(name, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("argumentsForSuccessfullyRemoveTest")
    void methodShouldRemoveEmployeeTest(String name, String lastName, int department, double salary) {
        Employee employee = out.remove(name, lastName, department, salary);
        assertFalse(out.getAllEmployees().containsKey(employee.getFullName()));
    }

    @ParameterizedTest
    @MethodSource("argumentsForEmployeeNotExistTest")
    void methodShouldThrowEmployeeNotFoundExceptionInRemoveMethod(String name, String lastName, int department, double salary) {
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(name, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("argumentsForSuccessfullyFindTest")
    void methodShouldFindEmployeeTest(String name, String lastName, int department, double salary) {
        Employee employee = out.find(name, lastName, department, salary);
        assertTrue(out.getAllEmployees().containsKey(employee.getFullName()));
    }

    @ParameterizedTest
    @MethodSource("argumentsForEmployeeNotExistTest")
    void methodShouldThrowEmployeeNotFoundExceptionInFindMethod(String name, String lastName, int department, double salary) {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(name, lastName, department, salary));
    }

    @Test
    void testGetAllEmployees() {
        Map<String, Employee> actual = out.getAllEmployees();
        Map<String, Employee> expected = new HashMap<>();
        expected.put("John Adams", new Employee("John", "Adams", 4, 49.36));
        expected.put("Mark Evans", new Employee("Mark", "Evans", 3, 93.24));
        expected.put("Helene Stewart", new Employee("Helene", "Stewart", 2, 57.06));
        expected.put("Ana de Armas", new Employee("Ana", "De Armas", 4, 31.22));
        expected.put("Brad Pitt", new Employee("Brad", "Pitt", 2, 75.39));
        expected.put("Maria Williams", new Employee("Maria", "Williams", 3, 63.03));
        expected.put("Anthony Webber", new Employee("Anthony", "Webber", 4, 46.12));

        assertEquals(actual.size(), expected.size());
        assertEquals(actual.get("John Adams"),expected.get("John Adams"));
        assertEquals(actual.get("Mark Evans"),expected.get("Mark Evans"));
        assertEquals(actual.get("Helene Stewart"),expected.get("Helene Stewart"));
        assertEquals(actual.get("Ana De Armas"),expected.get("Ana De Armas"));
        assertEquals(actual.get("Brad Pitt"),expected.get("Brad Pitt"));
        assertEquals(actual.get("Maria Williams"),expected.get("Maria Williams"));
        assertEquals(actual.get("Anthony Webber"),expected.get("Anthony Webber"));

    }
}