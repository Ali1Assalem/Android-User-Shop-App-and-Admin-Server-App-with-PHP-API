package com.example.adminshopserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminshopserver.Adapter.OrderDetailAdapter;
import com.example.adminshopserver.Model.DataMessage;
import com.example.adminshopserver.Model.MyResponse;
import com.example.adminshopserver.Model.Order;
import com.example.adminshopserver.Model.Token;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Retrofit.IFCMServices;
import com.example.adminshopserver.Utils.Common;

import java.util.HashMap;
import java.util.Map;




import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrderDetail extends AppCompatActivity {

    TextView txt_order_id,txt_order_price,txt_order_comment,txt_order_address,txt_order_email;
    Spinner spinner_order_status;
    RecyclerView recycler_order_detail;


    String[] spinner_source=new String[]{
            "Cancelled",
            "Placed",
            "Processed",
            "Shipping",
            "Shipped"
    };

    IFCMServices mFCMService;
    IDrinkShopApi mService;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Order Detail");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

        mService= Common.getApi();
        mFCMService=Common.getFCMAPI();

        txt_order_id=findViewById(R.id.txt_order_id);
        txt_order_price=findViewById(R.id.txt_order_price);
        txt_order_comment=findViewById(R.id.txt_order_comment);
        txt_order_address=findViewById(R.id.txt_order_address);
        txt_order_email=findViewById(R.id.txt_order_email);

        spinner_order_status=findViewById(R.id.spinner_order_atatus);
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,spinner_source);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_order_status.setAdapter(stringArrayAdapter);


        recycler_order_detail=findViewById(R.id.recycler_order_detail);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(this));
        recycler_order_detail.setAdapter(new OrderDetailAdapter(this));

        txt_order_id.setText(new StringBuilder("#")
                .append(Common.currentOrder.getOrderId()));

        txt_order_price.setText(new StringBuilder("$")
                .append(Common.currentOrder.getOrderPrice()));
        txt_order_address.setText(Common.currentOrder.getOrderAddress());
        txt_order_comment.setText(Common.currentOrder.getOrderComment());
        txt_order_email.setText(Common.currentOrder.getUserEmail());

        setSpinnerSelectedBaseOnOrderStatus();
    }

    private void setSpinnerSelectedBaseOnOrderStatus() {
        switch (Common.currentOrder.getOrderStatus()){
            case -1:
                spinner_order_status.setSelection(0);//cancelled
                break;
            case 0:
                spinner_order_status.setSelection(1);//placed
                break;
            case 1:
                spinner_order_status.setSelection(2);//processed
                break;
            case 2:
                spinner_order_status.setSelection(3);//shipping
                break;
            case 3:
                spinner_order_status.setSelection(4);//shipped
                break;
        }
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ordre_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.action_save_order_detail)
            saveOrderDetail();
        return true;
    }

    private void saveOrderDetail() {
        int order_status=spinner_order_status.getSelectedItemPosition()-1;
        compositeDisposable.add(mService.updateOrderStatus(Common.currentOrder.getUserEmail(),
                Common.currentOrder.getOrderId(),
                order_status)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                           Toast.makeText(ViewOrderDetail.this, "Order updated!", Toast.LENGTH_SHORT).show();
                           finish();
                           sendOrderUpdateNotification(Common.currentOrder,order_status);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(ViewOrderDetail.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })
        );
    }



    private void sendOrderUpdateNotification(final Order currentOrder,int order_status) {


        //Get token of Owner Order
        mService.getToken(currentOrder.getUserEmail(),"0")
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        Token userToken=response.body();

                        DataMessage dataMessage=new DataMessage();


                        Map<String,String> dataSend=new HashMap<>();
                        dataSend.put("title","Your order has been update");
                        dataSend.put("message","Order #"+currentOrder.getOrderId()+"has been updated to "+Common.convertCodeToStatus(order_status));
                        dataMessage.to=userToken.getToken();
                        dataMessage.setData(dataSend);
                        mFCMService.sendNotification(dataMessage)
                                .enqueue(new Callback<MyResponse>() {
                                    @Override
                                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                        if (response.body().success==1)
                                        {
                                            Toast.makeText(ViewOrderDetail.this, "Order updated!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MyResponse> call, Throwable t) {
                                        Toast.makeText(ViewOrderDetail.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(ViewOrderDetail.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}