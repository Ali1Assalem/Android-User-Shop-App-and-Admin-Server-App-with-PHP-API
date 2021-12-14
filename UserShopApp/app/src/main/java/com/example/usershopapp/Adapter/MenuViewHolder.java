package com.example.usershopapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usershopapp.Interface.IItemClickListener;
import com.example.usershopapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView img_company;
    TextView name_company;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        img_company=itemView.findViewById(R.id.companyPic);
        name_company=itemView.findViewById(R.id.companyName);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view);
    }
}
