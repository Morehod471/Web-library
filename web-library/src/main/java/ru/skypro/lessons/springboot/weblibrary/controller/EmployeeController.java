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

    @GetMapping("/all")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/salary/sum")
    public Integer showSalary() {
        return employeeService.findSalary();
    }

    @GetMapping("/salary/min")
    public Employee showSalaryMin() {
        return employeeService.findSalaryMin();
    }

    @GetMapping("/salary/max")
    public Employee showSalaryMax() {
        return employeeService.findSalaryMax();
    }

    @GetMapping("/high-salary")
    public List<Employee> showSalaryHigh() {
        return employeeService.findSalaryHigh();
    }

    @PostMapping
    public List<Employee> addEmployee(@RequestBody List<Employee> employeeList) {
        return employeeService.addEmployee(employeeList);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employeeService.editEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<Employee> findSalaryHigherThan(@RequestParam int salary) {
        return employeeService.findSalaryHigherThan(salary);
    }
}
