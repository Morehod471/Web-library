package ru.skypro.lessons.springboot.weblibrary.model;

public class EmployeeFullInfo {

    private int id;
    private String name;
    private Integer salary;
    private String position;

    public EmployeeFullInfo(int id, String name, Integer salary, String position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public EmployeeFullInfo(String name, Integer salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
