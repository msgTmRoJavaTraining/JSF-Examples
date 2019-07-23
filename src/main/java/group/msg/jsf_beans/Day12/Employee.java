package group.msg.jsf_beans.Day12;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {
    private String name;
    private String role;
    private int salary;

    public Employee(){}

    public Employee(String name, String role, int salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public Employee(Employee e) {
        this.name = e.name;
        this.role = e.role;
        this.salary = e.salary;
    }
}


