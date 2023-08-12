package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDto;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.PaginEmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PaginEmployeeRepository paginEmployeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PaginEmployeeRepository paginEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.paginEmployeeRepository = paginEmployeeRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAllEmployees().stream()
                .map((EmployeeDto employee) -> EmployeeDto.fromEmployee(employee.toEmployee()))
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public Integer showSalary() {
        Integer sum = getEmployees().stream()
                .map(EmployeeDto::getSalary)
                .reduce(0, Integer::sum);
        return sum;
    }

    @Override
    public List<EmployeeDto> showSalaryMin() {
        Comparator<EmployeeDto> comparator = Comparator.comparing(EmployeeDto::getSalary);
        List<EmployeeDto> minSalary = getEmployees().stream()
                .min(comparator)
                .stream()
                .collect(Collectors.toList());
        return minSalary;
    }

    @Override
    public List<EmployeeDto> showSalaryMax() {
        Comparator<EmployeeDto> comparator = Comparator.comparing(EmployeeDto::getSalary);
        List<EmployeeDto> maxSalary = getEmployees().stream()
                .max(comparator)
                .stream()
                .collect(Collectors.toList());
        return maxSalary;
    }




    @Override
    public List<EmployeeDto> getEmployeesWithSalaryHigherThan(Integer salary) {
        List<EmployeeDto> salaryEmployeeBigerThenSalary = getEmployees().stream().filter(i -> i.getSalary() >= salary).toList();
        return salaryEmployeeBigerThenSalary;
    }

    @Override
    public List<EmployeeDto> getEmployeesByIdWithRequired(Integer id) {
        List<EmployeeDto> getIdEmplyee = getEmployees().stream().filter(i -> i.equals(getEmployees().get(id))).toList();
        return getIdEmplyee;
    }

    @Override
    public void deleteEmployeesWithId(int id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void editEmployee(int id) {
        getEmployees().get(id);
    }

    @Override
    public List<EmployeeDto> findByIdGreaterThan(int number) {
        return employeeRepository.findByIdGreaterThan(10000);
    }



    @Override
    public List<EmployeeFullInfo> getEmployeesFull(int id) {
        return employeeRepository.findAllEmployeeFullInfo(id);
    }

    @Override
    public List<EmployeeFullInfo> getEmployeesFullPosition(String position) {
        return employeeRepository.getEmployeesFullPosition(position);

    }
    @Override
    public List<EmployeeFullInfo> withHighestSalary() {
        return employeeRepository.withHighestSalary();
    }
    @Override
    public List<EmployeeDto> getEmployeesWithPaging(int page,int size) {
        Pageable employeeOfConcretePage =  PageRequest.of(page,size);
        Page<EmployeeDto> allPage = employeeRepository.findAll(employeeOfConcretePage);

        return allPage.stream()
                .toList();
    }

}
