package com.droidaxel.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    private Context context;
    LinearLayout weekView;
    LinearLayout[] eventsView;
    Calendar calThisMonth;
    TextView monthName;
    ImageView prev, next;
    int noOfWeeks;
    Calendar calToday;
    int iCurrentMonth = 0, iCurrentYear = 0;
    Utilities utilities;
    private LinearLayout weekHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        context = this;
        utilities = new Utilities(context);
        initUI();
    }

    private void initUI() {
        LinearLayout.LayoutParams linlayparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        weekView = new LinearLayout(context);
        weekView.setOrientation(LinearLayout.VERTICAL);
        weekView.setLayoutParams(linlayparams);
        weekView.setBackgroundColor(Color.BLUE);
        final LinearLayout linlayCalParent = (LinearLayout) findViewById(R.id.calParent);
//		linlayCalParent.addView(weekView);

        weekHolder = (LinearLayout) findViewById(R.id.week_holder);

//        addSample();

        // weekView =
        // (LinearLayout) findViewById(R.id.xlinlayCalendar);
        monthName = (TextView) findViewById(R.id.xtxtvwMonthName);


        calThisMonth = Calendar.getInstance();
        calToday = Calendar.getInstance();
        iCurrentMonth = calToday.get(Calendar.MONTH);
        iCurrentYear = calToday.get(Calendar.YEAR);
//        renderCalendar(calThisMonth);
//        linlayCalParent.addView(weekHolder);

        prev = (ImageView) findViewById(R.id.ximgvwMonthPrev);
        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calThisMonth.add(Calendar.MONTH, -1);
                renderCalendar(calThisMonth);
            }
        });

        next = (ImageView) findViewById(R.id.ximgvwMonthNext);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calThisMonth.add(Calendar.MONTH, 1);
                renderCalendar(calThisMonth);
            }
        });
    }

    private void addDayInWeek(){

    }

    private void addSample() {

        weekHolder.setBackgroundColor(Color.BLUE);
        Button button = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setText("Hello");
        weekHolder.addView(button);
    }

    /**
     * THIS METHOD IS USED TO DISPLAY CALENDAR
     *
     * @param calThisMonth
     */
    public void renderCalendar(Calendar calThisMonth) {

//		xlinlayWeek.removeAllViews();
        monthName.setText(utilities
                .formatDate(calThisMonth, "MMMM yyyy"));
        int iCurrentDay = calThisMonth.get(Calendar.DAY_OF_MONTH);
        calThisMonth.set(Calendar.DAY_OF_MONTH, 1);
        int iFirstDayOfMonth = getDayNo(calThisMonth);
        int iDaysInMonth = calThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        noOfWeeks = calThisMonth.getActualMaximum(Calendar.WEEK_OF_MONTH);
//        if (noOfWeeks == 4)
//            noOfWeeks = 5;
//        else {
//            noOfWeeks = 6;
//        }

        eventsView = new LinearLayout[noOfWeeks];
        setLinearLayoutProps(eventsView);

        calThisMonth.set(Calendar.DAY_OF_MONTH, iCurrentDay);
        // calThisMonth = Calendar.getInstance();
        // iFirstDayOfMonth = iFirstDayOfMonth - 2;
        int iTodaysDay = calThisMonth.get(Calendar.DAY_OF_MONTH);
        int iDay = 1, iDayOfWeek = iFirstDayOfMonth;

        for (int i = 0; i < noOfWeeks; i++) {
            final LinearLayout viewWeek = (LinearLayout) LayoutInflater.from(
                    context).inflate(R.layout.week, null);

            for (int j = 0; j < 7; j++) {
                if (iFirstDayOfMonth >= 7) {
                    iFirstDayOfMonth = iFirstDayOfMonth % 7;
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                final LinearLayout.LayoutParams textviewParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.weight = 1;

                final View viewDay = LayoutInflater.from(context).inflate(
                        R.layout.calendar_item, null);
                viewDay.setLayoutParams(params);

                viewWeek.addView(viewDay);
                viewWeek.setTag(i);

                /**
                 * TO DISPLAY THE DAYS
                 */
                if (iDay <= iDaysInMonth && j == iFirstDayOfMonth) {
                    final TextView txtvwDay = (TextView) viewDay
                            .findViewById(R.id.xtxtvwDay);
                    txtvwDay.setVisibility(View.VISIBLE);
                    txtvwDay.setText("" + iDay);

                    /**
                     * HIGHLIGHT TODAY'S DATE
                     */
                    if (iDay == iTodaysDay
                            && calThisMonth.get(Calendar.MONTH) == iCurrentMonth
                            && calThisMonth.get(Calendar.YEAR) == iCurrentYear) {
                        LinearLayout xlinlayDay = (LinearLayout) viewDay
                                .findViewById(R.id.xlinlayDay);
                        // deSelectAll();
                        xlinlayDay
                                .setBackgroundResource(R.drawable.calendar_sel);
                        selectDay(xlinlayDay);

                    }
                    viewDay.setTag(iDay);
                    viewDay.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // TODO Auto-generated method stub

                            if (event.getAction() == MotionEvent.ACTION_DOWN)
                                utilities.showToast(viewDay.getTag()
                                        .toString());

                            else if (event.getAction() == MotionEvent.ACTION_UP)
                                utilities.showToast(viewDay.getTag()
                                        .toString());

                            return false;
                        }
                    });

                    viewDay.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            LinearLayout xlinlayDay = (LinearLayout) viewDay
                                    .findViewById(R.id.xlinlayDay);
                            deSelectAll();
                            xlinlayDay
                                    .setBackgroundResource(R.drawable.calendar_sel);
                            Calendar calThis = Calendar.getInstance();
                            calThis.set(Calendar.DAY_OF_MONTH, Integer
                                    .parseInt(txtvwDay.getText().toString()));
                            selectDay(xlinlayDay);

                            // Utilities.showToast(""
                            // + myUtilities.formatDate(calThis,
                            // "dd MMMM yyyy"));

                        }
                    });

                    /**
                     * TO DISPLAY DOT FOR AN ENTRY i have considered 10th of
                     * every month as en entry to display
                     */
                    ImageView imgvDot = (ImageView) viewDay
                            .findViewById(R.id.ximgvwdot);
                    if (iDay == 10)
                        imgvDot.setVisibility(View.VISIBLE);
                    imgvDot.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            addEvent(eventsView[(Integer) viewWeek
                                    .getTag()]);
                        }
                    });

                    iFirstDayOfMonth++;
                    iDay++;
                }

            }

            weekHolder.addView(viewWeek);
            weekHolder.addView(eventsView[i]);

        }

        // Utilities.showToast("" + iNoOfWeeks + "\n"
        // + myUtilities.formatDate(calThisMonth, "EEEE d MMM yyyy"));
    }

    /**
     * When a day is selected
     */
    public void deSelectAll() {
        for (int i = 0; i < noOfWeeks; i++) {
            eventsView[i].removeAllViews();

            for (int j = 0; j < 7; j++) {
                LinearLayout xlinlayDayWhole = (LinearLayout) ((LinearLayout) weekView
                        .getChildAt(i * 2)).getChildAt(j);
                LinearLayout xlinlayDay = (LinearLayout) xlinlayDayWhole
                        .findViewById(R.id.xlinlayDay);

                TextView txtvwDay = (TextView) xlinlayDay
                        .findViewById(R.id.xtxtvwDay);
                txtvwDay.setTextColor(Color.BLACK);
                xlinlayDay.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public void selectDay(LinearLayout linlaySelDay) {
        TextView txtvwDay = (TextView) linlaySelDay
                .findViewById(R.id.xtxtvwDay);
        txtvwDay.setTextColor(Color.WHITE);
    }

    /**
     * Set properties to linear layout array
     *
     * @param linlayEvent
     */
    public void setLinearLayoutProps(LinearLayout[] linlayEvent) {
        LinearLayout.LayoutParams linlayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < noOfWeeks; i++) {
            linlayEvent[i] = new LinearLayout(context);
            linlayEvent[i].setLayoutParams(linlayParams);
            linlayEvent[i].setVisibility(View.GONE);
            linlayEvent[i].setBackgroundColor(Color.GRAY);
            linlayEvent[i].setOrientation(LinearLayout.VERTICAL);
        }
    }

    public void addEvent(LinearLayout linlayEvent) {
        LinearLayout.LayoutParams linlayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        linlayEvent.removeAllViews();
        linlayEvent.setVisibility(View.VISIBLE);

        for (int i = 0; i < 2; i++) {
            View inflateView = LayoutInflater.from(context).inflate(
                    R.layout.event_type, null);
            // LinearLayout linlayEventItem=(LinearLayout)
            // inflateView.findViewById(R.id.xlinlayevent_item);
            inflateView.setLayoutParams(linlayParams);

//            inflateView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//                    startActivity(new Intent(context, DetailActivity.class));
//                }
//            });

            linlayEvent.addView(inflateView);
        }
    }

    /**
     * CUSTOMIZE DAY NO TO SET A WEEK START DAY
     *
     * @param cal
     * @return
     */
    public int getDayNo(Calendar cal) {
        int iDay = 0;

        int iDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (iDayOfWeek == Calendar.MONDAY)
            iDay = 1;
        else if (iDayOfWeek == Calendar.TUESDAY)
            iDay = 2;
        else if (iDayOfWeek == Calendar.WEDNESDAY)
            iDay = 3;
        else if (iDayOfWeek == Calendar.THURSDAY)
            iDay = 4;
        else if (iDayOfWeek == Calendar.FRIDAY)
            iDay = 5;
        else if (iDayOfWeek == Calendar.SATURDAY)
            iDay = 6;
        else if (iDayOfWeek == Calendar.SUNDAY)
            iDay = 0;

        return iDay;
    }
}