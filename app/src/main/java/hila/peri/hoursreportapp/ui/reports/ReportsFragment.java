package hila.peri.hoursreportapp.ui.reports;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hila.peri.hoursreportapp.R;

public class ReportsFragment extends Fragment {
    private DatePickerFragment datePickerFragment;
    private MapsFragment fragmentMap;

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

            fragmentMap = new MapsFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.record_LAY_map, fragmentMap);
            ft.commit();


        }

        private void findViews (View view){
        }

    }
