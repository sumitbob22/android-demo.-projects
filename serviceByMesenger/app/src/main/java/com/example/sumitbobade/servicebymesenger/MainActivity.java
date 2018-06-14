package com.example.sumitbobade.servicebymesenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button start;
    Button stop;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.startb);
        stop=findViewById(R.id.stopb);
        intent=new Intent(this,MyService.class);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {

        switch(v.getId())
        {
            case R.id.startb:
                            startService(intent);
                            break;
            case R.id.stopb:
                            stopService(intent);
        }

    }
}
