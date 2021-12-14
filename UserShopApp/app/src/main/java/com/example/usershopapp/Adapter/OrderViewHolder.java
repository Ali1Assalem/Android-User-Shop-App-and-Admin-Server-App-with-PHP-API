package com.example.usershopapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.usershopapp.Interface.IItemClickListener;
import com.example.usershopapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txt_order_id,txt_order_price,txt_order_address,txt_order_comment,txt_order_status,txt_order_date;
    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_order_address=itemView.findViewById(R.id.txt_order_address);
        txt_order_id=itemView.findViewById(R.id.txt_order_id);
        txt_order_price=itemView.findViewById(R.id.txt_order_price);
        txt_order_comment=itemView.findViewById(R.id.txt_order_comment);
        txt_order_status=itemView.findViewById(R.id.txt_order_status);
        txt_order_date=itemView.findViewById(R.id.txt_order_date);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
