package com.example.adminshopserver.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adminshopserver.Adapter.ViewHolder.MenuViewHolder;
import com.example.adminshopserver.DrinkListActivity;
import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.Model.Category;
import com.example.adminshopserver.R;

import com.example.adminshopserver.UpdateCategoryActivity;
import com.example.adminshopserver.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    Context context;
    List<Category> categoryList;

    public MenuAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.menu_item_layout,parent,false);

        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get()
                .load(categoryList.get(position).Link)
                .into(holder.img_product);

        holder.txt_product.setText(categoryList.get(position).Name);



        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, boolean isLongClick) {
                if (isLongClick){
                    Common.currentCategory=categoryList.get(position);

                    context.startActivity(new Intent(context, UpdateCategoryActivity.class));
                }
                else {
                    Common.currentCategory=categoryList.get(position);

                    context.startActivity(new Intent(context, DrinkListActivity.class));
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
