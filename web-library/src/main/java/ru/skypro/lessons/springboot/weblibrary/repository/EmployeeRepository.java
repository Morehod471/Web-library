package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    public void editEmployee(int id, Employee employee);

    public Optional<Employee> findById(int id);

    public void deleteEmployee(int id);

}
