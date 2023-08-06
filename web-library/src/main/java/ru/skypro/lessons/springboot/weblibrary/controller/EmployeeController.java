package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
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
    public List<Employee> showSalaryMin() {
        return employeeService.findSalaryMin();

    }

    @GetMapping("/salary/max")
    public List<Employee> showSalaryMax() {
        return employeeService.findSalaryMax();
    }

    @GetMapping("/high-salary")
    public List<Employee> showSalaryHigh() {
        return employeeService.findSalaryHigh();
    }

}
