package hila.peri.hoursreportapp.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;

import hila.peri.hoursreportapp.MainActivity;
import hila.peri.hoursreportapp.R;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private HomeViewModel homeViewModel;
    private TextView HomePage_TXT_header;
    private Spinner HomePage_SPNR_reportsType;
    private ImageView HomePage_IMG_clock;
    private TextView HomePage_TXT_HeaderTime;
    private TextClock HomePage_TXT_CurrentTime;
    private Button HomePage_BTN_Report;
    private TextView totalFreeDayes;
    public static int freeDayesCounter = 0;
    public static int sickDayCounter = 0;
    public static int workingDayesCounter = 0;
    String currentDateTimeString = java.text.DateFormat.getTimeInstance().format(new Date());
    Date time = new Date();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        findViews();
        initViews();

        return root;
    }

    private void findViews() {
        HomePage_TXT_header = root.findViewById(R.id.HomePage_TXT_header);
        HomePage_SPNR_reportsType = root.findViewById(R.id.HomePage_SPNR_reportsType);
        HomePage_IMG_clock = root.findViewById(R.id.HomePage_IMG_clock);
        HomePage_TXT_HeaderTime = root.findViewById(R.id.HomePage_TXT_HeaderTime);
        HomePage_TXT_CurrentTime = (TextClock) root.findViewById(R.id.HomePage_TXT_CurrentTime);
        HomePage_BTN_Report = root.findViewById(R.id.HomePage_BTN_Report);
        totalFreeDayes = root.findViewById(R.id.totalFreeDayes);
    }

    private void initViews() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Repotrs_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HomePage_SPNR_reportsType.setAdapter(adapter);
        HomePage_SPNR_reportsType.setOnItemSelectedListener(this);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                HomePage_TXT_header.setText(s);
                HomePage_TXT_CurrentTime.setFormat24Hour("EEE MMM d hh:mm:ss a");

            }
        });

    }


    public void onPointerCaptureChanged(boolean hasCapture) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
        if(text.equals("Work")) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);
            HomePage_BTN_Report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        workingDayesCounter += 1;
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("reported_time", currentDateTimeString).apply();
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("type_of_day", "work_day").apply();
//                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("working_day", String.valueOf(workingDayesCounter)).apply();
                        Intent intent = new Intent(getActivity(), HomeApproveActivity.class);
                        startActivity(intent);
                }
            });


        }else if(text.equals("Free")){
            ((TextView) parent.getChildAt(0)).setTextColor(Color.YELLOW);
            HomePage_BTN_Report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    freeDayesCounter +=1;
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("type_of_day", "free_day").apply();
                    Intent intent = new Intent(getActivity(), HomeApproveActivity.class);
                    startActivity(intent);
                }
            });
        }else if(text.equals("Sick")){
            ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
            HomePage_BTN_Report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   sickDayCounter +=1;

                   PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("type_of_day", "sick_day").apply();
                   Intent intent = new Intent(getActivity(), HomeApproveActivity.class);
                   startActivity(intent);
                }
            });

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        }

}