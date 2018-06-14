package com.example.sumitbobade.uiassignment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sumitbobade on 10/03/18.
 */

@SuppressLint("ValidFragment")
public class TimeDialog extends DialogFragment implements  TimePickerDialog.OnTimeSetListener{


    TextView time;
    int mode;

    @SuppressLint("ValidFragment")
    public TimeDialog(View view)
    {
        time=(TextView)view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        Calendar c=Calendar.getInstance();

        int hour=c.get(Calendar.HOUR);
        int minute=c.get(Calendar.MINUTE);

         mode=c.get(Calendar.AM_PM);


        return new TimePickerDialog(getActivity(),this,hour,minute,false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute)
    {
        Calendar calendar=Calendar.getInstance();

        String md=calendar.getDisplayName(mode, Calendar.LONG, Locale.getDefault());

        DecimalFormat mFormat= new DecimalFormat("00");
        String hourtxt=mFormat.format(Double.valueOf(hour));
        String mintxt=mFormat.format(Double.valueOf(minute));


        String am_pm = "";

        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hour);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";


        String times=""+hourtxt+":"+mintxt+" "+am_pm;

        //String times=""+formattedDate;

        time.setText(times);



    }
}
