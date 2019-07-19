package group.msg.jsf_MyBean;

import lombok.Data;

import java.util.LinkedList;

@Data
public class MovieList
{
    private LinkedList<Movie> listaFilme=new LinkedList<>();

    public void add(Movie e)
    {
        listaFilme.add(e);
    }
}
