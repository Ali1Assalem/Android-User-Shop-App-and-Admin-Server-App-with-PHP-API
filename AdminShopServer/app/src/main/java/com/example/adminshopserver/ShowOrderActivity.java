package com.example.adminshopserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adminshopserver.Adapter.OrderViewAdapter;
import com.example.adminshopserver.Model.Order;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Utils.Common;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowOrderActivity extends AppCompatActivity {
    RecyclerView recycler_orders;

    IDrinkShopApi mService;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Order");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

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


    private void loadOrder(String code) {
        compositeDisposable.add(mService.getAllOrder(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> orders) throws Exception {
                        displayOrder(orders);
                    }
                }));
    }
    private void displayOrder(List<Order> orders) {
        Collections.reverse(orders);
        OrderViewAdapter orderAdapter=new OrderViewAdapter(this,orders);
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
        bottomNavigationView.setSelectedItemId(R.id.order_new);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

}