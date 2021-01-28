package hila.peri.hoursreportapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hila.peri.hoursreportapp.R;

public class HomeApproveActivity extends AppCompatActivity {
    private TextView ReportApprove_TXT_MainHeader;
    private TextView  ReportApprove_TXT_Secondheader;
    private ImageView ReportApprove_IMG_done;
    private TextView ReportApprove_TXT_thankYou;
    private Button ReportApprove_BTN_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_approve);
        findViews();
        initViews();
    }

    private void findViews() {
        ReportApprove_TXT_MainHeader = findViewById(R.id.ReportApprove_TXT_MainHeader);
        ReportApprove_TXT_Secondheader = findViewById(R.id.ReportApprove_TXT_Secondheader);
        ReportApprove_IMG_done = findViewById(R.id.ReportApprove_IMG_done);
        ReportApprove_TXT_thankYou = findViewById(R.id.ReportApprove_TXT_thankYou);
        ReportApprove_BTN_finish = findViewById(R.id.ReportApprove_BTN_finish);
    }

    private void initViews() {
        ReportApprove_BTN_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}
