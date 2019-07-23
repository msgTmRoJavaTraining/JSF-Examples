package group.msg.entities;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private String cnp;
    private String role;

    public Employee(String name, String cnp, String role) {
        this.name = name;
        this.cnp = cnp;
        this.role = role;
    }
}
