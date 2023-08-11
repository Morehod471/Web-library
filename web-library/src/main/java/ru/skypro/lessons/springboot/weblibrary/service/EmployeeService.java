package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    public Integer findSalary();

    public Employee findSalaryMin();

    public Employee findSalaryMax();

    public List<Employee> findSalaryHigh();

    public List<Employee> addEmployee(List<Employee> employeeList);

    public void editEmployee(int id, Employee employee);

    public Employee findEmployeeById(int id);

    public void deleteEmployee(int id);

    public List<Employee> findSalaryHigherThan(int salary);
}
