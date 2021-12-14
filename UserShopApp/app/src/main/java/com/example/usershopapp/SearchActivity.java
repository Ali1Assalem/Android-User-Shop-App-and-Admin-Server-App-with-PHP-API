package com.example.usershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.usershopapp.Adapter.ProductAdapter;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    List<String> suggestList=new ArrayList<>();
    List<Product> localDataSourcde =new ArrayList<>();
    MaterialSearchBar searchBar;

    IStoreApi mservice;

    RecyclerView recycler_search;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    BadgeDrawable badgeDrawable;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout rootLayout;

    ProductAdapter searchAdapter,adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        bottomNavigationView = findViewById(R.id.bottom_mavigator);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        return true;
                }
                return false;
            }
        });


        rootLayout=findViewById(R.id.rootLayout);

        badgeDrawable= bottomNavigationView.getOrCreateBadge(R.id.cart);
        badgeDrawable.clearNumber();
        badgeDrawable.setVisible(false);



        mservice= Common.getApi();

        recycler_search=findViewById(R.id.recycler_search);
        recycler_search.setLayoutManager(new GridLayoutManager(this,2));

        searchBar=findViewById(R.id.searchBar);
        searchBar.setHint("Enter your Product");

        loadAllDrinks();

        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest=new ArrayList<>();
                for (String search:suggestList)
                {
                    if (search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recycler_search.setAdapter(adapter);//restore full list of drinks
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }


    private void startSearch(CharSequence text) {
        List<Product> result=new ArrayList<>();
        for (Product drink:localDataSourcde)
            if (drink.Name.contains(text))
                result.add(drink);
        searchAdapter=new ProductAdapter(this,result);
        recycler_search.setAdapter(searchAdapter);
    }

    private void loadAllDrinks() {
        compositeDisposable.add(mservice.getAllDrinks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> drinks) throws Exception {
                        displayListDrink(drinks);
                        buildSuggestList(drinks);
                    }
                }));
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void buildSuggestList(List<Product> drinks) {
        for (Product drink:drinks)
            suggestList.add(drink.Name);
        searchBar.setLastSuggestions(suggestList);
    }

    private void displayListDrink(List<Product> drinks) {
        localDataSourcde=drinks;
        adapter=new ProductAdapter(this,drinks);
        recycler_search.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(SearchActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}