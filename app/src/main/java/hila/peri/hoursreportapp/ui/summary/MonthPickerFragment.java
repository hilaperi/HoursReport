package hila.peri.hoursreportapp.ui.summary;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    View view;
    // Get calendar instance
    Calendar calendar = Calendar.getInstance();

    static final int DATE_DIALOG_ID = 1;
    private int mYear = 2013;
    private int mMonth = 5;
    private int mDay = 30;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_month_picker, container, false);

        findViews();
        initViews();

        return view;

    }



    private void findViews( ) {
        thePicker= view.findViewById(R.id.datePicker1);
    }

    private void initViews( ) {

    }


}