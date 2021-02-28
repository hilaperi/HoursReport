package hila.peri.hoursreportapp.ui.summary;

import android.app.DatePickerDialog;
import android.os.Bundle;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

import java.lang.reflect.Field;
import java.util.Calendar;

import hila.peri.hoursreportapp.R;
import hila.peri.hoursreportapp.ui.home.HomeViewModel;
import hila.peri.hoursreportapp.ui.reports.SlideshowViewModel;


public class MonthPickerFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private DatePicker thePicker;
    private TextView editTotalMonthHours;
    private Button calc_BTN_calc;
    private EditText salary_TXT;
//    private String salaryPerHour;
    private TextView editTotalSalary;
    private TextView editTotalFreeDayes;
//    private TextView dfate_picker_edibtTotalHour;
//    int totalSalary=0;
    String salaryPerHour;

//    View view;
    // Get calendar instance
    Calendar calendar = Calendar.getInstance();

    static final int DATE_DIALOG_ID = 1;
    private int mYear = 2013;
    private int mMonth = 5;
    private int mDay = 30;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
//        dfate_picker_edibtTotalHour = view.findViewById(R.id.dfate_picker_editTotalHour);
    }

    private void initViews( ) {


        String totalHours = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("total_Hours", "0");
        editTotalMonthHours.setText(totalHours);
        String totalFreeDayes = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("free_day", "0");
        editTotalFreeDayes.setText(totalFreeDayes);

//        int totHours = Integer.parseInt(totalHours);

        calc_BTN_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salaryPerHour = salary_TXT.getText().toString();
                int totalSalary = Integer.parseInt(salaryPerHour) * Integer.parseInt(totalHours);
//                int salaryPerH = salaryPerHour;
//                int totalSalary =  salaryPerH;
//                dfate_picker_edibtTotalHour.clearComposingText();
                salary_TXT.getText().clear();
                editTotalSalary.setText(String.valueOf(totalSalary));

            }
        });

    }


}