package com.tzidis.android.movienightpart1.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String mOriginalTitle;
    private String mMoviePoster;
    private String mOverview;
    private double mUserRating;
    private String mReleaseDate;
    private String mBackdrop;

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mOriginalTitle);
        out.writeString(mMoviePoster);
        out.writeString(mOverview);
        out.writeDouble(mUserRating);
        out.writeString(mReleaseDate);
        out.writeString(mBackdrop);
    }

    private Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mMoviePoster = in.readString();
        mOverview = in.readString();
        mUserRating = in.readDouble();
        mReleaseDate = in.readString();
        mBackdrop = in.readString();
    }

    /*
     * Create a new Movie object.
     * */

    public Movie(String originalTitle, String moviePoster, String overview, double userRating,
                 String releaseDate, String backdrop){

        mOriginalTitle = originalTitle;
        mMoviePoster = moviePoster;
        mOverview = overview;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
        mBackdrop = backdrop;
    }

    /**
     * Get and Set the Original title of the movie
     */
    public String getOriginalTitle(){return mOriginalTitle;}
    public void setOriginalTitle(String originalTitle){mOriginalTitle = originalTitle;}

    /**
     * Get and Set the path to the poster of the movie
     */
    public String getMoviePoster(){return mMoviePoster;}
    public void setMoviePoster(String moviePoster){mMoviePoster = moviePoster;}

    /**
     * Get and Set the plot synopsis of the movie
     */
    public String getOverview(){return mOverview;}
    public void setOverview(String overview){mOverview = overview;}

    /**
     * Get and Set the rating users gave to the movie
     */
    public double getUserRating(){return mUserRating;}
    public void setUserRating(double userRating){mUserRating = userRating;}

    /**
     * Get and Set the date the movie has been released in the US
     */
    public String getReleaseDate(){return mReleaseDate;}
    public void setReleaseDate(String releaseDate){mReleaseDate = releaseDate;}

    /**
     * Get and Set the the path to the backdrop of the movie
     */
    public String getBackdrop(){return mBackdrop;}
    public void setBackdrop(String backdrop){mBackdrop = backdrop;}


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {


        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }


        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
