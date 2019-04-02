package com.example.deepansh.petsearchtest.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deepansh.petsearchtest.Model.Movie;
import com.example.deepansh.petsearchtest.View.Activity.MovieDetailsActivity;
import com.example.deepansh.petsearchtest.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>
{

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.language.setText(movies.get(position).getOriginalLanguage());
        Glide.with(context).load(IMAGE_BASE_URL + movies.get(position).getPosterPath()).into(holder.img_poster);

        holder.img_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=movies.get(position).getId();
                Intent i = new Intent(context, MovieDetailsActivity.class);
                i.putExtra("movie_id",id);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        TextView language;
        ImageView img_poster;


        public MovieViewHolder(View v) {
            super(v);
            img_poster = (ImageView) v.findViewById(R.id.item_movie_poster);
            movieTitle = (TextView) v.findViewById(R.id.txt_title);
            data = (TextView) v.findViewById(R.id.txt_date);
            movieDescription = (TextView) v.findViewById(R.id.txt_description);
            rating = (TextView) v.findViewById(R.id.txt_rating);
            language = (TextView) v.findViewById(R.id.txt_lang);
        }
    }
}
