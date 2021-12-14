package com.example.usershopapp.Retrofit;


import com.example.usershopapp.Model.Banner;
import com.example.usershopapp.Model.CheckUserResponse;
import com.example.usershopapp.Model.Menu;
import com.example.usershopapp.Model.Order;
import com.example.usershopapp.Model.OrderResult;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.Model.Token;
import com.example.usershopapp.Model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IStoreApi {
    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse> checkExistsUser(@Field("email") String email,
                                            @Field("password") String password);


    @FormUrlEncoded
    @POST("register.php")
    Call<User> registerNewUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("address") String address);

    @FormUrlEncoded
    @POST("getUser.php")
    Call<User> getUserInformation(@Field("email") String email);

    @GET("getbanner.php")
    Observable<List<Banner>> getBanners();

    @GET("getmenu.php")
    Observable<List<Menu>> getMenu();

    @GET("getRandom.php")
    Observable<List<Product>> getRandom();


    @FormUrlEncoded
    @POST("getproduct.php")
    Observable<List<Product>> getProductByMenuID(
            @Field("menuid") String menuId);



    @Multipart
    @POST("upload.php")
    Call<String> uploadFile(@Part MultipartBody.Part email, @Part MultipartBody.Part file);


    @GET("getAllDrink.php")
    Observable<List<Product>> getAllDrinks();




    @FormUrlEncoded
    @POST("submitorder.php")
    Call<OrderResult> insertNewOrder(
            @Field("price") float orderPrice,
            @Field("orderDetail") String orderDetail,
            @Field("comment") String comment,
            @Field("address") String address,
            @Field("email") String email,
            @Field("paymentMethod") String paymentMethod);


    @FormUrlEncoded
    @POST("getorder.php")
    Observable<List<Order>> getOrder(
            @Field("userEmail") String userEmail,
            @Field("status") String status);


    @FormUrlEncoded
    @POST("cancelorder.php")
    Call<String> cancelOrder(
            @Field("orderId") String orderId,
            @Field("userEmail") String userEmail);


    @FormUrlEncoded
    @POST("updatetoken.php")
    Call<String> updateToken(
            @Field("email") String email,
            @Field("token") String token,
            @Field("isServerToken") String isServerToken);

    @FormUrlEncoded
    @POST("gettoken.php")
    Call<Token> getToken(
            @Field("email") String email,
            @Field("isServerToken") String isServerToken);

}
