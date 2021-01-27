package hila.peri.hoursreportapp.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.Date;
import hila.peri.hoursreportapp.R;
import androidx.appcompat.app.AppCompatActivity;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private HomeViewModel homeViewModel;
    String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
    Button homePage_BTN_OK;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView homePage_TXT_header = root.findViewById(R.id.homePage_TXT_header);
        Spinner homePage_SPN_type = root.findViewById(R.id.homePage_SPN_type);
        final ImageView homePage_IMG_clock = root.findViewById(R.id.homePage_IMG_clock);
        final TextView homePage_TXT_txtTime = root.findViewById(R.id.homePage_TXT_txtTime);
        final TextView homePage_TXT_currentTime = root.findViewById(R.id.homePage_TXT_currentTime);
        Button homePage_BTN_OK = root.findViewById(R.id.homePage_BTN_OK);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Repotrs_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homePage_SPN_type.setAdapter(adapter);
        homePage_SPN_type.setOnItemSelectedListener(this);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                homePage_TXT_header.setText(s);
                homePage_TXT_currentTime.setText(currentDateTimeString);

            }
        });

        homePage_BTN_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeApprove.class);
                startActivity(intent);            }
        });


        return root;
    }




    public void onPointerCaptureChanged(boolean hasCapture) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);

    }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


}