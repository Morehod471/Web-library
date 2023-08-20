package ru.skypro.lessons.springboot.weblibrary.service;

import jakarta.annotation.Nullable;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.model.EmployeeFullInfo;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> findAllEmployees();

    Integer findSalary();

    EmployeeDto findSalaryMin();

    EmployeeDto findSalaryMax();

    List<EmployeeDto> findSalaryHigh();

    List<EmployeeDto> addEmployee(List<EmployeeDto> employeeList);

    void editEmployee(int id, EmployeeDto employee);

    EmployeeDto findEmployeeById(int id);

    void deleteEmployee(int id);

    List<EmployeeDto> findSalaryHigherThan(double salary);

    List<EmployeeDto> findEmployeeWithHighestSalary();

    List<EmployeeDto> findEmployee(@Nullable String position);

    EmployeeDto getFullInfo(int id);

    List<EmployeeDto> findEmployeeFromPage(int page);

    List<EmployeeFullInfo> findAllEmployeeFullInfo();
}
