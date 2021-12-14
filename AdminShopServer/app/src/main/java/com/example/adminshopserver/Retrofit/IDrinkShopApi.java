package com.example.adminshopserver.Retrofit;


import com.example.adminshopserver.Model.Category;
import com.example.adminshopserver.Model.Drink;
import com.example.adminshopserver.Model.Order;
import com.example.adminshopserver.Model.Token;

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

public interface IDrinkShopApi {

    @GET("getmenu.php")
    Observable<List<Category>> getMenu();

    @FormUrlEncoded
    @POST("server/category/add_category.php")
    Observable<String> addNewCategory(@Field("name") String name,
                                      @Field("imgPath") String imgPath);

    @Multipart
    @POST("server/category/upload_category_img.php")
    Call<String> uploadCategoryFile(@Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("server/category/update_category.php")
    Observable<String> updateCategory( @Field("id") String id,
                                       @Field("name") String name,
                                       @Field("imgPath") String imgPath);


    @FormUrlEncoded
    @POST("server/category/delete_category.php")
    Observable<String> deleteCategory(@Field("id") String id);


    @FormUrlEncoded
    @POST("getproduct.php")
    Observable<List<Drink>> getProductByMenuID(@Field("menuid") String menuID);

    @FormUrlEncoded
    @POST("server/product/add_product.php")
    Observable<String> addNewProduct(@Field("name") String name,
                                     @Field("imgPath") String imgPath,
                                     @Field("price") String price,
                                     @Field("menuId") String menuId);

    @Multipart
    @POST("server/product/upload_product_img.php")
    Call<String> uploadProductFile(@Part MultipartBody.Part file);



    @FormUrlEncoded
    @POST("server/product/update_product.php")
    Observable<String> updateProduct( @Field("id") String id,
                                      @Field("name") String name,
                                      @Field("imgPath") String imgPath,
                                      @Field("price") String price,
                                      @Field("menuId") String menuId);


    @FormUrlEncoded
    @POST("server/product/delete_product.php")
    Observable<String> deleteProduct(@Field("id") String id);


    @FormUrlEncoded
    @POST("server/order/getorder.php")
    Observable<List<Order>> getAllOrder(@Field("status") String status);

    @FormUrlEncoded
    @POST("server/order/updateorderstatus.php")
    Observable<String> updateOrderStatus(@Field("email") String email,
                                         @Field("order_id") long orderId,
                                         @Field("status") int status);


    @FormUrlEncoded
    @POST("updatetoken.php")
    Call<String> updateToken(
            @Field("email") String email,
            @Field("token") String token,
            @Field("isServerToken") String isServerToken);



    @FormUrlEncoded
    @POST("gettoken.php")
    Call<Token> getToken(@Field("email") String email,
                         @Field("isServerToken") String isServerToken);

}


