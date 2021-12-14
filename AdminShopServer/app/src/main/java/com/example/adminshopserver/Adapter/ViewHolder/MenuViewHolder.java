package com.example.adminshopserver.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public ImageView img_product;
    public TextView txt_product;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        img_product=itemView.findViewById(R.id.img_product);
        txt_product=itemView.findViewById(R.id.txt_menu_name);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,true);
        return true;
    }
}
