package omdb.jenuine.com.omdbapi;


/**
 * Created by jenu on 4/9/15.
 */
public class Movie {
    public String Title, Year, Runtime, Genre, Poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    @Override
    public String toString() {
        return "Title : "+getTitle()+"\n"+"Year : "+getYear()+"\n"+"Runtime : "+getRuntime()+"\n"+"Genre : "+getGenre()+"\n";
    }
}
