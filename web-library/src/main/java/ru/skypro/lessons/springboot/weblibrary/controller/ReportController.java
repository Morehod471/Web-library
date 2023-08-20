package ru.skypro.lessons.springboot.weblibrary.controller;


import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final EmployeeService employeeService;

    public ReportController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public int report() {
        return employeeService.generateReport();
    }

    @GetMapping("/{id}")
    public String find(@PathVariable int id) {
        return employeeService.findReport(id);
    }


}
