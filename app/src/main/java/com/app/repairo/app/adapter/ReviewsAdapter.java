package com.app.repairo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.repairo.app.R;
import com.app.repairo.app.activity.dashboard.BookNowActivity;
import com.app.repairo.app.model.products.ProductsList;
import com.app.repairo.app.model.ratingsreview.ReviewData;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder>   {
    private Context context;
    private ArrayList<ProductsList.ReviewList> data;

    public ReviewsAdapter(Context context, ArrayList<ProductsList.ReviewList> data) {
        this.context = context;
        this.data = data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtDate, txtRating,txtReview,txtTitle;
        private ImageView imgUser;
        private RatingBar txtRatingCount;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            txtName = itemView.findViewById(R.id.txtName);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtRatingCount = itemView.findViewById(R.id.txtRatingCount);
            txtReview = itemView.findViewById(R.id.txtReview);

        }
    }


    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_items, parent, false);

        return new ReviewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReviewsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
        try{
            holder.txtName.setText(data.get(listPosition).getName());
            holder.txtTitle.setText(data.get(listPosition).getTitle());
            holder.txtRating.setText(data.get(listPosition).getRating()+"");
            holder.txtRatingCount.setRating(Float.valueOf(data.get(listPosition).getRating()));
            holder.txtDate.setText(data.get(listPosition).getCreated_at());
            holder.txtReview.setText(data.get(listPosition).getReview()+"");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
