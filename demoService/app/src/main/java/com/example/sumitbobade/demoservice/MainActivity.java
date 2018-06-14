package com.example.sumitbobade.demoservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Intent serviceIntent;

    private Button startb ;
    private Button stopb;
    private Button bind;
    private Button unbind;
    private Button getrandomno;
    private TextView showNo;

    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection=null;

     Receiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Log.i("in main thread ","main thread id "+Thread.currentThread().getId());

         serviceIntent=new Intent(getApplicationContext(),MyService.class);

         startb=findViewById(R.id.start);
         stopb=findViewById(R.id.stop);
        bind=findViewById(R.id.bind);
        unbind=findViewById(R.id.unbind);
        getrandomno=findViewById(R.id.getRandom);
        showNo=findViewById(R.id.showNo);


         startb.setOnClickListener(this);
         stopb.setOnClickListener(this);

         bind.setOnClickListener(this);
         unbind.setOnClickListener(this);
         getrandomno.setOnClickListener(this);


         registerReceiver(receiver,new IntentFilter());

    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.start:
                            startService(serviceIntent);
                             bindMyService();
                            break;
            case R.id.stop:
                            stopService(serviceIntent);
                            break;

//            case R.id.bind:
//                            bindMyService();
//                            break;
            case R.id.unbind:
                             unBindService();
                            break;
           case R.id.getRandom:setRandomNo();
                               // break;
        }

    }

    private void bindMyService()
    {
        if(serviceConnection==null) {
            Log.i("", "inside on Bind method");
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) service;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;
                }
            };
        }
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }
    private void unBindService()
    {
        if(isServiceBound)
        {
            Log.i("","inside unBind method");
            unbindService(serviceConnection);
            isServiceBound=false;
        }
    }
    private void setRandomNo()
    {
        Log.i("","inside setRandom method");

//        if(isServiceBound)
//        {
//            showNo.setText(" "+myService.getRandomNumber());
//        }else
//        {
//            showNo.setText("Service not found");
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        receiver=new Receiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("myIntent");
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    class Receiver extends BroadcastReceiver
    {
        public void onReceive(Context context, Intent intent)
        {
            int randomNo=intent.getIntExtra("randomNo",0);
             //Log.e("on receive","inside on revceive"+randomNo);
            // Toast.makeText(con,"recieved random no"+randomNo,Toast.LENGTH_SHORT).show();

            //System.out.println("Random no: "+randomNo);
            if(isServiceBound)
            {
                showNo.setText(" "+randomNo);
            }else
            {
                showNo.setText("Service not found");
            }
        }
    }

}
