package com.example.sumitbobade.explicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;

    StudentData st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.msg);
        btn=(Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(getApplicationContext(),second.class);

                //String s=et.getText().toString();

                    st=new StudentData(22,"sumit");

                    it.putExtra("message", (Serializable) st);

                startActivity(it);

            }
        });



    }
}
