package com.droidaxel.customcalendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Abhijeet.J
 */
public class Utilities {

    private Context context;

    public Utilities(Context context) {
        this.context = context;
    }

    public Calendar getCalendarFromString(String strDate, String strFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat,
                Locale.getDefault());

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(strDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cal;
    }

    public String formatDate(Calendar cal, String strDateFormat) {
        return (String) DateFormat.format(strDateFormat, cal);
    }

    public void showToast(String strMessage) {
        Toast toast = Toast.makeText(context, strMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}
