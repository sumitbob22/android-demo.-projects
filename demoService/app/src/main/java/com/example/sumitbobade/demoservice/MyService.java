package com.example.sumitbobade.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Random;

/**
 * Created by sumitbobade on 04/04/18.
 */

public class MyService extends Service
{

    private int randomNumber;
    private boolean isRandomNoGeneraterOn;

    private final int MIN=0;
    private final int MAX=100;
    private IBinder mBinder=new MyServiceBinder();

    LocalBroadcastManager localBroadcastManager;


    class MyServiceBinder extends Binder
    {
        public MyService getService()
        {
            return MyService.this;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        stopRandomNoGenerater();
        Log.i("Stop service ","inside on destroy");
    }


    @Override
    public void onCreate() {
        super.onCreate();



     //   localBroadcastManager=LocalBroadcastManager.getInstance(getApplicationContext());

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {


        isRandomNoGeneraterOn=true;
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                Log.i("inside onStartCommand","Thread id is :"+Thread.currentThread().getId());
                startRandomNoGenerater();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }


    public void startRandomNoGenerater()
    {

        while(isRandomNoGeneraterOn)
        {

            try {
                Thread.sleep(1000);
                if(isRandomNoGeneraterOn)
                {
                    Intent intent=new Intent();
                    intent.setAction("myIntent");
                    randomNumber = new Random().nextInt(MAX) + MIN;
                    intent.putExtra("randomNo",randomNumber);
                    sendBroadcast(intent);
                    Log.i("StartRandomGenerator ", "Random No is : " + randomNumber);
                }
                } catch (InterruptedException e)
                {

                    Log.i("thread Interupted","thread Interupted");
                }


        }
    }

    public void stopRandomNoGenerater()
    {
            isRandomNoGeneraterOn=false;
    }

    public int getRandomNumber()
    {
        return  randomNumber;
    }

}
