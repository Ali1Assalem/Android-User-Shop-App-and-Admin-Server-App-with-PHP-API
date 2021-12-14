package com.example.adminshopserver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Model.Cart;
import com.example.adminshopserver.R;
import com.example.adminshopserver.Utils.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrederDetailViewHolder> {

    Context context;
    List<Cart> cartList;

    public OrderDetailAdapter(Context context) {
        this.context = context;
        this.cartList = new Gson().fromJson(Common.currentOrder.getOrderDetail(),new TypeToken<List<Cart>>(){}.getType());
    }

    @NonNull
    @Override
    public OrederDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_layout,parent,false);

        return new OrederDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrederDetailViewHolder holder, int position) {
        holder.txt_drink_amount.setText(""+cartList.get(position).getAmount());
        holder.txt_drink_name.setText(new StringBuilder(cartList.get(position).getName()));
        holder.txt_size.setText(cartList.get(position).getSize()==0?"Size M":"Size L");

        if (cartList.get(position).getWith()!=null &&
                !cartList.get(position).getWith().isEmpty()) {
            String topping_format = cartList.get(position).getWith().replaceAll("\\n", ",");
            topping_format = topping_format.substring(0, topping_format.length() - 1);

            holder.txt_topping.setText(topping_format);
        }

        else {
            holder.txt_topping.setText("None");
        }
        Picasso.get().load(cartList.get(position).getLink()).into(holder.img_order_item);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class OrederDetailViewHolder extends RecyclerView.ViewHolder{

        ImageView img_order_item;
        TextView txt_drink_name,txt_drink_amount,txt_size,txt_topping;

        public OrederDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            img_order_item=itemView.findViewById(R.id.img_order_item);
            txt_drink_name=itemView.findViewById(R.id.txt_drink_name);
            txt_drink_amount=itemView.findViewById(R.id.txt_drink_amount);
            txt_size=itemView.findViewById(R.id.txt_size);
            txt_topping=itemView.findViewById(R.id.txt_topping);
        }
    }
}
