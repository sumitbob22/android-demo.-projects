package com.example.sumit.aithmeticapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

        EditText et1,et2;
        Button btn;
        TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.editText);
        et2=findViewById(R.id.editText2);
        tv=findViewById(R.id.textView4);
        btn=findViewById(R.id.button);
        System.out.println("Starting app");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int no1=Integer.parseInt(et1.getText().toString());
                int no2=Integer.parseInt(et2.getText().toString());

                int ans=no1+no2;
                tv.setText(ans);

            }
        });


    }

}
