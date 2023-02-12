package com.app.repairo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.repairo.app.R;
import com.app.repairo.app.activity.dashboard.ProductListActivity;
import com.app.repairo.app.activity.dashboard.ServiceListActivity;
import com.app.repairo.app.model.homenewmodel.CategoryList;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder>   {
    private Context context;
    private ArrayList<CategoryList> data;

    public ServiceAdapter(Context context, ArrayList<CategoryList> data) {
        this.context = context;
        this.data = data;
    }

    public void updateAdapter( ArrayList<CategoryList> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName,txtDescription;
        private ImageView imgCategory;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }


    @Override
    public ServiceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_items, parent, false);

        return new ServiceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ServiceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
        try{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProductListActivity.class).putExtra("header",data.get(listPosition).getCategory_name()).putExtra("categoryId",data.get(listPosition).getId()));
                }
            });
            holder.txtName.setText(data.get(listPosition).getCategory_name());
            holder.txtDescription.setText(data.get(listPosition).getCategory_description());
            Glide.with(context)
                    .load(data.get(listPosition).getCategory_image()).into(holder.imgCategory);
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
