package com.app.repairo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.repairo.app.R;
import com.app.repairo.app.activity.dashboard.AddReviewActivity;
import com.app.repairo.app.activity.dashboard.BookingDetailsActivity;
import com.app.repairo.app.activity.dashboard.ProductDetailsActivity;
import com.app.repairo.app.activity.dashboard.ServiceListActivity;
import com.app.repairo.app.model.bookings.BookingList;
import com.app.repairo.app.model.products.ProductsList;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.MyViewHolder>   {
    private Context context;
    private ArrayList<BookingList> data;

    public BookingsAdapter(Context context, ArrayList<BookingList> data) {
        this.context = context;
        this.data = data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtServiceName,txtServiceType,txtStatus, txtRatingCount,txtAddReview;
        private ImageView imgService,imgRatingCount;
        private RelativeLayout cardStatus,reviewStatus;
        private LinearLayout layoutData;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgService = itemView.findViewById(R.id.imgService);
            imgRatingCount = itemView.findViewById(R.id.imgRatingCount);
            layoutData = itemView.findViewById(R.id.layoutData);
            txtServiceName = itemView.findViewById(R.id.txtServiceName);
            txtServiceType = itemView.findViewById(R.id.txtServiceType);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtRatingCount = itemView.findViewById(R.id.txtRatingCount);
            cardStatus = itemView.findViewById(R.id.cardStatus);
            reviewStatus = itemView.findViewById(R.id.reviewStatus);
            txtAddReview = itemView.findViewById(R.id.txtAddReview);

        }
    }


    @Override
    public BookingsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookings_items, parent, false);

        return new BookingsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookingsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
        try{
            holder.txtAddReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity( new Intent(context, AddReviewActivity.class)
                          .putExtra("dealer_id", data.get(listPosition).getDealer_id())
                          .putExtra("business_name", data.get(listPosition).getBusiness_name())
                          .putExtra("category_name", data.get(listPosition).getCategory_name())
                  );
                }
            });
            holder.layoutData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, BookingDetailsActivity.class)
                            .putExtra("dealer_id",data.get(listPosition).getDealer_id())
                            .putExtra("rating_count",data.get(listPosition).getRating_total())
                            .putExtra("total_rating_count",String.valueOf(data.get(listPosition).getReviews_list().size()))
                            .putExtra("category_name",data.get(listPosition).getCategory_name())
                            .putExtra("status",data.get(listPosition).getBooking_status())
                            .putExtra("bookingId",data.get(listPosition).getId())
                            .putExtra("date",data.get(listPosition).getCreated_at()));
                }
            });
            holder.txtServiceName.setText(data.get(listPosition).getCategory_name());
            if(!data.get(listPosition).getRating_total().equals("0")){
                 holder.txtRatingCount.setText(data.get(listPosition).getRating_total()+" ("+String.valueOf(data.get(listPosition).getReviews_list().size())+")");
                holder.txtRatingCount.setVisibility(View.VISIBLE);
                holder.imgRatingCount.setVisibility(View.VISIBLE);
             }else {
                holder.txtRatingCount.setVisibility(View.GONE);
                holder.imgRatingCount.setVisibility(View.GONE);
            }
            holder.txtServiceType.setText(data.get(listPosition).getBusiness_name());
            if(data.get(listPosition).getBooking_status().equals("Pending")){
                holder.txtStatus.setText("BOOKING PENDING");
                holder.cardStatus.setBackgroundDrawable(context.getDrawable(R.drawable.round_button_pending));
                holder.reviewStatus.setVisibility(View.GONE);
            }else  if(data.get(listPosition).getBooking_status().equals("Completed")){
                holder.txtStatus.setText("BOOKING COMPLETED");
                holder.cardStatus.setBackgroundDrawable(context.getDrawable(R.drawable.round_button_complete));
                holder.reviewStatus.setVisibility(View.VISIBLE);
            }else  if(data.get(listPosition).getBooking_status().equals("Cancelled")){
                holder.txtStatus.setText("BOOKING CANCELLED");
                holder.cardStatus.setBackgroundDrawable(context.getDrawable(R.drawable.round_button_cancel));
                holder.reviewStatus.setVisibility(View.VISIBLE);

            }else  if(data.get(listPosition).getBooking_status().equals("Confirmed")){
                holder.txtStatus.setText("BOOKING CONFIRMED");
                holder.cardStatus.setBackgroundDrawable(context.getDrawable(R.drawable.round_button_complete));
                holder.reviewStatus.setVisibility(View.VISIBLE);
            }
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
