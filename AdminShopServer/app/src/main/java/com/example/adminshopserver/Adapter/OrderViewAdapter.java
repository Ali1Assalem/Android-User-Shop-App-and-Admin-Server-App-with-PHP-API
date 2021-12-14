package com.example.adminshopserver.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Adapter.ViewHolder.OrdrViewHolder;
import com.example.adminshopserver.Interface.IItemClickListener;
import com.example.adminshopserver.Model.Order;
import com.example.adminshopserver.R;
import com.example.adminshopserver.Utils.Common;
import com.example.adminshopserver.ViewOrderDetail;


import java.util.List;

public class OrderViewAdapter extends RecyclerView.Adapter<OrdrViewHolder> {

    Context context;
    List<Order> orderList;


    public OrderViewAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrdrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_layout, parent, false);
        return new OrdrViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull OrdrViewHolder holder, int position) {
        holder.txt_order_id.setText(new StringBuilder("#").append(orderList.get(position).getOrderId()));
        holder.txt_order_price.setText(new StringBuilder("$ ").append(orderList.get(position).getOrderPrice()));
        holder.txt_order_address.setText(new StringBuilder("Location: ").append(orderList.get(position).getOrderAddress()));
        holder.txt_order_comment.setText(new StringBuilder("Comment: ").append(orderList.get(position).getOrderComment()));
        holder.txt_order_status.setText(new StringBuilder("Order Status: ").append(Common.convertCodeToStatus(orderList.get(position).getOrderStatus())));
        holder.txt_userEmail.setText(new StringBuilder("User Email: ").append(orderList.get(position).getUserEmail()));


        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, boolean isLongClick) {

                Common.currentOrder = orderList.get(position);
                context.startActivity(new Intent(context, ViewOrderDetail.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();

    }



}