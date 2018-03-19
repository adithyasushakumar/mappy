//package com.adhithya.track.homeMap;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.adhithya.track.model.LocationModel;
//import com.adhithya.track.responce.Locationresponce;
//import com.adhithya.track.service.Config;
//import com.adhithya.track.service.RetroClient;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by ASUS on 3/19/2018.
// */
//
//public class sendlocation extends IntentService {
//
//    public sendlocation() {
//        super("sendlocation");
//    }
//
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        String uid = intent.getStringExtra("uid");
//        String lati = intent.getStringExtra("lati");
//        String longi = intent.getStringExtra("longi");
//        SendLocation(uid,lati,longi);
//
//
//    }
//    private void SendLocation(String uid, String lati, String longi){
//
//        SEND(Config.KEY,lati,longi).enqueue(new Callback<Locationresponce>() {
//            @Override
//            public void onResponse(Call<Locationresponce> call, Response<Locationresponce> response) {
//                if (response.body().getStatus()==1){
//
//
//                    List<LocationModel> list=response.body().getUser();
//                    LocationModel model=list.get(0);
//
//                    Log.e("MODEL",""+model.getLattitude()+" "+model.getLongtitude());
//                }else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Locationresponce> call, Throwable t) {
//                Log.e("MODEL",""+t);
//            }
//        });
//    }
//
//
//    private Call<Locationresponce> SEND(String uid,String lati, String longi){
//        return RetroClient.getApiService().sendLocation( uid,lati,longi);
//    }
//}
