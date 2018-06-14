package com.example.sumitbobade.intentresponce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edt;
    TextView tv;

    int MSG_REQ=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    edt=(EditText)findViewById(R.id.editText1);
    btn=(Button)findViewById(R.id.button1);
    tv=(TextView)findViewById(R.id.textView2);


    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent it=new Intent(getApplicationContext(),second.class);
            String s=edt.getText().toString();

            it.putExtra("message",s);

            startActivityForResult(it,MSG_REQ);

        }
    });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            if(requestCode==MSG_REQ)
            {
                if(resultCode==RESULT_OK)
                {

                    String reply=data.getStringExtra(second.message2);
                    //tv.setText(reply);
                    Toast.makeText(this,reply,Toast.LENGTH_LONG).show();



                }

            }


    }
}
