package group.msg.jsf_MyBean;

import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Named
@ViewScoped
public class MyDataTableBackingBean extends LazyDataModel<Movie>
{

    private Movie selectedMovie;
    private List<Movie> movieList;
    private String outputMessage;

    @PostConstruct
    public void init() {
        movieList = MySessionScopeBackingBean.getList();
    }

    @Override
    public Movie getRowData(String rowKey) {
        Integer id = Integer.parseInt(rowKey);
        return movieList.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Object getRowKey(Movie object) {
        return object.getId();
    }

    @Override
    public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Movie> filteredList = new ArrayList<>();
        movieList.forEach(movie -> {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(movie.getClass().getField(filterProperty).get(movie));

                        if (filterValue.equals("") || filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }
            if (match) {
                filteredList.add(movie);
            }
        });
        int dataSize = filteredList.size();
        if (sortField != null) {
            filteredList.sort(new group.msg.jsf_MyBean.MyDataTableBackingBean.MovieSorter(sortField, sortOrder));
        }
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return filteredList.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return filteredList.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return filteredList;
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
        public int compare(Movie movie1, Movie movie2) {
            try {
                Object val1 = Movie.class.getField(sortField).get(movie1);
                Object val2 = Movie.class.getField(sortField).get(movie2);


                int comparationResult = ((Comparable) val1).compareTo(val2);

                return SortOrder.ASCENDING.equals(sortOrder) ? comparationResult : (-1) * comparationResult;
            } catch (Exception e) {
                return 1;
            }
        }
    }

    public void rowSelected(SelectEvent event) {
        outputMessage = "Selected movie: "+selectedMovie.getName();
    }

}



//import group.msg.AnimalsHolder;
//import group.msg.entities.Animal;
//import lombok.Data;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.model.LazyDataModel;
//import org.primefaces.model.SortOrder;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import java.util.*;
//import java.util.stream.Collectors;

//@Data
//@Named
//@ViewScoped
//public class MyDataTableBackingBean extends LazyDataModel<Movie> {
//    private Movie selectedAnimal;
//    private List<Movie> animalsList;
//    private String outputMessage;
//
//    @PostConstruct
//    public void init() {
//        animalsList = MySessionScopeBackingBean.getList();
//    }
//
//    @Override
//    public Movie getRowData(String rowKey) {
//        Integer id = Integer.parseInt(rowKey);
//        return animalsList.stream().filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
//    }
//
//    @Override
//    public Object getRowKey(Movie object) {
//        return object.getId();
//    }
//
//    @Override
//    public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//        List<Movie> filteredList = new ArrayList<>();
//        animalsList.forEach(animal -> {
//            boolean match = true;
//            if (filters != null) {
//                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
//                    try {
//                        String filterProperty = it.next();
//                        Object filterValue = filters.get(filterProperty);
//                        String fieldValue = String.valueOf(animal.getClass().getField(filterProperty).get(animal));
//
//                        if (filterValue.equals("") || filterValue == null || fieldValue.startsWith(filterValue.toString())) {
//                            match = true;
//                        } else {
//                            match = false;
//                            break;
//                        }
//                    } catch (Exception e) {
//                        match = false;
//                    }
//                }
//            }
//            if (match) {
//                filteredList.add(animal);
//            }
//        });
//
//        int dataSize = filteredList.size();
//        if (sortField != null) {
//            filteredList.sort(new group.msg.jsf_MyBean.MyDataTableBackingBean.MovieSorter(sortField, sortOrder));
//        }
//        this.setRowCount(dataSize);
//
//        //paginate
//        if (dataSize > pageSize) {
//            try {
//                return filteredList.subList(first, first + pageSize);
//            } catch (IndexOutOfBoundsException e) {
//                return filteredList.subList(first, first + (dataSize % pageSize));
//            }
//        } else {
//            return filteredList;
//        }
//    }
//
//    public static class MovieSorter implements Comparator<Movie> {
//        private String sortField;
//        private SortOrder sortOrder;
//
//        public MovieSorter(String sortField, SortOrder sortOrder) {
//            this.sortField = sortField;
//            this.sortOrder = sortOrder;
//        }
//
//        @Override
//        public int compare(Movie animal, Movie t1) {
//            try {
//                Object val1 = Movie.class.getField(sortField).get(animal);
//                Object val2 = Movie.class.getField(sortField).get(t1);
//
//                int comparationResult = ((Comparable) val1).compareTo(val2);
//
//                return SortOrder.ASCENDING.equals(sortOrder) ? comparationResult : (-1) * comparationResult;
//            } catch (Exception e) {
//                return 1;
//            }
//        }
//    }
//    public void rowSelected(SelectEvent event) {
//        outputMessage = selectedAnimal.getName();
//    }
//}
//
