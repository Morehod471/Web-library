package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<EmployeeDto> findAllEmployees();

    List<EmployeeDto> findByIdGreaterThan(int number);

    Page<EmployeeDto> findAll(Pageable employeeOfConcretePage);

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.id, e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and e.id= :id")
    List<EmployeeFullInfo> findAllEmployeeFullInfo(@Param("id") int id);

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p and p.name = :position")
    List<EmployeeFullInfo> getEmployeesFullPosition(@Param("position") String position);

    @Query( "SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e  left join Position p on p.id= e.position.id " +
            "WHERE e.salary = (select  max (e.salary) from  Employee e) ")
    List<EmployeeFullInfo> withHighestSalary();
}
