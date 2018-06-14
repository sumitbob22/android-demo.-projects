package com.example.sumitbobade.demopushnotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView msgText;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgText=findViewById(R.id.msgText);

    }

    @Override
    protected void onStart() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("com.example.sumitbobade.demopushnotify_myIntent"));
        super.onStart();
    }

    BroadcastReceiver receiver=new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

            message=intent.getStringExtra("msg");
            Log.i("Tag inside in receive"," "+message);
            msgText.setText(""+message);
        }
    };

    @Override
    protected void onPause()
    {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onPause();
    }
}
