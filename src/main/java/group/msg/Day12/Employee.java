package group.msg.Day12;

import lombok.Data;

@Data
public class Employee
{
    private String name;
    private String CNP;
    private String role;

    public Employee(){}

    public Employee(String name, String CNP, String role) {
        this.name = name;
        this.CNP = CNP;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getCNP() {
        return CNP;
    }

    public String getRole() {
        return role;
    }
}
