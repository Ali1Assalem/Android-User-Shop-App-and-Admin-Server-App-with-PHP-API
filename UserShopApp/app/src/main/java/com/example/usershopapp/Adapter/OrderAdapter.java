package com.example.usershopapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usershopapp.Interface.IItemClickListener;
import com.example.usershopapp.Model.Order;
import com.example.usershopapp.OrderActivity;
import com.example.usershopapp.OrderDetailActivity;
import com.example.usershopapp.R;
import com.example.usershopapp.Utils.Common;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    Context context;
    List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView= LayoutInflater.from(context).inflate(R.layout.order_layout,parent,false);
        return new OrderViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_order_id.setText(new StringBuilder("#").append(orderList.get(position).getOrderId()));
        holder.txt_order_price.setText(new StringBuilder("$").append(orderList.get(position).getOrderPrice()));
        holder.txt_order_address.setText(new StringBuilder("location : ").append(orderList.get(position).getOrderAddress()));
        holder.txt_order_comment.setText(new StringBuilder("comment : ").append(orderList.get(position).getOrderComment()));
        holder.txt_order_status.setText(new StringBuilder("Order Status : ").append(Common.convertCodeToStatus(orderList.get(position).getOrderStatus())));
        holder.txt_order_date.setText(orderList.get(position).getOrderDate());

        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentOrder=orderList.get(position);
                context.startActivity(new Intent(context, OrderDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
