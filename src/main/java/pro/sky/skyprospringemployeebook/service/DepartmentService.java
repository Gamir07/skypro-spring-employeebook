package pro.sky.skyprospringemployeebook.service;

import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee employeeWithMinSalary(int department);

    Employee employeeWithMaxSalary(int department);

    Map<String, Employee> printAllEmployeesByDepartment(int department);

    Map<Integer, List<Employee>> printEmployeesInDepartments();
}
