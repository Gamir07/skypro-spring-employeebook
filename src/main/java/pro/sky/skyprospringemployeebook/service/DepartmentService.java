package pro.sky.skyprospringemployeebook.service;

import pro.sky.skyprospringemployeebook.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Double minSalaryInDepartment(int department);

    Double maxSalaryInDepartment(int department);

    Map<String, Employee> printAllEmployeesByDepartment(int department);

    Double totalSalaryForDepartment(int department);

    Map<Integer, List<Employee>> printEmployeesInDepartments();
}
