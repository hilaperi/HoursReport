package hila.peri.hoursreportapp.ui.reports;

import android.app.DatePickerDialog;
import android.content.Context;
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

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hila.peri.hoursreportapp.R;

public class DatePickerFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    CalendarView simpleCalendarView;
    private MapsFragment location = new MapsFragment();
    private TextView date_picker_editTimeReport;
    private Button enterLoc;
    private Button exitLoc;
    GoogleMap googleMap;
    Location loc;
    String currentDateTimeString = java.text.DateFormat.getTimeInstance().format(new Date());
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");


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
    }

    private void initViews () throws ParseException {
//        date_picker_editTimeReport.setFormat12Hour(null);
//        date_picker_editTimeReport.setFormat24Hour("hh:mm:ss a");
        String time = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("reported_time", "reported time");
//        "reported time"
        date_picker_editTimeReport.setText(time.toString());
//        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().clear().apply();
        Date time1 = dateFormat.parse(time);
        Date time2 = dateFormat.parse(currentDateTimeString);


        enterLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                location.onLocationChanged(loc);
//                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("has_clicked12", "yes").apply();

                String text= "Location saved have a great day at work !";
                Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT).show();
            }
        });

        exitLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long totalHours = Integer.parseInt(currentDateTimeString.substring(3,5)) - Integer.parseInt(time.substring(3,5));
                long totalHours = time2.getTime() - time1.getTime();
                int days = (int) (totalHours / (1000*60*60*24));
                int hours = (int) ((totalHours - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60 * 24));
                int  min = (int) (totalHours - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                hours = (hours < 0 ? -hours : hours);
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("total_Hours", String.valueOf(hours)).apply();
                String text= "location exited total hours: " + totalHours + "time now " + currentDateTimeString;
                Toast.makeText(getActivity(), text,Toast.LENGTH_SHORT).show();
            }
        });

    }

}