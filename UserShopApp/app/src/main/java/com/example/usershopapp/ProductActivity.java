package com.example.usershopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usershopapp.Adapter.ProductAdapter;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity {
    TextView txt_banner_name;
    SwipeRefreshLayout swipeRefreshLayout;

    IStoreApi mservice;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView lst_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Common.currentMenu.Name);

        mservice = Common.getApi();
        lst_product = findViewById(R.id.recycler_drinks);
        lst_product.setLayoutManager(new GridLayoutManager(this, 2));
        lst_product.setHasFixedSize(true);


        swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);


        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                LoadListDrink(Common.currentMenu.ID);
                getTooping(Common.Topping_Munu_ID);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                LoadListDrink(Common.currentMenu.ID);
                getTooping(Common.Topping_Munu_ID);
            }
        });
    }
        private void getTooping(String menuId) {
            compositeDisposable.add(mservice.getProductByMenuID(menuId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Product>>() {
                        @Override
                        public void accept(List<Product> products) throws Exception {
                            Common.toppingList=products;
                        }
                    }));
        }

        private void LoadListDrink(String menuId) {
            compositeDisposable.add(mservice.getProductByMenuID(menuId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Product>>() {
                        @Override
                        public void accept(List<Product> products) throws Exception {
                            displayDrinkList(products);
                        }
                    }));
        }

        private void displayDrinkList(List<Product> productList) {
            ProductAdapter productAdapter=new ProductAdapter(this,productList);
            lst_product.setAdapter(productAdapter);

            swipeRefreshLayout.setRefreshing(false);
        }

}