package hila.peri.hoursreportapp.ui.summary;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.util.Calendar;

//import hila.peri.hoursreportapp.MyAdapter;
import hila.peri.hoursreportapp.R;
import hila.peri.hoursreportapp.ui.home.HomeViewModel;
import hila.peri.hoursreportapp.ui.reports.SlideshowViewModel;

import static hila.peri.hoursreportapp.ui.home.HomeFragment.freeDayesCounter;
import static hila.peri.hoursreportapp.ui.home.HomeFragment.sickDayCounter;
import static hila.peri.hoursreportapp.ui.home.HomeFragment.workingDayesCounter;
import static hila.peri.hoursreportapp.ui.reports.DatePickerFragment.hours;
import static hila.peri.hoursreportapp.ui.reports.DatePickerFragment.min;


public class MonthPickerFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private DatePicker thePicker;
    private TextView editTotalMonthHours;
    private Button calc_BTN_calc;
    private EditText salary_TXT;
    private TextView editTotalSalary;
    private TextView editTotalFreeDayes;
    private  TextView editTotalWorkingDayes;
    private TextView editTotalSickDays;
    private int totalhoursbase;
    private String stringTotalHourBase;
    private DatabaseReference mDatabase;
    static String onpausetotal = "0";
    static String totalworkonpause = "0";
    static String totalfreeonpause = "0";
    static String totalsickonpause = "0";
    private String time;
    private int numOfMonths;
    String salaryPerHour;

//    View view;
    // Get calendar instance
    Calendar calendar = Calendar.getInstance();

    static final int DATE_DIALOG_ID = 1;
    private int mYear = 2013;
    private int mMonth = 5;
    private int mDay = 30;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_picker, container, false);

        findViews(view);
        initViews();



        return view;

    }



    private void findViews(View view ) {
        thePicker= view.findViewById(R.id.datePicker1);
        editTotalMonthHours = view.findViewById(R.id.editTotalMonthHours);
        salary_TXT = view.findViewById(R.id.salary_TXT);
        calc_BTN_calc = view.findViewById(R.id.calc_BTN_calc);
        editTotalSalary = view.findViewById(R.id.editTotalSalary);
        editTotalFreeDayes = view.findViewById(R.id.editTotalFreeDayes);
        editTotalWorkingDayes = view.findViewById(R.id.editTotalWorkingDayes);
        editTotalSickDays = view.findViewById(R.id.editTotalSickDays);
    }

    private void initViews() {

        if(hours == 0){
            totalhoursbase = Integer.parseInt(onpausetotal);
            stringTotalHourBase = String.valueOf(totalhoursbase);
            editTotalMonthHours.setText(stringTotalHourBase);
        }else{
            totalhoursbase = Integer.parseInt(onpausetotal) +  hours;
            stringTotalHourBase = String.valueOf(totalhoursbase);
            editTotalMonthHours.setText(stringTotalHourBase);
        }


        editTotalWorkingDayes.setText(String.valueOf(workingDayesCounter));

        editTotalFreeDayes.setText(String.valueOf(freeDayesCounter));

        editTotalSickDays.setText(String.valueOf(sickDayCounter));

        calc_BTN_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salaryPerHour = salary_TXT.getText().toString();
                int totalSalary = Integer.parseInt(salaryPerHour) * totalhoursbase;
                salary_TXT.getText().clear();
                editTotalSalary.setText(String.valueOf(totalSalary));

            }
        });

    }



    @Override
    public void onResume() {
        super.onResume();
        String timetotalmonth = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("totalmonth", "0");


        if(!timetotalmonth.equals("0")) {
            editTotalMonthHours.setText(String.valueOf(Integer.parseInt(timetotalmonth) + min));

        }

        editTotalWorkingDayes.setText(String.valueOf(workingDayesCounter));

        editTotalFreeDayes.setText(String.valueOf(freeDayesCounter));

        editTotalSickDays.setText(String.valueOf(sickDayCounter));

    }

    @Override
    public void onPause() {
        super.onPause();

        onpausetotal = editTotalMonthHours.getText().toString();
        totalworkonpause = editTotalWorkingDayes.getText().toString();
        totalfreeonpause = editTotalFreeDayes.getText().toString();
        totalsickonpause = editTotalSickDays.getText().toString();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("totalmonth", onpausetotal).commit();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("totalworking", totalworkonpause).commit();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("totalfree", String.valueOf(freeDayesCounter)).commit();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("totalsick", String.valueOf(sickDayCounter)).commit();

        hours = 0;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}