package hila.peri.hoursreportapp.ui.reports;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import hila.peri.hoursreportapp.R;

public class SickOrFreeFragment extends Fragment {
    private ImageView statusImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sick_or_free, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void initViews() {

        String typeOfDay = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("type_of_day", "0");
        if (typeOfDay.equals("sick_day")) {
            statusImage.setImageResource(R.drawable.cry);
        } else if (typeOfDay.equals("free_day")) {
            statusImage.setImageResource(R.drawable.vaction);
        }

    }
    private void findViews(View view) {
        statusImage = view.findViewById(R.id.statusImage);
    }
}
