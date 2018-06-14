package com.example.sumitbobade.shoppingmart.api;

import android.util.Log;

import com.example.sumitbobade.shoppingmart.Provider.ProductListProvider;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sumitbobade on 20/04/18.
 */

public class ApiConnector
{
    public static final String TAG="ApiConnector";
    public static int statuscode;

    public static String getResponce()
    {
        OkHttpClient client=new OkHttpClient();
        String responceString=null;
        Response response=null;
        Request request = new Request.Builder()
                .url("https://mobiletest-hackathon.herokuapp.com/getdata/")
                .get()
                .build();

        try{
            response = client.newCall(request).execute();
        }
        catch(Exception e)
        {
            Log.i(TAG,"Responce Failure");
        }

        if(response!=null)
        {
            try {
                responceString = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
        {
            return null;
        }
            return responceString;
        }
}
