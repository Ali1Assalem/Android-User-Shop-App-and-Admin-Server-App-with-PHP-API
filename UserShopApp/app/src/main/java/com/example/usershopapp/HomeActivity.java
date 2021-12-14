package com.example.usershopapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usershopapp.Adapter.MenuAdapter;
import com.example.usershopapp.Adapter.ProductAdapter;
import com.example.usershopapp.Database.DataStore.CartRepository;
import com.example.usershopapp.Database.DataStore.FavoriteRepository;
import com.example.usershopapp.Database.Local.AliRoomDatabase;
import com.example.usershopapp.Database.Local.CartDataSource;
import com.example.usershopapp.Database.Local.FavoriteDataSource;
import com.example.usershopapp.Interface.UploadCallBack;
import com.example.usershopapp.Model.Banner;
import com.example.usershopapp.Model.Menu;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.Model.User;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;
import com.example.usershopapp.Utils.ProgressRequestBody;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import edmt.dev.afilechooser.utils.FileUtils;
import edmt.dev.edmtslider.SliderLayout;
import edmt.dev.edmtslider.SliderTypes.BaseSliderView;
import edmt.dev.edmtslider.SliderTypes.TextSliderView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements UploadCallBack {

    private static final int PICK_FILE_REQUEST = 1222;
    private static final int REQUEST_PERMISSION = 1001;


    BottomNavigationView bottomNavigationView;

    TextView name,t1;
    IStoreApi mservice;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView lst_company,lstRandom;
    SliderLayout sliderLayout;
    CircleImageView circleImageView;

    BadgeDrawable badgeDrawable;

    Uri selectedFileUri;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
                break;
                default:
                    break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.whitelow, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.whitelow));
        }



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, REQUEST_PERMISSION);


        //BOTTOM NAVIGATION
        getSupportActionBar().hide();

        bottomNavigationView=findViewById(R.id.bottom_mavigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(),FavoriteActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
                        overridePendingTransition(0,0);
                        HomeActivity.this.finish();
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        badgeDrawable= bottomNavigationView.getOrCreateBadge(R.id.cart);
        badgeDrawable.clearNumber();
        badgeDrawable.setVisible(false);


        //BOTTOM NAVIGATION END


        mservice= Common.getApi();


        t1=findViewById(R.id.t1);

        lst_company=findViewById(R.id.view1);
        lst_company.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lst_company.setHasFixedSize(true);




        name=findViewById(R.id.name);
        sliderLayout=findViewById(R.id.slider);
        name.setText("HI "+Common.currentUser.getName().toString());



        circleImageView=findViewById(R.id.img_avatar);


        //set Avatar
        if(!TextUtils.isEmpty(Common.currentUser.getAvatarUrl() ))
        {
            Picasso.get()
                    .load(new StringBuilder(Common.BASE_URL)
                            .append("user_avatar/")
                            .append(Common.currentUser.getAvatarUrl()).toString())
                            .into(circleImageView);
        }


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });



        getTooping(Common.Topping_Munu_ID);

        getMenu();
        getBannerImage();
        initDB();
        updateCartCount();
     
    }
    private void chooseImage() {
        startActivityForResult(Intent.createChooser(FileUtils.createGetContentIntent(),"Select a file"),
                PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==PICK_FILE_REQUEST)
            {
                if(data!=null)
                {
                    selectedFileUri=data.getData();
                    if(selectedFileUri!=null && !selectedFileUri.getPath().isEmpty())
                    {
                        circleImageView.setImageURI(selectedFileUri);
                        uploadFile();
                    }
                    else
                    {
                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void uploadFile() {
        if(selectedFileUri!=null)
        {
            File file=FileUtils.getFile(this,selectedFileUri);

            String fileName=new StringBuilder(Common.currentUser.getEmail())
                    .append(FileUtils.getExtension(file.toString()))
                    .toString();

            ProgressRequestBody requestFile= new ProgressRequestBody(file,this);

            final MultipartBody.Part body=MultipartBody.Part.createFormData("uploaded_file",fileName,requestFile);
            final MultipartBody.Part userEmail=MultipartBody.Part.createFormData("email",Common.currentUser.getEmail());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mservice.uploadFile(userEmail,body)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Toast.makeText(HomeActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }).start();
        }
    }





    private void getMenu() {
        compositeDisposable.add(mservice.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Menu>>() {
                    @Override
                    public void accept(List<Menu> menus) throws Exception {
                        displayMenu(menus);
                    }
                }));
    }


    private void displayMenu(List<Menu> menus) {
        MenuAdapter menuAdapter =new MenuAdapter(HomeActivity.this,menus);
        lst_company.setAdapter(menuAdapter);
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


    private void getBannerImage() {
        compositeDisposable.add(mservice.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        displayImages(banners);
                    }
                }));
    }
    private void displayImages(List<Banner> banners){
        HashMap<String,String> bannerMap=new HashMap<>();
        for (Banner item:banners)
            bannerMap.put(item.getName(),item.getLink());

        for (String name:bannerMap.keySet()){
            TextSliderView textSliderView=new TextSliderView(HomeActivity.this);
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);
        }
    }




    private void initDB() {
        Common.aliRoomDatabase= AliRoomDatabase.getInstance(getApplicationContext());
        Common.cartRepository= CartRepository.getInstance(CartDataSource.getInstance(Common.aliRoomDatabase.cartDAO()));
        Common.favoriteRepository= FavoriteRepository.getInstance(FavoriteDataSource.getInstance(Common.aliRoomDatabase.favoriteDao()));

    }


    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartCount();
    }


    public void updateCartCount(){

                if(Common.cartRepository.countCartItems()==0){
                    badgeDrawable.clearNumber();
                    badgeDrawable.setVisible(false);
                }
                else{
                    badgeDrawable.setVisible(true);
                    badgeDrawable.setNumber(Common.cartRepository.countCartItems());
                }
            }


    @Override
    public void onProgressUpdate(int pertantage) {

    }
}