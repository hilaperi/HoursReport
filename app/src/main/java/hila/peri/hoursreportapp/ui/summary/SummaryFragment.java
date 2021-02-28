package hila.peri.hoursreportapp.ui.summary;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import hila.peri.hoursreportapp.R;
import hila.peri.hoursreportapp.ui.reports.DatePickerFragment;

public class SummaryFragment extends Fragment {

    private MonthPickerFragment monthPickerFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void initViews() {
        monthPickerFragment = new MonthPickerFragment();
        FragmentManager fm2 = getFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        ft2.add(R.id.summary_LAY_reportDay, monthPickerFragment);
        ft2.commit();



    }


    private void findViews (View view){
    }
}