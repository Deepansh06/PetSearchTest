package com.example.deepansh.petsearchtest.View.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deepansh.petsearchtest.Model.Genre;
import com.example.deepansh.petsearchtest.Model.Movie;
import com.example.deepansh.petsearchtest.R;
import com.example.deepansh.petsearchtest.Network.ApiClient;
import com.example.deepansh.petsearchtest.Network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity  extends AppCompatActivity
{
    private TextView txt_title,txt_description, txt_genres, txt_date, txt_rating,
            txt_duration, txt_lang,txt_budget, txt_revenue;
    private ImageView img_backdrop;
    private int movieId;

    private final static String API_KEY = "3a85f86f12cffa54396a8d003c764f0f";
    private static final String LANGUAGE = "en-US";
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        movieId = getIntent().getIntExtra("movie_id", movieId);
        dialog = new ProgressDialog(this);
        init();
    }
    private void init()
    {
        img_backdrop = findViewById(R.id.img_backdrop);
        txt_description = findViewById(R.id.txt_description);
        txt_genres = findViewById(R.id.txt_genres);
        txt_date = findViewById(R.id.txt_date);
        txt_rating = findViewById(R.id.txt_rating);
        txt_duration = findViewById(R.id.txt_duration);
        txt_lang = findViewById(R.id.txt_lang);
        txt_budget = findViewById(R.id.txt_budget);
        txt_revenue = findViewById(R.id.txt_revenue);

        dialog.setMessage("Please wait...");
        dialog.show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> call = apiService.getMovieDetails(movieId,API_KEY,LANGUAGE);
        call.enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                if (response.isSuccessful())
                {
                    Movie movie = response.body();
                    dialog.dismiss();

                    Glide.with(MovieDetailsActivity.this).
                            load(IMAGE_BASE_URL + movie.getBackdropPath()).into(img_backdrop);

                    getSupportActionBar().setTitle(movie.getTitle());

                    txt_description.setText(movie.getOverview());
                    txt_date.setText(movie.getReleaseDate());

                    List<String> currentGenres = new ArrayList<>();
                    for (Genre genre : movie.getGenres())
                    {
                        currentGenres.add(genre.getName());
                    }
                    txt_genres.setText(TextUtils.join(", ", currentGenres));

                    txt_rating.setText(movie.getVoteAverage().toString());
                    txt_duration.setText(movie.getRuntime().toString()+" minutes");
                    txt_lang.setText(movie.getOriginalLanguage());

                    int i = (int) Math.round(movie.getBudget());
                    String str = String.valueOf(i);
                    if (str.length() >= 6 && str.length() <= 8)
                    {
                        if(str.length()==6)
                        {
                            str = str.substring(0, 1) +"."+str.substring(1, 3)+ " Thousand";
                        }
                        else if(str.length()==7)
                        {
                            str = str.substring(0, 2) +"."+str.substring(2, 3)+ " Thousand";
                        }
                        else if(str.length()==8)
                        {
                            str = str.substring(0, 3) + " Thousand";
                        }
                    }
                    else if (str.length() >= 7 && str.length() <= 9)
                    {
                        if(str.length()==7)
                        {
                            str = str.substring(0, 1) +"."+str.substring(1, 3)+ " Million";
                        }
                        else if(str.length()==8)
                        {
                            str = str.substring(0, 2) +"."+str.substring(2, 3)+ " Million";
                        }
                        else if(str.length()==9)
                        {
                            str = str.substring(0, 3) + " Million";
                        }
                    }
                    else if (str.length() >= 10 && str.length() <= 12)
                    {
                        if(str.length()==10)
                        {
                            str = str.substring(0, 1) +"."+str.substring(1, 3)+ " Billion";
                        }
                        else if(str.length()==11)
                        {
                            str = str.substring(0, 2) +"."+str.substring(2, 3)+ " Billion";
                        }
                        else if(str.length()==12)
                        {
                            str = str.substring(0, 3) + " Billion";
                        }
                    }
                    txt_budget.setText("$"+str);


                    int j = (int) Math.round(movie.getRevenue());
                    String str1 = String.valueOf(j);
                    if (str1.length() >= 6 && str1.length() <= 8)
                    {
                        if(str1.length()==6)
                        {
                            str1 = str1.substring(0, 1) +"."+str1.substring(1, 3)+ " Thousand";
                        }
                        else if(str1.length()==7)
                        {
                            str1 = str1.substring(0, 2) +"."+str1.substring(2, 3)+ " Thousand";
                        }
                        else if(str1.length()==8)
                        {
                            str1 = str1.substring(0, 3) + " Thousand";
                        }
                    }
                    else if (str1.length() >= 7 && str1.length() <= 9)
                    {
                        if(str1.length()==7)
                        {
                            str1 = str1.substring(0, 1) +"."+str1.substring(1, 3)+ " Million";
                        }
                        else if(str1.length()==8)
                        {
                            str1 = str1.substring(0, 2) +"."+str1.substring(2, 3)+ " Million";
                        }
                        else if(str1.length()==9)
                        {
                            str1 = str1.substring(0, 3) + " Million";
                        }
                    }
                    else if (str1.length() >= 10 && str1.length() <= 12)
                    {
                        if(str1.length()==10)
                        {
                            str1 = str1.substring(0, 1) +"."+str1.substring(1, 3)+ " Billion";
                        }
                        else if(str1.length()==11)
                        {
                            str1 = str1.substring(0, 2) +"."+str1.substring(2, 3)+ " Billion";
                        }
                        else if(str1.length()==12)
                        {
                            str1 = str1.substring(0, 3) + " Billion";
                        }
                    }
                    txt_revenue.setText("$"+str1);

                }
                else
                {
                    dialog.dismiss();
                    Toast.makeText(MovieDetailsActivity.this, "No details found", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t)
            {
                Log.e("TAG", t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
