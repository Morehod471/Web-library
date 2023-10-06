package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Position;

import java.util.Optional;

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
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setPosition(
                Optional.ofNullable(employee.getPosition())
                        .map(Position::getPosition)
                        .orElse(null)
        );
        return employeeDto;
    }
}
