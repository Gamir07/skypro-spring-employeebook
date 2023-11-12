package pro.sky.skyprospringemployeebook.service;

import pro.sky.skyprospringemployeebook.model.Employee;

public interface EmployeeService {
    Employee add(String name, String lastName, int department, double salary);

    Employee find(String name, String lastName, int department, double salary);

    Employee remove(String name, String lastName, int department, double salary);

}
