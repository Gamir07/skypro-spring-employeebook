package pro.sky.skyprospringemployeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringemployeebook.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyprospringemployeebook.model.Employee;
import pro.sky.skyprospringemployeebook.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    private static int maxCapacity = 5;

    public Employee add(Employee employee) {
        if (employeeList.size() < maxCapacity) {
            if (employeeList.contains(employee)) {
                throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в коллекции");
            }
            employeeList.add(employee);
            maxCapacity--;
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
    }

    public Employee find(Employee employee) {
        if (employeeList.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee remove(Employee employee) {
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Collection<Employee> printAllEmployees() {
        return Collections.unmodifiableList(employeeList);
    }
}
