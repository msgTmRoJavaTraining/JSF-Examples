package group.msg.jsf_beans.Day10;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Movie {

    private String name;
    private String genre;
    private int rating;
    private Date productionYear;
    private List<String> genreList;
    private List<String> suitableFor;

    public void init(){

        genreList=new ArrayList<>();

        genreList.add("Romance");
        genreList.add("Action");
        genreList.add("Horror");
        genreList.add("Mistery");
    }

    public Movie(){
        productionYear=new Date();
    }

    public Movie(String name, String genre, int rating, Date productionYear,List<String> genreList,List<String> suitableFor) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.productionYear=productionYear;
        this.genreList=genreList;
        this.suitableFor=suitableFor;
    }
    public Movie(Movie m) {
        this.name = m.getName();
        this.genre =m.getGenre();
        this.rating = m.getRating();
        this.productionYear=m.productionYear;
        this.suitableFor=m.suitableFor;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    //    public String getProductionYearString(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
//        return simpleDateFormat.format(productionYear);
//    }
}
