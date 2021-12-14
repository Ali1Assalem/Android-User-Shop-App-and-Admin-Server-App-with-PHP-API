package com.example.usershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.usershopapp.Adapter.CartAdapter;
import com.example.usershopapp.Database.ModelDB.Cart;
import com.example.usershopapp.Model.DataMessage;
import com.example.usershopapp.Model.MyResponse;
import com.example.usershopapp.Model.OrderResult;
import com.example.usershopapp.Model.Token;
import com.example.usershopapp.Retrofit.IFCMService;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Utils.Common;
import com.example.usershopapp.Utils.RecyclerItemTouchHelper;
import com.example.usershopapp.Utils.RecyclerItemTouchHelperListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {
    RecyclerView recycler_cart;
    Button btn_place_order;

    CompositeDisposable compositeDisposable;
    IStoreApi mservice;

    BadgeDrawable badgeDrawable;
    BottomNavigationView bottomNavigationView;
    List<Cart> cartList=new ArrayList<>();
    RelativeLayout rootLayout;

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_mavigator);
        bottomNavigationView.setSelectedItemId(R.id.cart);

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
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });


        rootLayout=findViewById(R.id.rootLayout);

        badgeDrawable= bottomNavigationView.getOrCreateBadge(R.id.cart);
        badgeDrawable.clearNumber();
        badgeDrawable.setVisible(false);


        compositeDisposable = new CompositeDisposable();
        mservice = Common.getApi();

        recycler_cart = findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        recycler_cart.setHasFixedSize(true);

        btn_place_order = findViewById(R.id.btn_place_order);
        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                placeOrder();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback=new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler_cart);

        loadCartItems();
        updateCartCount();

    }

    private void loadCartItems() {
        compositeDisposable.add(Common.cartRepository.getCartItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Cart>>() {
                    @Override
                    public void accept(List<Cart> carts) throws Exception {
                        displayCartItem(carts);
                    }
                }));
    }

    private void displayCartItem(List<Cart> carts) {
        cartList=carts;
        cartAdapter = new CartAdapter(CartActivity.this, carts);
        recycler_cart.setAdapter(cartAdapter);
    }

    private void updateCartCount(){
        CartActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(Common.cartRepository.countCartItems()==0){
                    badgeDrawable.clearNumber();
                    badgeDrawable.setVisible(false);
                }
                else{
                    badgeDrawable.setVisible(true);
                    badgeDrawable.setNumber(Common.cartRepository.countCartItems());
                }
            }
        });
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartAdapter.CartViewHolder)
        {
            String name=cartList.get(viewHolder.getAdapterPosition()).name;

            Cart deletedItem=cartList.get(viewHolder.getAdapterPosition());
            int deletedIndex =viewHolder.getAdapterPosition();

            //delete item from adapter
            cartAdapter.removeItem(deletedIndex);

            //delete from room database
            Common.cartRepository.deleteToCart(deletedItem);
            badgeDrawable.setVisible(true);
            badgeDrawable.setNumber(Common.cartRepository.countCartItems());

            Snackbar snackbar=Snackbar.make(rootLayout,new StringBuilder(name).append(" removed from Cart List").toString(),
                    Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartAdapter.restoreItemtem(deletedItem,deletedIndex);
                    Common.cartRepository.insertToCart(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }



    private void placeOrder() {

        if (Common.cartRepository.countCartItems()!=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Submit Order");

            View submit_order_layout = LayoutInflater.from(this).inflate(R.layout.submit_order_layout, null);
            EditText edt_comment = submit_order_layout.findViewById(R.id.edt_comment);
            EditText edt_other_address = submit_order_layout.findViewById(R.id.edt_other_address);

            RadioButton rdi_user_address = submit_order_layout.findViewById(R.id.rdi_user_address);
            RadioButton rdi_other_address = submit_order_layout.findViewById(R.id.rdi_other_address);

            RadioButton rdi_credit_card = submit_order_layout.findViewById(R.id.rdi_credit_card);
            RadioButton rdi_cod = submit_order_layout.findViewById(R.id.rdi_cod);

            rdi_user_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                        edt_other_address.setEnabled(false);
                }
            });

            rdi_other_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                        edt_other_address.setEnabled(true);
                }
            });

            builder.setView(submit_order_layout);
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (rdi_credit_card.isChecked()) {
                        final String orderComment = edt_comment.getText().toString();
                        final String orderAddress;
                        if (rdi_user_address.isChecked())
                            orderAddress = Common.currentUser.getAddress();
                        else if (rdi_other_address.isChecked())
                            orderAddress = edt_other_address.getText().toString();

                        else
                            orderAddress = "";

                        //submit order to server
                        compositeDisposable.add(
                                Common.cartRepository.getCartItem()
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Consumer<List<Cart>>() {
                                            @Override
                                            public void accept(List<Cart> carts) throws Exception {
                                                if (!TextUtils.isEmpty(orderAddress))
                                                    sendOrderToServer(Common.cartRepository.sumPrice(),
                                                            carts, orderComment, orderAddress, "COD");
                                                else
                                                    Toast.makeText(CartActivity.this, "Order Address Can't null", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                        );
                    } else if (rdi_cod.isChecked()) {
                        final String orderComment = edt_comment.getText().toString();
                        final String orderAddress;
                        if (rdi_user_address.isChecked())
                            orderAddress = Common.currentUser.getAddress();
                        else if (rdi_other_address.isChecked())
                            orderAddress = edt_other_address.getText().toString();

                        else
                            orderAddress = "";

                        //submit order to server
                        compositeDisposable.add(
                                Common.cartRepository.getCartItem()
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Consumer<List<Cart>>() {
                                            @Override
                                            public void accept(List<Cart> carts) throws Exception {
                                                if (!TextUtils.isEmpty(orderAddress))
                                                    sendOrderToServer(Common.cartRepository.sumPrice(),
                                                            carts, orderComment, orderAddress, "COD");
                                                else
                                                    Toast.makeText(CartActivity.this, "Order Address Can't null", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                        );
                    }
                }
            });
            builder.show();
        }
    }

    private void sendOrderToServer(float sumPrice, List<Cart> carts, String orderComment,String orderAddress,String paymentMethod) {
        if (carts.size()>0)
        {
            String orderDetail=new Gson().toJson(carts);

            mservice.insertNewOrder(sumPrice,orderDetail,orderComment,orderAddress,Common.currentUser.getEmail(),paymentMethod)
                    .enqueue(new Callback<OrderResult>() {
                        @Override
                        public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {

                            sendNotificationToServer(response.body());

                        }

                        @Override
                        public void onFailure(Call<OrderResult> call, Throwable t) {
                            Toast.makeText(CartActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }






    private void sendNotificationToServer(OrderResult orderResult) {

        mservice.getToken("server_app","1")
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        //when we have token ,just send notification to this token
                        Map<String,String> contentSend=new HashMap<>();
                        contentSend.put("title","New order #"+orderResult.getOrderId()+" From Email:  "+Common.currentUser.getEmail());
                        contentSend.put("message","You have new order "+orderResult.getOrderId());
                        DataMessage dataMessage=new DataMessage();
                        if (response.body().getToken()!=null)
                            dataMessage.setTo(response.body().getToken());
                        dataMessage.setData(contentSend);

                        IFCMService ifcmService=Common.getFCMService();
                        ifcmService.sendNotification(dataMessage)
                                .enqueue(new Callback<MyResponse>() {
                                    @Override
                                    public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                        if (response.code()==200)
                                        {
                                            if (response.body().success==1)
                                            {
                                                Toast.makeText(CartActivity.this, "Thank you ,Order Place", Toast.LENGTH_SHORT).show();

                                                Common.cartRepository.emptyCart();
                                                badgeDrawable.clearNumber();
                                                badgeDrawable.setVisible(false);
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(CartActivity.this, "Send Notification Failed !", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MyResponse> call, Throwable t) {
                                        Toast.makeText(CartActivity.this, ""+t.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(CartActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }






    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(CartActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

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

    @Override
    public void onResume() {
        loadCartItems();
        updateCartCount();
        super.onResume();
    }


}