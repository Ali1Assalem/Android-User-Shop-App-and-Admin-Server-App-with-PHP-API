package com.example.adminshopserver.Retrofit;

import com.example.adminshopserver.Model.DataMessage;
import com.example.adminshopserver.Model.MyResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMServices {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAlGHbfKE:APA91bHZHu7msN46FUg1DHfVbkepd-nDSBoXjv1lgf_LZbwkhrwIIwyWYz_aDwK1e8x5SxUGh2B8Zlia7xi-QBxyPGBG9x4gAfon6hTxCNnHD-vs3Wft-YmrlsaYHyU_uYmN9_dqU0SJ"
    })


    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body DataMessage body);
}
