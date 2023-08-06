package ru.skypro.lessons.springboot.weblibrary.exception;

import ru.skypro.lessons.springboot.weblibrary.model.Employee;

public class EmployeeNotValidException extends RuntimeException {

    private final Employee employee;

    public EmployeeNotValidException(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}