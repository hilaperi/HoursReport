package hila.peri.hoursreportapp.ui.reports;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.MapView;

import java.util.Calendar;

import hila.peri.hoursreportapp.R;

public class DatePickerFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    CalendarView simpleCalendarView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_reports, container, false);


        return view;
    }


}