package com.droidaxel.customcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Abhijeet.J
 */
public class DateAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> dates;

    public DateAdapter(Context context, ArrayList<String> dates) {
        this.context = context;
        this.dates = dates;
    }

    public int getCount() {
        return dates.size();
    }

    public Object getItem(int position) {
        return dates.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView;
        if (convertView == null) {

            textView = (TextView) LayoutInflater.from(context).inflate(R.layout.date_view, null);
            textView.setText(dates.get(position));
        } else {
            textView = (TextView) convertView;
        }
        return textView;

//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };
}
