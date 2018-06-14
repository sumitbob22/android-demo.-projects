package com.example.sumitbobade.servicebymesenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by sumitbobade on 05/04/18.
 */

public class MyService extends Service {


    private int randomNumber;
    private boolean isRandomNoGeneraterOn;

    private final int MIN=0;
    private final int MAX=100;

    public static final int  GET_MESSAGE=0;


    class RandomNoRequestHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {

            switch(msg.what)
            {

                case GET_MESSAGE :

                    Message randomNoMessage=Message.obtain(null,GET_MESSAGE);
                    randomNoMessage.arg1=getRandomNumber();

                    try {
                        msg.replyTo.send(randomNoMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
            super.handleMessage(msg);
        }
    }


    private Messenger radomNoMessanger=new Messenger(new RandomNoRequestHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return radomNoMessanger.getBinder();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        stopRandomNoGenerater();
        Log.i("Stop service ","inside on destroy");
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
                    randomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i("StartRandomGenerator ", "Random No is : " + randomNumber);
                }
            } catch (InterruptedException e) {

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
