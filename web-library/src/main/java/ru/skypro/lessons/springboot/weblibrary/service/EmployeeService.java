package ru.skypro.lessons.springboot.weblibrary.service;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.*;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotFoundException;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotValidException;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    public void init() {
        employeeRepository.deleteAll();

        employeeRepository.saveAll(
                List.of(
                        new Employee("Катя", 90_000),
                        new Employee("Дима", 102_000),
                        new Employee("Олег", 80_000),
                        new Employee("Вика", 165_000)
                )
        );
    }

        public Integer findSalary() {
        return employeeRepository.findSalary();
    }

    public EmployeeDto findSalaryMin() {
        Page<EmployeeDto> page = employeeRepository.findSalaryMin(PageRequest.of(0, 1));
        if (page.isEmpty()) {
            return null;
        }
        return page.getContent().get(0);
    }

    public EmployeeDto findSalaryMax() {
        Page<EmployeeDto> page = employeeRepository.findSalaryMax(PageRequest.of(0, 1));
        if (page.isEmpty()) {
            return null;
        }
        return page.getContent().get(0);
    }

    public List<EmployeeDto> findSalaryHigh() {
        double average = employeeRepository.findAverageSalary();
        return findEmployeesWithSalaryHigherThan(average);
    }

    public List<EmployeeDto> addEmployee(List<EmployeeDto> employeeList) {
        Optional<EmployeeDto> incorrectEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() <= 0 || employee.getName() == null || employee.getName().isEmpty())
                .findFirst();
        if (incorrectEmployee.isPresent()){
            throw new EmployeeNotValidException(incorrectEmployee.get());
        }
        return employeeRepository.saveAll(employeeList.stream()
                        .map(employeeMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(employeeMapper::fromEntity)
                .collect((Collectors.toList()));
    }

    public void editEmployee(int id, EmployeeDto employee) {
        Employee oldemployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        oldemployee.setSalary(employee.getSalary());
        oldemployee.setName(employee.getName());
        employeeRepository.save(oldemployee);
    }

    public EmployeeDto findEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::fromEntity)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployee(int id) {
        Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(employee);
    }

    public List<EmployeeDto> findEmployeesWithSalaryHigherThan(double salary) {
        return employeeRepository.findEmployeesWithSalaryHigherThan(salary);
    }
}