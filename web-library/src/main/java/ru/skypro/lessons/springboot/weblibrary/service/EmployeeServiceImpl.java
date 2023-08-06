package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.*;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Integer findSalary() {
        return getAllEmployees().stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }

    @Override
    public List<Employee> findSalaryMin() {
        return getAllEmployees().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .stream().collect(Collectors.toList());
    }

    @Override
    public List<Employee> findSalaryMax() {
        return getAllEmployees().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .stream().collect(Collectors.toList());
    }

    @Override
    public List<Employee> findSalaryHigh() {
        int chetcik = getAllEmployees().size();
        int midlSalary = findSalary() / chetcik;
        List<Employee> salaryHigherAveSalary = getAllEmployees().stream()
                .filter(i -> i.getSalary() >= midlSalary)
                .toList();
        return salaryHigherAveSalary;
    }
}
