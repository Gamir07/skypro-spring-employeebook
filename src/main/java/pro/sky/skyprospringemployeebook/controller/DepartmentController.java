package pro.sky.skyprospringemployeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyprospringemployeebook.model.Employee;
import pro.sky.skyprospringemployeebook.service.DepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> printEmployeesInDepartments() {
        return departmentService.printEmployeesInDepartments();
    }

    @GetMapping(path = "/all/byNumber")
    public Map<String, Employee> printAllEmployeesByDepartment(@RequestParam(name = "departmentId") int department) {
        return departmentService.printAllEmployeesByDepartment(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee employeeWithMinSalary(@RequestParam(name = "departmentId") int department) {
        return departmentService.employeeWithMinSalary(department);
    }
    @GetMapping(path = "/max-salary")
    public Employee employeeWithMaxSalary(@RequestParam(name = "departmentId") int department) {
        return departmentService.employeeWithMaxSalary(department);
    }
}
