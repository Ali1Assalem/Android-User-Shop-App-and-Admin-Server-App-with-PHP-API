package com.example.usershopapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usershopapp.Interface.IItemClickListener;
import com.example.usershopapp.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imgProduct;
    TextView txt_drink_name,txt_price;
    IItemClickListener itemClickListener;
    ImageView btn_add_to_cart,btn_favorite;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imgProduct=itemView.findViewById(R.id.img_product);
        txt_drink_name=itemView.findViewById(R.id.txt_drink_name);
        txt_price=itemView.findViewById(R.id.txt_price);
        btn_add_to_cart=itemView.findViewById(R.id.btn_add_cart);
        btn_favorite=itemView.findViewById(R.id.btn_favorite);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);
    }
}
