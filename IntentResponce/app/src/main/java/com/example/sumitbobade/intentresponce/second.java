package com.example.sumitbobade.intentresponce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class second extends AppCompatActivity {


        final static String message2 = "message2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle=getIntent().getExtras();

        String s=bundle.getString("message");

        Intent it2=new Intent();

        it2.putExtra(message2,"Successfully got request");
        setResult(RESULT_OK,it2);

        finish();


    }
}
