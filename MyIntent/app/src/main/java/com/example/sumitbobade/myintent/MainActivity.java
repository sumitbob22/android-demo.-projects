package com.example.sumitbobade.myintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Uri uri=Uri.parse("http://www.google.com");
//        Intent it=new Intent(Intent.ACTION_VIEW,uri);
//
//        startActivity(it);


//        Uri uri=Uri.parse("tel:7507307342");
//        Intent it=new Intent(Intent.ACTION_DIAL,uri);
//
//        startActivity(it);

        Button b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });



    }
}
