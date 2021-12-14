package com.example.usershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.usershopapp.Adapter.OrderAdapter;
import com.example.usershopapp.Model.Order;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {


    IStoreApi mService;
    RecyclerView recycler_orders;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mService= Common.getApi();

        recycler_orders=findViewById(R.id.recycler_orders);
        recycler_orders.setLayoutManager(new LinearLayoutManager(this));
        recycler_orders.setHasFixedSize(true);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.order_new)
                {
                    loadOrder("0");
                }
                else if (item.getItemId()==R.id.order_cancel)
                {
                    loadOrder("-1");
                }
                else if (item.getItemId()==R.id.order_processing)
                {
                    loadOrder("1");
                }
                else if (item.getItemId()==R.id.order_shipping)
                {
                    loadOrder("2");
                }
                else if (item.getItemId()==R.id.order_shipped)
                {
                    loadOrder("3");
                }

                return true;
            }
        });

        loadOrder("0");
    }


    private void loadOrder(String statusCode) {
        if (Common.currentUser != null) {
            compositeDisposable.add(mService.getOrder(Common.currentUser.getEmail(), statusCode)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<List<Order>>() {
                        @Override
                        public void accept(List<Order> orders) throws Exception {
                            displayOrder(orders);
                        }
                    }));
        }
        else {
            Toast.makeText(this, "Please logging again !", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void displayOrder(List<Order> orders) {
        Collections.reverse(orders);
        OrderAdapter orderAdapter=new OrderAdapter(this,orders);
        recycler_orders.setAdapter(orderAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrder("0");
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(OrderActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}