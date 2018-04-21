package com.tzidis.android.movienightpart1.Adapters;

import com.squareup.picasso.Picasso;
import com.tzidis.android.movienightpart1.Objects.Movie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tzidis.android.movienightpart1.MovieDetails;
import com.tzidis.android.movienightpart1.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    // Store a member variable for the Movies
    private List<Movie> mMovies;
    // Store the context for easy access
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mImage;
        final LinearLayout mView;


        ViewHolder(View view) {
            super(view);
            mImage = view.findViewById(R.id.photo);
            mView = view.findViewById(R.id.linearLayout);
        }
    }

    // Provide a suitable constructor
    public MovieAdapter(Activity context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View newsView = inflater.inflate(R.layout.movie_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Movie currentMovie = mMovies.get(position);
        String posterPath = currentMovie.getMoviePoster();
        String imgUrl = "http://image.tmdb.org/t/p/w185" + posterPath;

        Picasso .get()
                .load(imgUrl)
                .error(R.drawable.no_image_placeholder)
                .into(holder.mImage);

        //Create an implicit intent to display the detailed movie information if the user taps on the list item
        final Intent itemIntent = new Intent(mContext, MovieDetails.class);

        //Put the properties of the Object to the intent
        itemIntent.putExtra("movieTitle", currentMovie.getOriginalTitle());
        itemIntent.putExtra("movieOverview", currentMovie.getOverview());
        itemIntent.putExtra("movieUserRating", currentMovie.getUserRating());
        itemIntent.putExtra("movieReleaseDate", currentMovie.getReleaseDate());
        itemIntent.putExtra("backdrop", currentMovie.getBackdrop());

        //Start the intent if the user taps on the list item
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(itemIntent);
            }
        });

    }

    public void addAll(List <Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void clear(){
        mMovies.clear();
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
