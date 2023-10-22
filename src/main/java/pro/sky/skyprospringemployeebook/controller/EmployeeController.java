package pro.sky.skyprospringemployeebook.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyprospringemployeebook.model.Employee;
import pro.sky.skyprospringemployeebook.service.EmployeeServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeServiceImpl.add(employee);
        return employee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeServiceImpl.remove(employee);
        return employee;
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeServiceImpl.find(employee);
        return employee;
    }

    @GetMapping(path = "/allEmployees")
    public Collection<Employee> printAllEmployees() {
        return employeeServiceImpl.printAllEmployees();
    }
}
