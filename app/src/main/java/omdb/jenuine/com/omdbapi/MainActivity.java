package omdb.jenuine.com.omdbapi;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends BaseActivity implements MovieView<Movie>, SearchView.OnQueryTextListener {
    private ActivityMainPresenter presenter;
    private SearchView search;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = ActivityMainPresenter.create(this);
        this.adapter = new MovieAdapter(this.findViewById(R.id.empty));
        final RecyclerView recycler = (RecyclerView) this.findViewById(R.id.movies);
        recycler.setAdapter(this.adapter);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        this.search = (SearchView) MenuItemCompat.getActionView(item);
        this.search.setOnQueryTextListener(this);
        this.search.onActionViewExpanded();

        return true;
    }

    @Override
    public void toast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onQueryTextSubmit(final String query) {
        this.presenter.getMovie(query);
        this.search.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(final String query) {
        return true;
    }

    @Override
    public void addItem(Movie item) {
        adapter.addItem(item);
        showToast(item);
    }

    private void showToast(Movie item) {
        Toast.makeText(MainActivity.this,item+"", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void clearItem() {

    }
}

