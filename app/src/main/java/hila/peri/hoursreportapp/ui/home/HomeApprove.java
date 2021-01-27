package hila.peri.hoursreportapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hila.peri.hoursreportapp.R;

public class HomeApprove extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_approve);

        setContentView(R.layout.approve_report_main);
        findViews();
        initViews();


    }

    private void findViews() {
        Button cancel_button = findViewById(R.id.cancel_button);
        TextView homePage_TXT_txtTime = findViewById(R.id.homePage_TXT_txtTime);
        ImageView homePage_IMG_clock = findViewById(R.id.homePage_IMG_clock);
        TextView homedPage_TXT_header = findViewById(R.id.homedPage_TXT_header);
        TextView homePage_TXT_header = findViewById(R.id.homePage_TXT_header);

//        cancel_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeApprove.this, HomeFragment.class);
//                startActivity(intent);
//            }
//        });
    }

    private void initViews() {


    }
}
