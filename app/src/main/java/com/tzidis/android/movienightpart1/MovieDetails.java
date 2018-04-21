package com.tzidis.android.movienightpart1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity{

    private final static String MOVIE_BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get the properties of the Movie Object from the intent
        Bundle b = getIntent().getExtras();
        final String movieTitle = b.getString("movieTitle");
        final String movieOverview = b.getString("movieOverview");
        final double movieUserRating = b.getDouble("movieUserRating");
        final String movieReleaseDate = b.getString("movieReleaseDate");
        final String backdrop = b.getString("backdrop");

        Toolbar detailedToolbar = findViewById(R.id.detailed_toolbar);
        //Set custom title
        detailedToolbar.setTitle(movieTitle);
        setSupportActionBar(detailedToolbar);

        //Show "back" button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the image to the ImageView in the activity_movie_details.xml
        ImageView imageView = findViewById(R.id.detailed_image);

        String imgUrl = MOVIE_BACKDROP_BASE_URL + backdrop;

        Picasso.get()
                .load(imgUrl)
                .into(imageView);

        //Set the user rating to the RatingBar in the activity_movie_details.xml
        RatingBar userRating = findViewById(R.id.ratingBar);
        userRating.setMax(5);
        userRating.setStepSize(0.1f);
        userRating.setRating((float) movieUserRating/2);

        //Set the release date to the TextView in the activity_movie_details.xml
        TextView releaseTextView = findViewById(R.id.detailed_release_date);
        releaseTextView.setText(movieReleaseDate);

        //Set the overview to the TextView in the activity_movie_details.xml
        TextView overviewTextView = findViewById(R.id.detailed_overview);
        overviewTextView.setText(movieOverview);

    }
}
