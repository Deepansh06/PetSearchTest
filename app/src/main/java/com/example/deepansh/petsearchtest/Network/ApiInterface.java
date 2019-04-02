package com.example.deepansh.petsearchtest.Network;

import com.example.deepansh.petsearchtest.Model.Movie;
import com.example.deepansh.petsearchtest.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("discover/movie")
    Call<MovieResponse> getPopularMovies(@Query("sort_by") String sort_by, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int id,
                                        @Query("api_key") String apiKEy,
                                        @Query("language") String language);
}