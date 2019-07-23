package group.msg.ejb_exercise;

import lombok.Data;

@Data
public class Employee {
    private String CNP;
    private String name;
    private String role;

    public Employee(){}
    public Employee(String CNP, String name, String role) {
        this.CNP = CNP;
        this.name = name;
        this.role = role;
    }
    public Employee(Employee emp){
        this.CNP= emp.CNP;
        this.name= emp.name;
        this.role=emp.role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "CNP='" + CNP + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
