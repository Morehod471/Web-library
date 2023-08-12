package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

public interface PaginEmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}
