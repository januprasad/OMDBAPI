package omdb.jenuine.com.omdbapi;


import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActivityMainPresenter2 implements Observer<Search> {
    private static final String OMDBAPI_API = "http://www.omdbapi.com";

    private final OmdbAPI service;

    private final MovieView<Movie> view;

    public ActivityMainPresenter2(final OmdbAPI service, final MovieView<Movie> view) {
        this.service = service;
        this.view = view;
    }

    public static ActivityMainPresenter2 create(final MovieView<Movie> view) {
        final OmdbAPI service = ActivityMainPresenter2.getRestAdapter().create(OmdbAPI.class);
        return new ActivityMainPresenter2(service, view);
    }


    private static RestAdapter getRestAdapter() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(OMDBAPI_API)
                .build();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(final Throwable exception) {
    }

    @Override
    public void onNext(Search movies) {
        this.view.addItems(movies.getMovies());
    }

    public void getMovies(String query) {
        this.service.getMovies(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

    }
}
