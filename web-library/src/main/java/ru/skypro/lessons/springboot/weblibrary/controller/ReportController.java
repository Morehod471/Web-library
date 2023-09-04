package ru.skypro.lessons.springboot.weblibrary.controller;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Resource> find(@PathVariable int id) {
        Resource resource = employeeService.findReport(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.json\"")
                .body(resource);
    }


}
