package group.msg.entities;

import lombok.Data;

@Data
public class Movie {
    private String name;
    private int year;
    private double grade;

    public Movie(){
    }
    public Movie(Movie m){
        this.name = m.name;
        this.grade = m.grade;
        this.year= m.year;
    }
    public Movie(String name, int year, double grade) {
        this.name = name;
        this.year = year;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", grade=" + grade +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
