package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.*;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotFoundException;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotValidException;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Integer findSalary() {
        return employeeRepository.getAllEmployees().stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }

    public Employee findSalaryMin() {
        return employeeRepository.getAllEmployees().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee findSalaryMax() {
        return employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> findSalaryHigh() {
        int chetcik = employeeRepository.getAllEmployees().size();
        int midlSalary = findSalary() / chetcik;
        List<Employee> salaryHigherAveSalary = employeeRepository.getAllEmployees().stream()
                .filter(i -> i.getSalary() >= midlSalary)
                .toList();
        return salaryHigherAveSalary;
    }

    public List<Employee> addEmployee(List<Employee> employeeList) {
        Optional<Employee> incorrectEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() <= 0 || employee.getName() == null || employee.getName().isEmpty())
                .findFirst();
        if (incorrectEmployee.isPresent()){
            throw new EmployeeNotValidException(incorrectEmployee.get());
        }
        return employeeList.stream()
                .map(employee -> new Employee(employee.getName(), employee.getSalary()))
                .map(employeeRepository::addEmployee)
                .collect(Collectors.toList());
    }

    public void editEmployee(int id, Employee employee) {
        Employee oldemployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        oldemployee.setSalary(employee.getSalary());
        oldemployee.setName(employee.getName());
        employeeRepository.editEmployee(id, oldemployee);
    }

    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployee(int id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteEmployee(id);
    }

    public List<Employee> findSalaryHigherThan(int salary) {
        return employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > salary)
                .collect(Collectors.toList());
    }
}