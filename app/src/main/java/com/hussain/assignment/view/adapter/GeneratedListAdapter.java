package com.hussain.assignment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hussain.assignment.R;
import com.hussain.assignment.base.BaseViewHolder;

import java.util.List;

public class GeneratedListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<String> mMovies;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    public GeneratedListAdapter(Context context, List<String> movies) {
        mContext = context;
        mMovies = movies;
    }

    public void setList(List<String> moviesList) {
        mMovies = moviesList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            return new MovieViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_movie_list_home, parent,
                            false));
        } else {

            return new LoadingViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent,
                            false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        viewHolder.onBind(position);

//        if (viewHolder instanceof MovieViewHolder) {
//
//
//        } else if (viewHolder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) viewHolder, position);
//        }


    }


    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     */
    @Override
    public int getItemViewType(int position) {
        return mMovies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class LoadingViewHolder extends BaseViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
           // progressBar = itemView.findViewById(R.id.progressBar);
        }

        @Override
        protected void clear() {

        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }


    class MovieViewHolder extends BaseViewHolder {

        CardView movieCard;
        ImageView moviePosterImageView;
        TextView movieTitleTextView;
        ImageButton movieFavImageButton;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieCard = itemView.findViewById(R.id.card_view_show_card);
            moviePosterImageView = itemView.findViewById(R.id.image_view_show_card);
            moviePosterImageView.getLayoutParams().width =350;

        }

        public void onBind(int position) {
            super.onBind(position);

            Glide.with(mContext)
                    .load(mMovies.get(position))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.movie_place_holder)
                    .into(moviePosterImageView);

        }

        @Override
        protected void clear() {

        }
    }
}