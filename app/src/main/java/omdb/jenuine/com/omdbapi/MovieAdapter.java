package omdb.jenuine.com.omdbapi;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import omdb.jenuine.com.omdbapi.databinding.ListItemBinding;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final View empty;
    private List<Movie> repos = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public MovieAdapter(final View empty) {
        this.empty = empty;
    }

    public List<Movie> getRepos() {
        return repos;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Movie repo = this.repos.get(position);
        holder.bind(repo);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    private void dataSetChanged() {
        this.notifyDataSetChanged();
        this.empty.setVisibility(this.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @UiThread
    public void addItem(final Movie movie) {
        if (movie == null || movie.getTitle() == null)
            return;
        this.repos.add(0, movie);
        this.notifyItemInserted(0);
        this.notifyItemRangeChanged(1, repos.size());
        this.dataSetChanged();
    }


    @UiThread
    public void clearItems() {
        this.repos.clear();
        this.dataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ListItemBinding binding;
        private final ProgressBar progress_circular;
        ImageView poster;

        public ViewHolder(final View view, final ListItemBinding binding) {
            super(view);
            this.binding = binding;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                view.setBackgroundResource(R.drawable.ripple_effect);
                view.setOnClickListener(this);
            poster = (ImageView) view.findViewById(R.id.poster);
            progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        }

        @UiThread
        public void bind(final Movie movie) {
            this.binding.setMovie(movie);
            if (movie.getPoster().length() > 5) {
                progress_circular.setVisibility(View.VISIBLE);
                Glide.with(this.binding.getRoot().getContext()).load(movie.getPoster()).into(poster);
            } else {
                progress_circular.setVisibility(View.GONE);
                poster.setImageResource(R.drawable.movie);
            }
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getPosition());
        }
    }
}