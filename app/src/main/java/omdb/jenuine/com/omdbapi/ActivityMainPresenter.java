package omdb.jenuine.com.omdbapi;


import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActivityMainPresenter implements Observer<Movie> {
    private static final String OMDBAPI_API = "http://www.omdbapi.com";

    private final OmdbAPI service;

    private final MovieView<Movie> view;

    public ActivityMainPresenter(final OmdbAPI service, final MovieView<Movie> view) {
        this.service = service;
        this.view = view;
    }

    public static ActivityMainPresenter create(final MovieView<Movie> view) {
        final OmdbAPI service = ActivityMainPresenter.getRestAdapter().create(OmdbAPI.class);
        return new ActivityMainPresenter(service, view);
    }


    private static RestAdapter getRestAdapter() {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(OMDBAPI_API)
                .build();
    }

    public void getMovie(final String t) {
        this.service.getMovie(t)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(final Throwable exception) {
    }

    @Override
    public void onNext(Movie movie) {
        this.view.addItem(movie);
    }


}
