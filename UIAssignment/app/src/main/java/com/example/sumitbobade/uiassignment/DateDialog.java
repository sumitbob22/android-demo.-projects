package com.example.sumitbobade.uiassignment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by sumitbobade on 10/03/18.
 */

@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    TextView  dateText;

    int dayOfWeek;
    String dayText;

    public DateDialog(View view)
    {
        dateText=(TextView)view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

       final Calendar c=Calendar.getInstance();

       int year=c.get(Calendar.YEAR);
       int month=c.get(Calendar.MONTH);
       int day=c.get(Calendar.DAY_OF_WEEK);
       // dayOfWeek=c.get(Calendar.DAY_OF_YEAR);


        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view , int year, int month, int day)
    {

        //final Calendar calendar=Calendar.getInstance();
        //String weekDay = calendar.getDisplayName(day, Calendar.LONG, Locale.getDefault());
        //System.out.println("name is "+weekDay);


        String[] monthn = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

        String date=" "+day+","+monthn[month]+" "+day+","+year;
        dateText.setText(date);

    }
}
