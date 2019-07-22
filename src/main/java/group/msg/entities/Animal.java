package group.msg.entities;

import lombok.Data;

import java.util.Arrays;

@Data
public class Animal {
    public int id;
    public String tip;
    public String nume;
    public int age;
    public String ownerName;

    public Animal() {
    }

    public Animal(int id, String tip, String nume, int age, String ownerName) {
        this.id = id;
        this.tip = tip;
        this.nume = nume;
        this.age = age;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }
}
