package ru.skypro.lessons.springboot.weblibrary.service;


import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }

    public EmployeeDto fromEntity(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
