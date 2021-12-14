package com.example.adminshopserver.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.R;


public class OrdrViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txt_order_id,txt_order_price,txt_order_address,txt_order_comment,txt_order_status,txt_userEmail;
    IItemClickListener itemClickListener;

    public OrdrViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_order_address=itemView.findViewById(R.id.txt_order_address);
        txt_order_id=itemView.findViewById(R.id.txt_order_id);
        txt_order_price=itemView.findViewById(R.id.txt_order_price);
        txt_order_comment=itemView.findViewById(R.id.txt_order_comment);
        txt_order_status=itemView.findViewById(R.id.txt_order_status);
        txt_userEmail=itemView.findViewById(R.id.txt_order_email);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,false);
    }
}
