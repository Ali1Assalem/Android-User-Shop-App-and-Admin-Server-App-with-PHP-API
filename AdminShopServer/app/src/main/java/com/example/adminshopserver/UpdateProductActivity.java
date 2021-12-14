package com.example.adminshopserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminshopserver.Model.Category;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Utils.Common;
import com.example.adminshopserver.Utils.ProgressRequestBody;
import com.example.adminshopserver.Utils.UploadCallBack;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import edmt.dev.afilechooser.utils.FileUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductActivity extends AppCompatActivity implements UploadCallBack {

    MaterialSpinner spinner_menu;
    HashMap<String,String> menu_data_for_get_key=new HashMap<>();
    HashMap<String,String> menu_data_for_get_value=new HashMap<>();

    List<String> menu_data=new ArrayList<>();


    private static final int PICK_FILE_REQUEST = 1111;
    ImageView img_browser;
    EditText edt_name,edt_price;
    Button btn_update,btn_delete;

    IDrinkShopApi mService;

    CompositeDisposable compositeDisposable;

    Uri selectedUri=null;
    String uploaded_img_path="",selected_category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        btn_delete=findViewById(R.id.btn_delete);
        btn_update=findViewById(R.id.btn_update);
        edt_name=findViewById(R.id.edt_drink_name);
        edt_price=findViewById(R.id.edt_drink_price);
        img_browser=findViewById(R.id.img_browser);

        spinner_menu=findViewById(R.id.spinner_menu_id);

        mService= Common.getApi();

        compositeDisposable=new CompositeDisposable();

        if (Common.currentProduct!=null)
        {
            uploaded_img_path=Common.currentProduct.Link;
            selected_category=Common.currentProduct.MenuId;
        }

        img_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Intent.createChooser(FileUtils.createGetContentIntent(),"Select a File"),
                        PICK_FILE_REQUEST);
            }
        });


        spinner_menu.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selected_category=menu_data_for_get_key.get(menu_data.get(position));
            }
        });



        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategory();
            }
        });



        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory();
            }
        });



        setSpinnerMenu();
        setProductInfo();
    }

    private void setProductInfo() {
        if (Common.currentProduct != null) {
            edt_name.setText(Common.currentProduct.Name);
            edt_price.setText(Common.currentProduct.Price);

            Picasso.get().load(Common.currentProduct.Link).into(img_browser);

            spinner_menu.setSelectedIndex(menu_data.indexOf(menu_data_for_get_value.get(Common.currentCategory.getID())));
            // هون استخدمت category id  ك key لوصلل لل value الي هيي اسم البروداكت       }
        }
    }



    private void setSpinnerMenu() {
        for (Category category : Common.menuList)
        {
            menu_data_for_get_key.put(category.getName(),category.getID());
            menu_data_for_get_value.put(category.getID(),category.getName());

            menu_data.add(category.getName());
        }
        spinner_menu.setItems(menu_data);
        Toast.makeText(this, menu_data_for_get_value.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Common.currentCategory.getID().toString(), Toast.LENGTH_SHORT).show();
    }




    private void deleteCategory() {
        compositeDisposable.add(mService.deleteProduct(Common.currentProduct.ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(UpdateProductActivity.this, s, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(UpdateProductActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }));
    }

    private void updateCategory() {
        compositeDisposable.add(mService.updateProduct(Common.currentProduct.ID,
                edt_name.getText().toString(),
                uploaded_img_path,
                edt_price.getText().toString(),
                selected_category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(UpdateProductActivity.this, s, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(UpdateProductActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK)
        {
            if (requestCode==PICK_FILE_REQUEST)
            {
                if (data!=null)
                {
                    selectedUri=data.getData();
                    if (selectedUri!=null && !selectedUri.getPath().isEmpty())
                    {
                        img_browser.setImageURI(selectedUri);
                        uploadFileToServer();
                    }
                    else
                        Toast.makeText(this, "Cannot upload file to server !", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void uploadFileToServer() {
        if (selectedUri!=null)
        {
            File file=FileUtils.getFile(this,selectedUri);

            String fileName=new StringBuilder(UUID.randomUUID().toString())
                    .append(FileUtils.getExtension(file.toString())).toString();

            ProgressRequestBody requestFile= new ProgressRequestBody(file,this);

            MultipartBody.Part body= MultipartBody.Part.createFormData("uploaded_file",fileName,requestFile);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mService.uploadProductFile(body)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    //after uploaded will get file name and return string contains link of images
                                    uploaded_img_path=new StringBuilder(Common.BASE_URL)
                                            .append("server/product/product_img/")
                                            .append(response.body().toString())
                                            .toString();

                                    Toast.makeText(UpdateProductActivity.this, uploaded_img_path, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(UpdateProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }).start();
        }
    }




    @Override
    public void onProgressUpdate(int pertantage) {

    }
}