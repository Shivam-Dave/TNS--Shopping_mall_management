package com.mallmanagement.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents an Employee entity in the database.
 */
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String name;
    private String position;
    private double salary;

    // Default constructor
    public Employee() {
        super();
    }

    // Constructor with all fields
    public Employee(Integer employeeId, String name, String position, double salary) {
        super();
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }


    // --- Getters and Setters ---

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
    }
}
