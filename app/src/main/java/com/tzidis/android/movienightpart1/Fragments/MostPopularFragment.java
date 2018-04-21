package com.tzidis.android.movienightpart1.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tzidis.android.movienightpart1.Adapters.MovieAdapter;
import com.tzidis.android.movienightpart1.Loaders.MovieLoader;
import com.tzidis.android.movienightpart1.Objects.Movie;
import com.tzidis.android.movienightpart1.R;

import java.util.ArrayList;
import java.util.List;


public class MostPopularFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Movie>>{

    private MovieAdapter mAdapter;

    //Variable for the EmptyStateTextView
    private TextView mEmptyStateTextView;

    //Progress Bar
    private View loadingIndicator;

    //URL for the Loader
    private String url;

    //URL for movie data from the tmdb site
    private static final String BASE_REQUEST_URL_MOST_POPULAR =
            "https://api.themoviedb.org/3/movie/popular?";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = "cc672846697e7fbe5333b9777650ce6b";

    public MostPopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_list, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a grid layout manager
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create a MovieAdapter, whose data source is a list of
        // Movie s. The adapter knows how to create list item views for each item
        // in the list.
        mAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
        mRecyclerView.setAdapter(mAdapter);

        loadingIndicator = rootView.findViewById(R.id.loading_spinner);
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isConnected()) {

            loadingIndicator.setVisibility(View.VISIBLE);
            Uri builtUri = Uri.parse(BASE_REQUEST_URL_MOST_POPULAR)
                    .buildUpon()
                    .appendQueryParameter(API_KEY_PARAM, API_KEY)
                    .build();

            url = builtUri.toString();

            mEmptyStateTextView.setVisibility(View.GONE);

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.restartLoader(1, null, this);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    //Helper method to check network service
    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting());
    }

    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(getContext(), url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> movies) {

        //Set visibility of progress bar to GONE
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter of previous movie data
        mAdapter.clear();

        // If there is a valid list of {@link Movie}s, then add them to the adapter's
        // data set. This will trigger the RecyclerView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        } else {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(R.string.no_movies);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}
