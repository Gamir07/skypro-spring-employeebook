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

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Double minSalaryInDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow().getSalary();

    }

    public Double maxSalaryInDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.getAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow().getSalary();

    }

    public Map<String, Employee> printAllEmployeesByDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.getAllEmployees().values().stream().
                filter(emp -> department == emp.getDepartment()).
                collect(Collectors.toMap(Employee::getFullName, e2 -> e2));
    }

    public Double totalSalaryForDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new DepartmentNotFoundException("Такого департамента не существует");
        }
        return employeeService.getAllEmployees().values().stream().
                filter(emp -> department == emp.getDepartment()).
                map(Employee::getSalary).
                reduce(Double::sum).orElseThrow();
    }

    public Map<Integer, List<Employee>> printEmployeesInDepartments() {
        return employeeService.getAllEmployees().values().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
