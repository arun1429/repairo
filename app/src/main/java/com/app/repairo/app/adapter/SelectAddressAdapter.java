package com.app.repairo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.repairo.app.R;
import com.app.repairo.app.model.address.AddressList;

import java.util.ArrayList;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.MyViewHolder>   {
    private Context context;
    private ArrayList<AddressList> data;
    EventListener eventListener;
    public SelectAddressAdapter(Context context, ArrayList<AddressList> data, EventListener eventListener1) {
        this.context = context;
        this.data = data;
        this.eventListener = eventListener1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName,txtAddress,txtCity,txtState,txtPinCode,txtLandmark;
        private ImageView imageCheck,imageDelete,imageEdit;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageCheck = itemView.findViewById(R.id.imageCheck);
            imageDelete = itemView.findViewById(R.id.imageDelete);
            imageEdit = itemView.findViewById(R.id.imageEdit);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtLandmark = itemView.findViewById(R.id.txtLandmark);
            txtCity = itemView.findViewById(R.id.txtCity);
            txtState = itemView.findViewById(R.id.txtState);
            txtPinCode = itemView.findViewById(R.id.txtPinCode);
        }
    }


    @Override
    public SelectAddressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_address_items, parent, false);

        return new SelectAddressAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectAddressAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
        try{
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.selectEventCalled(listPosition);
                }
            });
            holder.txtName.setText(data.get(listPosition).getName());
            holder.txtAddress.setText(data.get(listPosition).getAddress());
            holder.txtLandmark.setText(data.get(listPosition).getLandmark());
            holder.txtCity.setText(data.get(listPosition).getCity());
            holder.txtState.setText(data.get(listPosition).getState());
            holder.txtPinCode.setText(data.get(listPosition).getPincode());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface EventListener {
        void selectEventCalled(int pos);
    }
}
