package com.example.sumitbobade.uiassignment;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;

    CustomPagerAdapter adapter;

    Switch sh;
    TextView gmtText;

    TextView dateShow;
    TextView timeShow;

    CheckBox fb;
    CheckBox tweeter;

    Button lastb;


    @Override
    protected void onStart() {
        super.onStart();

        SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        timeShow.setText(formattedDate);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager=(ViewPager)findViewById(R.id.view_pager);
        adapter=new CustomPagerAdapter(this);
        viewPager.setAdapter(adapter);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#000000\" >" + "Share Multi-Photo Post" + "</font></p>")));
        sh=(Switch)findViewById(R.id.switch1);
        lastb = (Button)findViewById(R.id.button);
        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gmtText=(TextView)findViewById(R.id.gmttext);
                if(!sh.isChecked())
                {
                    LinearLayout mainLayout=(LinearLayout)findViewById(R.id.calenderview);
                    mainLayout.setVisibility(LinearLayout.GONE);
                    gmtText.setVisibility(View.INVISIBLE);
                    lastb.setText("SHARE");

                }else
                {

                    LinearLayout mainLayout=(LinearLayout)findViewById(R.id.calenderview);
                    mainLayout.setVisibility(LinearLayout.VISIBLE);
                    gmtText.setVisibility(View.VISIBLE);
                    lastb.setText("SCHEDULE");
                }


            }
        });

            dateShow=(TextView)findViewById(R.id.dp);
            dateShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"datePicker");

                }
            });

            timeShow=(TextView)findViewById(R.id.tp);
            timeShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                            TimeDialog td=new TimeDialog(view);
                            FragmentTransaction ft=getFragmentManager().beginTransaction();
                            td.show(ft,"timePicker");

                }
            });

         lastb=(Button)findViewById(R.id.button);

         fb=(CheckBox)findViewById(R.id.fbcheck);
         tweeter=(CheckBox)findViewById(R.id.tweetcheck);

        LinearLayout lastLayout=(LinearLayout)findViewById(R.id.lastl);
        lastLayout.setVisibility(LinearLayout.INVISIBLE);

         lastb.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                    if((fb.isChecked()==true)|| (tweeter.isChecked()==true))
                    {

                        LinearLayout lastLayout=(LinearLayout)findViewById(R.id.lastl);
                        lastLayout.setVisibility(LinearLayout.INVISIBLE);


                    }else
                    {

                        LinearLayout lastLayout=(LinearLayout)findViewById(R.id.lastl);
                        lastLayout.setVisibility(LinearLayout.VISIBLE);

                    }

             }
         });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fb.isChecked())
                {
                    lastb.setBackgroundColor(lastb.getContext().getResources().getColor(R.color.btncolor));
                    LinearLayout lastLayout=(LinearLayout)findViewById(R.id.lastl);
                    lastLayout.setVisibility(LinearLayout.INVISIBLE);

                }else if (!tweeter.isChecked())
                {
                    lastb.setBackgroundColor(lastb.getContext().getResources().getColor(R.color.buttonback));
                }
            }
        });

        tweeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tweeter.isChecked())
                {
                    lastb.setBackgroundColor(lastb.getContext().getResources().getColor(R.color.btncolor));
                    LinearLayout lastLayout=(LinearLayout)findViewById(R.id.lastl);
                    lastLayout.setVisibility(LinearLayout.INVISIBLE);
                }else if(!fb.isChecked())
                {
                    lastb.setBackgroundColor(lastb.getContext().getResources().getColor(R.color.buttonback));
                }
            }
        });

    }
}
