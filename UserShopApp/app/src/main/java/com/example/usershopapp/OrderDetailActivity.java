package com.example.usershopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usershopapp.Adapter.OrderDetailAdapter;
import com.example.usershopapp.Database.ModelDB.Cart;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    TextView txt_order_id,txt_order_price,txt_order_address,txt_order_comment,txt_order_status;
    RecyclerView recycler_order_detail;
    Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Order Details");

        txt_order_address=findViewById(R.id.txt_order_address);
        txt_order_id=findViewById(R.id.txt_order_id);
        txt_order_price=findViewById(R.id.txt_order_price);
        txt_order_comment=findViewById(R.id.txt_order_comment);
        txt_order_status=findViewById(R.id.txt_order_status);

        btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrder();
            }
        });

        recycler_order_detail=findViewById(R.id.recycler_order_detail);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(this));
        recycler_order_detail.setHasFixedSize(true);


        txt_order_id.setText(new StringBuilder("#").append(Common.currentOrder.getOrderId()));
        txt_order_price.setText(new StringBuilder("$").append(Common.currentOrder.getOrderPrice()));
        txt_order_address.setText(new StringBuilder("address : ").append(Common.currentOrder.getOrderAddress()));
        txt_order_comment.setText(new StringBuilder("comment : ").append(Common.currentOrder.getOrderComment()));
        txt_order_status.setText(new StringBuilder("Order Status : ").append(Common.convertCodeToStatus(Common.currentOrder.getOrderStatus())));


        displayOrderDetail();
    }


    private void displayOrderDetail() {
        List<Cart> orderDetail=new Gson().fromJson(Common.currentOrder.getOrderDetail(),
                new TypeToken<List<Cart>>(){}.getType());

        recycler_order_detail.setAdapter(new OrderDetailAdapter(this,orderDetail));
    }


    private void cancelOrder() {
        IStoreApi drinkShopAPI=Common.getApi();
        drinkShopAPI.cancelOrder(String.valueOf(Common.currentOrder.getOrderId()),
                Common.currentUser.getEmail())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(OrderDetailActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                        if (response.body().contains("Order has been cancelled"));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(OrderDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}