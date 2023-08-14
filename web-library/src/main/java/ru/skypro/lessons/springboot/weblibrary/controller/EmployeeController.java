package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public Integer showSalary() {
        return employeeService.findSalary();
    }

    @GetMapping("/salary/min")
    public EmployeeDto showSalaryMin() {
        return employeeService.findSalaryMin();
    }

    @GetMapping("/salary/max")
    public EmployeeDto showSalaryMax() {
        return employeeService.findSalaryMax();
    }

    @GetMapping("/high-salary")
    public List<EmployeeDto> showSalaryHigh() {
        return employeeService.findSalaryHigh();
    }

    @PostMapping
    public List<EmployeeDto> addEmployee(@RequestBody List<EmployeeDto> employeeList) {
        return employeeService.addEmployee(employeeList);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody EmployeeDto employee) {
        employeeService.editEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<EmployeeDto> findSalaryHigherThan(@RequestParam int salary) {
        return employeeService.findSalaryHigherThan(salary);
    }
}