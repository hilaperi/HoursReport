package hila.peri.hoursreportapp.ui.slideshow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hila.peri.hoursreportapp.R;

public class ReportsActivity extends Fragment {
    private DetailsDayFragment detailsDayFragment;
    private MapsFragment fragmentMap;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reports, container, false);

        findViews(view);
        initViews();

        return view;
    }


        private void initViews () {
            detailsDayFragment = new DetailsDayFragment();
            FragmentManager fm2 = getFragmentManager();
            FragmentTransaction ft2 = fm2.beginTransaction();
            ft2.add(R.id.report_LAY_reportDay, detailsDayFragment);
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
