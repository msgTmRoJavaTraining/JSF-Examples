package group.msg.jsf_exerciseBeans;

import group.msg.entities.Movie;
import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Named
@ViewScoped

public class DataTableBean extends LazyDataModel<Movie> implements Serializable {
    private Movie selectedMovie;
    @Inject
    private MoviesBean mb;
    private List<Movie> movieList;
    private String outputMessage;



    @Override
    public Movie getRowData(String rowKey) {
        String name = rowKey;
        return movieList.stream().filter(a -> a.getName().equals( name)).collect(Collectors.toList()).get(0);
    }

    @Override
    public Object getRowKey(Movie object) {
        return object.getName();
    }

    @Override
    public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Movie> data = new ArrayList<>();
        movieList= mb.getMoviesList();
        //filter
        for(Movie car : movieList) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        }
                        else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(car);
            }
        }

        //sort
            if(sortField != null) {
            Collections.sort(data,new DataTableBean.MovieSorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }


    public static class MovieSorter implements Comparator<Movie> {
        private String sortField;
        private SortOrder sortOrder;

        public MovieSorter(String sortField, SortOrder sortOrder) {
            this.sortField = sortField;
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(Movie movie, Movie t1) {
            try {
                Object val1 = Movie.class.getField(sortField).get(movie);
                Object val2 = Movie.class.getField(sortField).get(t1);

                int comparationResult = ((Comparable) val1).compareTo(val2);

                return SortOrder.ASCENDING.equals(sortOrder) ? comparationResult : (-1) * comparationResult;
            } catch (Exception e) {
                return 1;
            }
        }
    }
    public void rowSelected(SelectEvent event) {
        outputMessage = selectedMovie.getName();
    }

}
