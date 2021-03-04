package hila.peri.hoursreportapp.ui.reports;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import hila.peri.hoursreportapp.R;

public class ReportsFragment extends Fragment {
    private DatePickerFragment datePickerFragment;
    private MapsFragment fragmentMap;
    private SickOrFreeFragment fragmentSickOrFree;
    String yes = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        findViews(view);
        initViews();

        return view;
    }


        private void initViews () {
            datePickerFragment = new DatePickerFragment();
            FragmentManager fm2 = getFragmentManager();
            FragmentTransaction ft2 = fm2.beginTransaction();
            ft2.add(R.id.report_LAY_reportDay, datePickerFragment);
            ft2.commit();

            String typeOfDay = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("type_of_day", "0");

            if(typeOfDay.equals("work_day")) {
                fragmentMap = new MapsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.record_LAY_map, fragmentMap);
                ft.commit();
            }else{
                fragmentSickOrFree = new SickOrFreeFragment();
                FragmentManager fm3 = getFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();
                ft3.add(R.id.record_LAY_map, fragmentSickOrFree);
                ft3.commit();
            }


        }

        private void findViews (View view){

        }



    }
