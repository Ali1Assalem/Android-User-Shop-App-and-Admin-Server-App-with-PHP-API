package com.example.adminshopserver.Adapter.ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.R;

public class DrinkListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView img_product;
    public TextView txt_drink_name,txt_price;

    IItemClickListener iItemClickListener;

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    public DrinkListViewHolder(@NonNull View itemView) {
        super(itemView);

        img_product=itemView.findViewById(R.id.img_product);
        txt_drink_name=itemView.findViewById(R.id.txt_drink_name);
        txt_price=itemView.findViewById(R.id.txt_price);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        iItemClickListener.onClick(view,false);
    }
}
