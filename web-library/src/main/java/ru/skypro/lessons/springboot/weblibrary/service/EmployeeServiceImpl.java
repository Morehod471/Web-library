package ru.skypro.lessons.springboot.weblibrary.service;

import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.*;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotFoundException;
import ru.skypro.lessons.springboot.weblibrary.exception.EmployeeNotValidException;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.model.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper) {
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

    @Override
    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeFullInfo> findAllEmployeeFullInfo() {
        return employeeRepository.findAllEmployeeFullInfo();
    }

    @Override
    public Integer findSalary() {
        return employeeRepository.findSalary();
    }

    @Override
    public EmployeeDto findSalaryMin() {
        Page<EmployeeDto> page = employeeRepository.findSalaryMin(PageRequest.of(0, 1));
        if (page.isEmpty()) {
            return null;
        }
        return page.getContent().get(0);
    }

    @Override
    public EmployeeDto findSalaryMax() {
        List<EmployeeDto> employeeWithMaxSalary = findEmployeeWithHighestSalary() ;
        if (employeeWithMaxSalary.isEmpty()) {
            return null;
        }
        return employeeWithMaxSalary.get(0);
    }

    @Override
    public List<EmployeeDto> findSalaryHigh() {
        double average = employeeRepository.findAvgSalary();
        return findSalaryHigherThan(average);
    }

    @Override
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
                .collect(Collectors.toList());
    }

    @Override
    public void editEmployee(int id, EmployeeDto employee) {
        Employee oldemployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        oldemployee.setSalary(employee.getSalary());
        oldemployee.setName(employee.getName());
        employeeRepository.save(oldemployee);
    }

    @Override
    public EmployeeDto findEmployeeById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::fromEntity)
                .map(employeeDto -> {
                    employeeDto.setPosition(null);
                    return employeeDto;
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeDto> findSalaryHigherThan(double salary) {
        return employeeRepository.findEmployeesBySalaryGreaterThan(salary).stream()
                .map(employeeMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> findEmployeeWithHighestSalary() {
        return employeeRepository.findSalaryMax();
    }

    @Override
    public List<EmployeeDto> findEmployee(@Nullable String position) {
        return Optional.ofNullable(position)
                .map(employeeRepository::findEmployeesByPosition_Position)
                .orElseGet(employeeRepository::findAll)
                .stream()
                .map(employeeMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getFullInfo(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::fromEntity)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<EmployeeDto> findEmployeeFromPage(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10)).stream()
                .map(employeeMapper::fromEntity)
                .collect(Collectors.toList());
    }
}