package omdb.jenuine.com.omdbapi;


import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface OmdbAPI {

    @GET("/")
    Observable<Movie> getMovie(@Query("t") final String t);

    @GET("/")
    Observable<Search> getMovies(@Query("s") final String t);

    @GET("/")
    Observable<Movie> getMovieDetail(@Query("i") final String t, @Query("plot") final String plot);
}
