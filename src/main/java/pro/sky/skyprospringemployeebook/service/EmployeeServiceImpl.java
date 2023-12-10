package pro.sky.skyprospringemployeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringemployeebook.exceptions.InvalidInputException;
import pro.sky.skyprospringemployeebook.model.Employee;


import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    public Employee add(String name, String lastName, int department, double salary) {
        validateInput(name, lastName);
        Employee employee = new Employee(name, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
        } else {
            employees.put(employee.getFullName(), employee);
            return employee;
        }

    }

    public Employee find(String name, String lastName, int department, double salary) {
        validateInput(name, lastName);
        Employee employee = new Employee(name, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee remove(String name, String lastName, int department, double salary) {
        validateInput(name, lastName);
        Employee employee = new Employee(name, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());

        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Map<String, Employee> getAllEmployees() {
        return employees;
    }

    private void validateInput(String name, String lastName) {
        if (!(isAlpha(name) & isAlpha(lastName))){
            throw new InvalidInputException();
        }
    }
}
