package omdb.jenuine.com.omdbapi;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import omdb.jenuine.com.omdbapi.databinding.ActivityDetailBinding;
import omdb.jenuine.com.omdbapi.databinding.ListItemBinding;

public class DetailActivity extends AppCompatActivity implements MovieView<Movie> {


    public static final String EXTRA_IMDB_DATA = "movie";

    private ActivityMainPresenter presenter;
    private SearchView search;
    private MovieAdapter adapter;
    private FrameLayout progress_layout;
    private ActivityMainPresenter2 presenter2;
    ImageView poster;
    ProgressBar progress_circular;
    ActivityDetailBinding  binding;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        this.presenter = ActivityMainPresenter.create(this);
        this.presenter2 = ActivityMainPresenter2.create(this);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_IMDB_DATA);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setMovie(movie);
        poster = (ImageView) findViewById(R.id.poster);
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        if (movie.getPoster().length() > 5) {
            progress_circular.setVisibility(View.VISIBLE);
            Glide.with(this).load(movie.getPoster()).into(poster);
        } else {
            progress_circular.setVisibility(View.GONE);
            poster.setImageResource(R.drawable.movie);
        }
        presenter.getMovieDetail(movie.getImdbID());
    }

    @Override
    public void addItem(Movie movie) {
        binding.setMovie(movie);
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
