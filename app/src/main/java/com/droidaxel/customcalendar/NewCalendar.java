package com.droidaxel.customcalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class NewCalendar extends AppCompatActivity {

    private Context context;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar);
        context = this;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new DateAdapter(this, getDates()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(context, "" + getDates().get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> getDates() {
        ArrayList<String> dates = new ArrayList<>();
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("");
        dates.add("1");
        dates.add("2");
        dates.add("3");
        dates.add("4");
        dates.add("5");
        return dates;
    }

    private void addDay() {
        Button button = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setText("Hello");
        gridLayout.addView(button);
    }
}