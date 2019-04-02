package com.example.deepansh.petsearchtest.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.example.deepansh.petsearchtest.View.Adapter.MoviesAdapter;
import com.example.deepansh.petsearchtest.Model.Movie;
import com.example.deepansh.petsearchtest.Model.MovieResponse;
import com.example.deepansh.petsearchtest.R;
import com.example.deepansh.petsearchtest.Network.ApiClient;
import com.example.deepansh.petsearchtest.Network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "3a85f86f12cffa54396a8d003c764f0f";
    private final static String SORT_BY = "popularity.desc";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getPopularMovies(SORT_BY,API_KEY);
        call.enqueue(new Callback<MovieResponse>()
        {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response)
            {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_row, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t)
            {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
