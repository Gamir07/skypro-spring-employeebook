package pro.sky.skyprospringemployeebook.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyprospringemployeebook.model.Employee;
import pro.sky.skyprospringemployeebook.service.DepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> printEmployeesInDepartments() {
        return departmentService.printEmployeesInDepartments();
    }

    @GetMapping(path = "/{id}/employees")
    public Map<String, Employee> printAllEmployeesByDepartment(@PathVariable int id) {
        return departmentService.printAllEmployeesByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Double employeeWithMinSalary(@PathVariable int id) {
        return departmentService.minSalaryInDepartment(id);
    }
    @GetMapping(path = "/{id}/salary/max")
    public Double employeeWithMaxSalary(@PathVariable int id) {
        return departmentService.maxSalaryInDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public Double totalSalaryForDepartment(@PathVariable int id){
        return departmentService.totalSalaryForDepartment(id);
    }
}
