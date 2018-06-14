package com.example.sumitbobade.demosqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sumitbobade on 15/03/18.
 */

public class StudentDatabse extends SQLiteOpenHelper
{

    public static final String stud="stud";
    private static final String sid="sid";
    private static final String name="name";
    private static final String marks="marks";





    public StudentDatabse(Context context)
    {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table stud(sid text,name text,marks text)");

        String qr= "CREATE TABLE " + stud + "(" + sid+ " TEXT, " + name + " TEXT, " + marks + " TEXT, "+")";

        db.execSQL(qr);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists "+stud);
    onCreate(db);

    }
}
