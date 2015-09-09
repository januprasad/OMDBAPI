package omdb.jenuine.com.omdbapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity implements MovieView<Movie> {


    public static final String EXTRA_IMDB_ID = "imdbID";

    private ActivityMainPresenter presenter;
    private SearchView search;
    private MovieAdapter adapter;
    private FrameLayout progress_layout;
    private ActivityMainPresenter2 presenter2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.presenter = ActivityMainPresenter.create(this);
        this.presenter2 = ActivityMainPresenter2.create(this);
        String imdbID = getIntent().getStringExtra(EXTRA_IMDB_ID);
        presenter.getMovieDetail(imdbID);
    }

    @Override
    public void addItem(Movie item) {
        toast(item.toString());
    }

    @Override
    public void addItems(Movie[] items) {

    }

    @Override
    public void clearItem() {

    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();
    }
}
