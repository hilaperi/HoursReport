package hila.peri.hoursreportapp.ui.reports;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import hila.peri.hoursreportapp.MyAdapter;
import hila.peri.hoursreportapp.R;

import static hila.peri.hoursreportapp.ui.reports.MapsFragment.mLastLocation;

public class DatePickerFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    CalendarView simpleCalendarView;
    private MapsFragment location = new MapsFragment();
    private TextView date_picker_editTimeReport;
    private Button enterLoc;
    private Button exitLoc;
    GoogleMap googleMap;
    View regView;
    private TextView date_picker_editTotalHour;
    private TextView date_picker_kindOfWork;
    private TextView date_picker_timeReport;
    private TextView date_pickerd_totalHour;
    Location loc;
    static int mDay;
    private int mMonth;
    private int numOfDays;
    private int mYear;
    public static int min = 0;
//    public static hours
    private int datesArray[] = new int[30];
    int i = 0;
    String currentDateTimeString = java.text.DateFormat.getTimeInstance().format(new Date());
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    String currentDateString = java.text.DateFormat.getDateInstance().format(new Date());
    String[] day1 = currentDateString.split("[, ]");
    DateFormat day = new SimpleDateFormat("d");
    DayFragment dayFrag;
    private String cday;
    private String sTypeOfDay;
    private double lat = 0.0;
    private double lng = 0.0;
    public static final String CDAY = "CDAY";
    public static final String STYPEOFDAY = "STYPEOFDAY";
    public static final String LAT = "LAT";
    public static final String LNG = "LNG";
    private Month monthDays = new Month();
    private Day d = new Day();
    TextView dateNumber;
    TextView one_date_picker_kindOfWork;
    TextView one_date_pickerd_totalHour;
    TextView One_date_picker_editTotalHour;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_reports, container, false);
        findViews(view);
        try {
            initViews();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }



    private void findViews (View view){
        date_picker_editTimeReport = view.findViewById(R.id.date_picker_editTimeReport);
        enterLoc = view.findViewById(R.id.enterLoc);
        exitLoc = view.findViewById(R.id.exitLoc);
        date_picker_editTotalHour = view.findViewById(R.id.date_picker_editTotalHour);
        date_picker_kindOfWork = view.findViewById(R.id.date_picker_kindOfWork);
        date_picker_timeReport = view.findViewById(R.id.date_picker_timeReport);
        date_pickerd_totalHour = view.findViewById(R.id.date_pickerd_totalHour);
        simpleCalendarView = view.findViewById(R.id.simpleCalendarView);
        dateNumber = view.findViewById(R.id.dateNumber);
        one_date_picker_kindOfWork = view.findViewById(R.id.one_date_picker_kindOfWork);
        one_date_pickerd_totalHour = view.findViewById(R.id.one_date_pickerd_totalHour);
        One_date_picker_editTotalHour = view.findViewById(R.id.One_date_picker_editTotalHour);

    }

    private void initViews () throws ParseException {
//        date_picker_editTimeReport.setFormat12Hour(null);
//        date_picker_editTimeReport.setFormat24Hour("hh:mm:ss a");


        String typeOfDay = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("type_of_day", "0");
        if(typeOfDay.equals("sick_day")){
            date_picker_kindOfWork.setText("Sick Day");
            date_picker_kindOfWork.setTextColor(Color.RED);
            exitLoc.setVisibility(View.GONE);
            enterLoc.setVisibility(View.GONE);
            date_picker_timeReport.setVisibility(View.GONE);
            date_picker_editTimeReport.setVisibility(View.GONE);
            date_picker_editTotalHour.setVisibility(View.GONE);
            date_pickerd_totalHour.setVisibility(View.GONE);
        }else if(typeOfDay.equals("free_day")){
            date_picker_kindOfWork.setText("Free Day");
            date_picker_kindOfWork.setTextColor(Color.YELLOW);
            exitLoc.setVisibility(View.GONE);
            enterLoc.setVisibility(View.GONE);
            date_picker_timeReport.setVisibility(View.GONE);
            date_picker_editTimeReport.setVisibility(View.GONE);
            date_picker_editTotalHour.setVisibility(View.GONE);
            date_pickerd_totalHour.setVisibility(View.GONE);
        }else if(typeOfDay.equals("work_day")){
            date_picker_kindOfWork.setText("Work Day");
            date_picker_kindOfWork.setTextColor(Color.GREEN);

        }



        String time = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("reported_time", "reported time");
        date_picker_editTimeReport.setText(time.toString());
        Date time1 = dateFormat.parse(time);
        Date time2 = dateFormat.parse(currentDateTimeString);


        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                mDay = date;
                mYear = year;
                mMonth = month;



                String text= String.valueOf(mDay);
                Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT).show();
                if(mDay < Integer.parseInt(day1[1])) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    dayFrag = new DayFragment();
                    ft.replace(R.id.report_LAY_reportDay, dayFrag);
                    ft.commit();

                }

            }
        });

        enterLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                location.onLocationChanged(loc);
//                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("has_clicked12", "yes").apply();

//                String text= "Location saved have a great day at work !";
//                String time = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("reported_time", "reported time");
                String text= String.valueOf(mDay);

//                Toast.makeText(getActivity(), day1[1].trim(),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), currentDateString,Toast.LENGTH_SHORT).show();

                datesArray[i] = Integer.parseInt(day1[1]);
//                String text= String.valueOf(simpleCalendarView.getDate());
//                Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT).show();
                i++;

                LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                Toast.makeText(getActivity(), String.valueOf(latLng),Toast.LENGTH_SHORT).show();



            }
        });

        exitLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long totalHours = time2.getTime() - time1.getTime();
                int days = (int) (totalHours / (1000*60*60*24));
                int hours = (int) ((totalHours - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60 * 24));
                min = (int) (totalHours - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                hours = (hours < 0 ? -hours : hours);
//                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("total_Hours", String.valueOf(min)).apply();
                String text= "location exited total hours: " + hours + "time now " + currentDateTimeString;
                Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT).show();
                date_picker_editTotalHour.setText(min + "H");

            }
        });

    }





}