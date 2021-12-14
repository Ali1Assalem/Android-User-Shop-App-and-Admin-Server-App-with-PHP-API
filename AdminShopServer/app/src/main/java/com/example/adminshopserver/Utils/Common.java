package com.example.adminshopserver.Utils;

import com.example.adminshopserver.Model.Category;
import com.example.adminshopserver.Model.Drink;
import com.example.adminshopserver.Model.Order;
import com.example.adminshopserver.Retrofit.FCMRetrofitClient;
import com.example.adminshopserver.Retrofit.IDrinkShopApi;
import com.example.adminshopserver.Retrofit.IFCMServices;
import com.example.adminshopserver.Retrofit.RetrofitClient;


import java.util.ArrayList;
import java.util.List;


public class Common {

    public static Category currentCategory;
    public static Drink currentProduct;
    public static Order currentOrder;

    public static List<Category> menuList=new ArrayList<>();

    public static final String BASE_URL="http://192.168.1.109:8081/store/";



    public static IDrinkShopApi getApi()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopApi.class);
    }



    public static final String FCM_URL="https://fcm.googleapis.com/";

    public static IFCMServices getFCMAPI()
    {
        return FCMRetrofitClient.getClient(FCM_URL).create(IFCMServices.class);
    }



    public static String convertCodeToStatus(int orderStatus) {
        switch (orderStatus)
        {
            case 0:
                return "placed";
            case 1:
                return "Processing";
            case 2:
                return "Shipping";
            case 3:
                return "Shipped";
            case -1:
                return "Cancelled";
            default:
                return "Order Error";
        }
    }
}
