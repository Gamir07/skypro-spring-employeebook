package pro.sky.skyprospringemployeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringemployeebook.exceptions.DepartmentNotFoundException;
import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Employee employeeWithMinSalary(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();

    }

    public Employee employeeWithMaxSalary(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();

    }

    public Map<String, Employee> printAllEmployeesByDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.employees.values().stream().
                filter(emp -> department == emp.getDepartment()).
                collect(Collectors.toMap(Employee::toString, e2->e2));
    }

    public Map<Integer, List<Employee>> printEmployeesInDepartments() {
        return employeeService.employees.values().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
