package com.example.sumitbobade.servicemsgclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button bind;
    private Button unbind;
    private Button getRnadomNo;
    private TextView randomNoTextView;

    private Intent serviceIntent;

    boolean isBound=false;

    private int randomNoValue;
    private static final int GET_RANDOM_NO_FLAG=0;


    Messenger randomNoRequestMessenger,randomNoReceivedMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    bind=findViewById(R.id.bind);
    unbind=findViewById(R.id.unbind);
    getRnadomNo=findViewById(R.id.getRandomNo);
    randomNoTextView=findViewById(R.id.randomNotext);


    bind.setOnClickListener(this);
    unbind.setOnClickListener(this);
    getRnadomNo.setOnClickListener(this);


    serviceIntent=new Intent();

    serviceIntent.setComponent(new ComponentName("com.example.sumitbobade.servicebymesenger","com.example.sumitbobade.servicebymesenger.MyService"));
    }


    class RandomNoReceiverHAndler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            randomNoValue=0;

            switch(msg.what)
            {
                case GET_RANDOM_NO_FLAG:
                    randomNoValue=msg.arg1;
                    randomNoTextView.setText("Random no : "+randomNoValue);
                    break;
            }

            super.handleMessage(msg);
        }
    }


    ServiceConnection serviceConnection=new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
           randomNoRequestMessenger=new Messenger(service);
           randomNoReceivedMessenger=new Messenger(new RandomNoReceiverHAndler());
           isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            randomNoRequestMessenger=null;
            randomNoReceivedMessenger=null;
            isBound=false;

        }
    };


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.bind:
                            BindToRemoteService();
                            break;
            case R.id.unbind:
                                UnBindToRemoteService();
                                break;
            case R.id.getRandomNo:
                                    fetchRandonNoFromRemoteService();
                                    break;
        }

    }

    private void fetchRandonNoFromRemoteService()
    {
        if(isBound)
        {
            Message requestMsg=Message.obtain(null,GET_RANDOM_NO_FLAG);
            requestMsg.replyTo=randomNoReceivedMessenger;

            try {
                randomNoRequestMessenger.send(requestMsg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else
        {
            Toast.makeText(this,"Service unBound cant get random no",Toast.LENGTH_SHORT).show();
        }

    }

    private void UnBindToRemoteService()
    {
        if(isBound)
        {
            unbindService(serviceConnection);
            isBound = false;
            Toast.makeText(this, "Service Unbound", Toast.LENGTH_SHORT).show();
        }
    }

    private void BindToRemoteService()
    {
        bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
        Toast.makeText(this,"Service Bound",Toast.LENGTH_SHORT).show();

    }


}
