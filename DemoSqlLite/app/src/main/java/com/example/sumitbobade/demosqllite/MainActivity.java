package com.example.sumitbobade.demosqllite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StudentDatabse sdb=new StudentDatabse(this);

        SQLiteDatabase db=sdb.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("sid","22");
        values.put("name","sumit");
        values.put("marks","50");

        long row=db.insert(StudentDatabse.stud,null,values);

//        System.out.println("Row no is: "+row);



    }
}
