package com.example.adminshopserver.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Adapter.ViewHolder.DrinkListViewHolder;
import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.Model.Drink;
import com.example.adminshopserver.R;

import com.example.adminshopserver.UpdateProductActivity;
import com.example.adminshopserver.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListViewHolder> {

   Context context;
   List<Drink> drinkList;

    public DrinkListAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.drink_item_layout,parent,false);
        return new DrinkListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get()
                .load(drinkList.get(position).Link)
                .into(holder.img_product);


        holder.txt_price.setText(new StringBuilder("$").append(drinkList.get(position).Price).toString());

        holder.txt_drink_name.setText(drinkList.get(position).Name);
        //Event anti crash null item click
        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, boolean isLongClick) {
                Common.currentProduct=drinkList.get(position);
                context.startActivity(new Intent(context, UpdateProductActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}
