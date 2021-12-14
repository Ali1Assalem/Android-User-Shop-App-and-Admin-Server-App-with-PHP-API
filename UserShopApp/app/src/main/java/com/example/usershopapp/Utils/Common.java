package com.example.usershopapp.Utils;

import com.example.usershopapp.Database.DataStore.CartRepository;
import com.example.usershopapp.Database.DataStore.FavoriteRepository;
import com.example.usershopapp.Database.Local.AliRoomDatabase;
import com.example.usershopapp.Model.Menu;
import com.example.usershopapp.Model.Order;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.Model.User;
import com.example.usershopapp.Retrofit.FCMClient;
import com.example.usershopapp.Retrofit.IFCMService;
import com.example.usershopapp.Retrofit.IStoreApi;
import com.example.usershopapp.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {

    //public static final String BASE_URL="http://10.0.2.2/store/";
    public static final String BASE_URL="http://192.168.1.109:8081/store/";
    //public static final String BASE_URL="http://192.168.249.250:80/store/";
    public static User currentUser=null;
    public static Menu currentMenu=null;
    public static Order currentOrder=null;

    public static String name=null;
    public static String email=null;
    public static String address=null;

    public static int sizeOfCup=-1;


    public static final String Topping_Munu_ID="4";
    public static List<Product>  toppingList=new ArrayList<>();

    public static List<String> toppingAdded=new ArrayList<>();
    public static Double toppingPrice=0.0;

    public static AliRoomDatabase aliRoomDatabase;
    public static CartRepository cartRepository;
    public static FavoriteRepository favoriteRepository;


    public static IStoreApi getApi(){
        return RetrofitClient.getClient(BASE_URL).create(IStoreApi.class);
    }



    public static final String FCM_API ="https://fcm.googleapis.com/" ;

    public static IFCMService getFCMService()
    {
        return FCMClient.getClient(FCM_API).create(IFCMService.class);
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
