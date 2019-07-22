package JSFExercisesClasses;



import JSFExercisesClasses.Entities.Movie;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MovieHolder {


    private static List<Movie> movie;
    static {
        movie = new ArrayList<>();
    }
    public static void addMovie(Movie movie1) {


        movie.add(movie1);

    }

    public static List<Movie> getMovies(){
        return movie;
    }
}
