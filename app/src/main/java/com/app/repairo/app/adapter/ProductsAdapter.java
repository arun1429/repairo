package com.app.repairo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.app.repairo.app.R;
import com.app.repairo.app.activity.dashboard.ProductDetailsActivity;
import com.app.repairo.app.activity.logreg.LoginActivity;
import com.app.repairo.app.activity.logreg.VerifyOtpActivity;
import com.app.repairo.app.custom.OkDialog;
import com.app.repairo.app.model.products.ProductsList;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>   {
    private Context context;
    private ArrayList<ProductsList> data;
    private String  headerTxt ="";

    public ProductsAdapter(Context context, ArrayList<ProductsList> data,String headerTxt) {
        this.context = context;
        this.data = data;
        this.headerTxt = headerTxt;
    }
    public void updateAdapter(ArrayList<ProductsList> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtServiceName, txtRatingCount, txtPrice,txtOffer;
        private ImageView imgService;
        private RelativeLayout viewDetails;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgService = itemView.findViewById(R.id.imgService);
            txtServiceName = itemView.findViewById(R.id.txtServiceName);
            txtRatingCount = itemView.findViewById(R.id.txtRatingCount);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtOffer = itemView.findViewById(R.id.txtOffer);
            viewDetails = itemView.findViewById(R.id.viewDetails);

        }
    }


    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_items, parent, false);

        return new ProductsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
        try{
            holder.viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).putExtra("header",data.get(listPosition).getBusiness_name()).putExtra("headerTxt",headerTxt).putExtra("dealer_id",data.get(listPosition).getId()));
                }
            });
            holder.txtServiceName.setText(data.get(listPosition).getBusiness_name());
            holder.txtRatingCount.setText(data.get(listPosition).getRating_total()+" ("+String.valueOf(data.get(listPosition).getReviews_list().size())+")");
            holder.txtPrice.setText(data.get(listPosition).getService_charge()+"");

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
