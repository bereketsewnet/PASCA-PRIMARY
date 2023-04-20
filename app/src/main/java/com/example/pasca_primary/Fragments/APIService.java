package com.example.pasca_primary.Fragments;



import com.example.pasca_primary.Notifications.MyResponse;
import com.example.pasca_primary.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAmN1zWfs:APA91bHJkNBa-o-U-g1N1SKRCkqQy_Wjk6TIeIEf_qkIFUW6WvPdbMcjSwPEbkOXrW-FNprjH0ClRsDgjOlXJor7_a-yJ0N2-1p8b8MS54RApQpC4_7PF9E49VnpPgOqMgt3XxqDqzo4"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
