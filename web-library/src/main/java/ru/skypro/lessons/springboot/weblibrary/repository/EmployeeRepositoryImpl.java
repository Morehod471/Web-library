package ru.skypro.lessons.springboot.weblibrary.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.*;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    public void init() {
        employeeList.add(new Employee("Катя", 90_000));
        employeeList.add(new Employee("Дима", 102_000));
        employeeList.add(new Employee("Олег", 80_000));
        employeeList.add(new Employee("Вика", 165_000));
    }

    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        int newIndex = employeeList.size() + 1;
        employeeList.add(employee);
        employee.setId(newIndex);
        return employee;
    }

    public void editEmployee(int id, Employee employee) {
        int indexForUpdating = findIndexById(id);
        if (indexForUpdating != -1) {
            employeeList.set(indexForUpdating, employee);
        }
    }

    public Optional<Employee> findById(int id) {
        return employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public void deleteEmployee(int id) {
        int indexForRemoving = findIndexById(id);
        if (indexForRemoving != -1) {
            employeeList.remove(indexForRemoving);
        }
    }

    private int findIndexById(int id) {
        int index = -1;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
}