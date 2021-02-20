package hila.peri.hoursreportapp.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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
    private  TextView HomePage_TXT_HeaderTime;
    private TextView HomePage_TXT_CurrentTime;
    private Button HomePage_BTN_Report;
    String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
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
        HomePage_TXT_CurrentTime = root.findViewById(R.id.HomePage_TXT_CurrentTime);
        HomePage_BTN_Report = root.findViewById(R.id.HomePage_BTN_Report);
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
                HomePage_TXT_CurrentTime.setText(currentDateTimeString);

            }
        });

        HomePage_BTN_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeApproveActivity.class);
                startActivity(intent);            }
        });
        if (currentLocation != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(MainActivity.LATITUDE, currentLocation.getLatitude());
            intent.putExtra(MainActivity.LONGITUDE, currentLocation.getLongitude());
        }

    }

    private void mapFunc() {
        //thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(getActivity())
                        .setTitle("Request Location permission")
                        .setMessage("You have to give this permission to access this feature ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            } else {
                // request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                currentLocation = location;
                            }
                        }
                    });
        }

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
    @Override
    public void onResume() {
        super.onResume();
        mapFunc();
    }

}