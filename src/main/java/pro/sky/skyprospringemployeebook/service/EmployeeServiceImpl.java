package pro.sky.skyprospringemployeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    public Employee put(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
        } else {
            employees.put(employee.getFullName(), employee);
            return employee;
        }

    }

    public Employee find(String name, String lastName) {
        Employee employee = new Employee(name, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee remove(String name, String lastName) {
        Employee employee = new Employee(name, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());

        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Collection<Employee> printAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
