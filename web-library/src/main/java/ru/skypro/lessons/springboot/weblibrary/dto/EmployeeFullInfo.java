package ru.skypro.lessons.springboot.weblibrary.dto;

public class EmployeeFullInfo {
    private int id;
    private String name;
    private Integer salary;
    private String positionName;


    public EmployeeFullInfo(int id, String name, Integer salary, String positionName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.positionName = positionName;
    }
    public EmployeeFullInfo( String name, Integer salary, String positionName) {
        this.name = name;
        this.salary = salary;
        this.positionName = positionName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}