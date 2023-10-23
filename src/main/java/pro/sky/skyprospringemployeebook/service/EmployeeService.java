package pro.sky.skyprospringemployeebook.service;

import pro.sky.skyprospringemployeebook.model.Employee;

public interface EmployeeService {
    Employee put(String name, String lastName);

    Employee find(String name, String lastName);

    Employee remove(String name, String lastName);

}
