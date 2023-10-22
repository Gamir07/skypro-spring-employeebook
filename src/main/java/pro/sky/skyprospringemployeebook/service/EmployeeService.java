package pro.sky.skyprospringemployeebook.service;

import pro.sky.skyprospringemployeebook.model.Employee;

public interface EmployeeService {
    Employee add(Employee emp);

    Employee find(Employee emp);

    Employee remove(Employee emp);

}
