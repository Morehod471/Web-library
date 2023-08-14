package ru.skypro.lessons.springboot.weblibrary.exception;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

public class EmployeeNotValidException extends RuntimeException {

    private final EmployeeDto employee;

    public EmployeeNotValidException(EmployeeDto employee) {
        this.employee = employee;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }
}