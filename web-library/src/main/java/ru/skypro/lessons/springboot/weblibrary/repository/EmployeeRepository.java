package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT SUM(e.salary) FROM Employee e")
    int findSalary();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    double findAverageSalary();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto(e.id, e.name, e.salary) FROM Employee e " +
            "WHERE e.salary = (SELECT MIN (e.salary) FROM Employee e)")
    Page<EmployeeDto> findSalaryMin(Pageable pageable);

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto(e.id, e.name, e.salary) FROM Employee e " +
            "WHERE e.salary = (SELECT MAX (e.salary) FROM Employee e)")
    Page<EmployeeDto> findSalaryMax(Pageable pageable);

    List<EmployeeDto> findEmployeesWithSalaryHigherThan(double salary);

}
