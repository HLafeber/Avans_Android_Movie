package nl.avans.avansbioscoopapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nl.avans.avansbioscoopapp.Objects.Film;
import nl.avans.avansbioscoopapp.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>
{
    private List<Film> movieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MoviesAdapter(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.movieList = new ArrayList<>();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_image);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.fragment_movie_list_main, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        Film movie = movieList.get(position);

        // get image from moviedb and load them in
        Picasso.get()
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return (movieList == null) ? 0 : movieList.size();
    }

    public void setMovieList(List<Film> movieList)
    {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        // Crash #1, Adapter wist niet dat de data was veranderd. Dit fixed de crash
        notifyDataSetChanged();
    }

    public List<Film> getMovieList(){
        return this.movieList;
    }
}