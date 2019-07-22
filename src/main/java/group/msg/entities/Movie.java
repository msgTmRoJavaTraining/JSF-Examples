package group.msg.entities;

import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class Movie {
    public String name;
    public Date productionDate;
    public double rating;
    public String gender;
    public List<String> suitableFor;



    public Movie(){
    }
    public Movie(Movie m){
        this.name = m.name;
        this.rating = m.rating;
        this.productionDate= m.productionDate;
        this.gender= m.gender;
        this.suitableFor= m.suitableFor;

    }
    public Movie(String name, Date productionDate, double rating,String gender,List<String> suitableFor) {
        this.name = name;
        this.productionDate = productionDate;
        this.rating = rating;
        this.gender= gender;
        this.suitableFor= suitableFor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie{" +
                "name='" + name + '\'' +
                ", year=" + productionDate +
                ", rating=" + rating +
                ", gender='" + gender + '\'');
        if(suitableFor!=null) {
            sb.append(", suitableFor='" + suitableFor.toString() + "\'" + '}');
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(List<String> suitableFor) {
        this.suitableFor = suitableFor;
    }
}
