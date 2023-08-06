package ru.skypro.lessons.springboot.weblibrary.model;

public class Employee {

    private int idGenerator;

    private int id;
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.id = idGenerator++;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
