package com.example.adminshopserver.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminshopserver.Adapter.MenuAdapter;
import com.example.adminshopserver.Model.Category;
import com.example.adminshopserver.R;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Utils.Common;
import com.example.adminshopserver.databinding.FragmentHomeBinding;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    RecyclerView recycler_menu;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    IDrinkShopApi mService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle("Categories Page");
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recycler_menu=root.findViewById(R.id.recycler_menu);
        recycler_menu.setLayoutManager(new GridLayoutManager(getContext(),2));
        recycler_menu.setHasFixedSize(true);

        mService= Common.getApi();

        getMenu();
        return root;
    }

    public void getMenu() {
        compositeDisposable.add(mService.getMenu()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        displayMenuList(categories);
                    }
                }));
    }

    public void displayMenuList(List<Category> categories) {
        Common.menuList=categories;
        MenuAdapter adapter=new MenuAdapter(getContext(),categories);
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMenu();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}