package com.example.sumitbobade.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        HelloFragment helloFragment=new HelloFragment();

        FragmentManager manager=getFragmentManager();

        FragmentTransaction ft=manager.beginTransaction();

        ft.add(R.id.relative,helloFragment,"helloFrag");

        ft.commit();


    }
}
